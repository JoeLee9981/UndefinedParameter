package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.QuizScore;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuizCreatorView;
import com.UndefinedParameter.views.QuizEditQuestionsView;
import com.UndefinedParameter.views.QuizEditView;
import com.UndefinedParameter.views.QuizView;
import com.UndefinedParameter.views.QuizzesView;
import com.UndefinedParameter.views.ScoreView;

@Path("/quiz")
@Produces(MediaType.TEXT_HTML)
public class QuizResource {
	
	private QuizManager quizManager;
	private GroupManager groupManager;
	
	public QuizResource(QuizDAO quizDAO, QuestionDAO questionDAO, OrganizationDAO orgDAO, GroupDAO groupDAO, QuizScoreDAO quizScoreDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
		groupManager = new GroupManager(orgDAO, groupDAO);
	}
	
	@GET
	public Response getQuizView(@Auth(required = false) User user, @QueryParam("quizId") long id, @QueryParam("groupId") long groupId) {
		
		//invalid id return bad request
		if(id < 1)
			return Response.status(Status.BAD_REQUEST).build();
		
		Quiz quiz = quizManager.getRandomizedQuestions(id);
		float userBestScore = -1.0f;
		
		//return bad request if no quiz found
		if(quiz == null)
			return Response.status(Status.BAD_REQUEST).build();
		
		//if user is not logged in
		if(user == null)
			return Response.ok(new QuizView(user, quiz, groupId, false, false, 0, 0, userBestScore)).build();
		
		//obtain the user rating
		int userRating = quizManager.findUserRating(user.getId(), quiz.getQuizId());
		int userDiff = quizManager.findUserDifficulty(user.getId(), quiz.getQuizId());
		List<QuizScore> userScores = quizManager.findScoresByQuizAndUser(id, user.getId());
		
		for(QuizScore score : userScores) {
			if(score.getScore() > userBestScore)
				userBestScore = score.getScore();
		}
		
		//set editable to true if the user is the creator
		if(user != null && user.getId() == quiz.getCreatorId()) {
			//user is logged in and owner
			return Response.ok(new QuizView(user, quiz, groupId, true, true, userRating, userDiff, userBestScore)).build();
		}
		else {
			//user is logged in but not the owner
			return Response.ok(new QuizView(user, quiz, groupId, true, false, userRating, userDiff, userBestScore)).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuizView(@Auth(required = false) User user, @Valid QuizScore newScore) {
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		
		if(quizManager.insertScore(newScore.getQuizId(), newScore.getUserId(), newScore.getScore()))
			response.put("response", "success");
		else
			response.put("fail", "Unable to save score.");
		
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/create")
	public Response getQuizCreatorView(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		else if(groupId > 0) {
			return Response.ok(new QuizCreatorView(user, groupManager.findGroupById(groupId))).build();
		}
		//not a valid group id return a bad request
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createQuiz(@Auth(required = false) User user, @Valid Quiz quiz, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		//TODO: Find some of these instead of insert
		quiz.setCreatorId(user.getId());
		quiz.setDifficulty(3);
		quiz.setRating(3);
		//this is for future use
		quiz.setTime(10000);
		
		long quizId = quizManager.createQuiz(quiz);
		if(quizId >= 1) {
			if(quizManager.addQuizToGroup(quizId, groupId)) {
				response.put("response", "success");
				response.put("redirect", "/group?groupId=" + groupId);
			}
			else {
				response.put("response", "fail");
				response.put("message", "Unable to add the quiz to the group");
			}
		}
		else {
			response.put("response", "fail");
			response.put("message", "Unable to create the quiz");
		}
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/score")
	public Response getScoreView(@Auth(required=false) User user, @QueryParam("quizId") int id) {
		
		return Response.ok(new ScoreView(user, id)).build();
	}
	
	@GET
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editQuiz(@Auth(required = false) User user, @QueryParam("quizId") long id, @QueryParam("groupId") long groupId) {
		
		//invalid id
		if(id == 0)
			return Response.status(Status.BAD_REQUEST).build();
		//user must be logged in
		if(user == null)
			return Response.ok(new LoginView(user)).build();
		//find quiz
		Quiz quiz = quizManager.findQuiz(id);
		Group group = null;
		if(groupId > 0)
			group = groupManager.findGroupById(groupId);
		else
			group = groupManager.findGroupByQuizId(id);
		
		if(quiz != null && user.getId() == quiz.getCreatorId()) {
			return Response.ok(new QuizEditView(user, quiz, group)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@GET
	@Path("/edit/questions")
	public Response getQuizQuestions(@Auth(required = false) User user, @QueryParam("quizId") long quizId) {
		
		if(quizId == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		Quiz quiz = quizManager.findQuiz(quizId);
		
		if(quiz != null && (user.getId() == quiz.getCreatorId() || quiz.isOpen())) {
			return Response.ok(new QuizEditQuestionsView(quiz)).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	@GET
	@Path("/quizzes")
	public Response getQuizzesPage(@Auth(required = false) User user) {
		if(user != null) {
			return Response.ok(new QuizzesView(user, quizManager.findTopQuizzes(), quizManager.findQuizzesByCreatorId(user.getId()))).build();
		}
		else {
			return Response.ok(new QuizzesView(user, quizManager.findTopQuizzes(), null)).build();
		}
	}
	
	@POST
	@Path("/rate/rating")
	public Response rateQuizQuality(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("rating") int rating) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if(quizManager.rateQuizQuality(user.getId(), quizId, rating)) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/rate/difficulty")
	public Response rateQuizDifficulty(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("rating") int rating) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if(quizManager.rateQuizDifficulty(user.getId(), quizId, rating)) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}