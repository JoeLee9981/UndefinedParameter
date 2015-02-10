package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;

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
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuizCreatorView;
import com.UndefinedParameter.views.QuizEditView;
import com.UndefinedParameter.views.QuizView;
import com.UndefinedParameter.views.QuizzesView;
import com.UndefinedParameter.views.ScoreView;

@Path("/quiz")
@Produces(MediaType.TEXT_HTML)
public class QuizResource {
	
	private QuizManager quizManager;
	private GroupManager groupManager;
	
	public QuizResource(QuizDAO quizDAO, QuestionDAO questionDAO, OrganizationDAO orgDAO, GroupDAO groupDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO);
		groupManager = new GroupManager(orgDAO, groupDAO);
	}
	
	@GET
	public Response getQuizView(@Auth(required = false) User user, @QueryParam("quizId") long id) {
		
		//invalid id return bad request
		if(id < 1)
			return Response.status(Status.BAD_REQUEST).build();
		
		Quiz quiz = quizManager.getRandomizedQuestions(id);
		
		//return bad request if no quiz found
		if(quiz == null)
			return Response.status(Status.BAD_REQUEST).build();
		
		//set editable to true if the user is the creator
		if(user != null && user.getId() == quiz.getCreatorId())
			return Response.ok(new QuizView(quiz, true)).build();
		else
			return Response.ok(new QuizView(quiz, false)).build();
	}
	
	@GET
	@Path("/create")
	public Response getQuizCreatorView(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView()).build();
		}
		else if(groupId > 0) {
			return Response.ok(new QuizCreatorView(groupManager.findGroupById(groupId))).build();
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
	public Response getScoreView(@QueryParam("quizId") int id) {
		
		return Response.ok(new ScoreView(id)).build();
	}
	
	@GET
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editQuiz(@Auth User user, @QueryParam("quizId") long id, @QueryParam("groupId") long groupId) {
		
		//invalid id
		if(id == 0)
			return Response.status(Status.BAD_REQUEST).build();
		//user must be logged in
		if(user == null)
			return Response.ok(new LoginView()).build();
		//find quiz
		Quiz quiz = quizManager.findQuiz(id);
		Group group = null;
		if(groupId > 0)
			groupManager.findGroupById(groupId);
		if(quiz != null && user.getId() == quiz.getCreatorId()) {
			return Response.ok(new QuizEditView(quiz, group)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	@GET
	@Path("/quizzes")
	public Response getQuizzesPage(@Auth(required = false) User user) {
		if(user != null) {
			return Response.ok(new QuizzesView(quizManager.findTopQuizzes(), quizManager.findQuizzesByCreatorId(user.getId()))).build();
		}
		else {
			return Response.ok(new QuizzesView(quizManager.findTopQuizzes(), null)).build();
		}
	}
}