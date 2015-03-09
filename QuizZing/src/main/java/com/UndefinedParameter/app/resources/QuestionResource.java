package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuestionAddView;
import com.UndefinedParameter.views.QuestionCreatorView;

@Path("/question")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource {
	
	private QuizManager quizManager;
	
	public QuestionResource(QuizDAO quizDAO, QuestionDAO questionDAO, QuizScoreDAO quizScoreDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
	}
	
	@GET
	@Path("/create")
	public Response getAddQuestionView(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		return Response.ok(new QuestionCreatorView(user, quizId, groupId)).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creatQuestionForQuiz(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @Valid Question question) {
		//User must be logged in to perform this
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		
		try {
			question.setRating(3.0);
			question.setQuestionDifficulty(3.0);
			long questionId = quizManager.createQuestion(question);
			if(questionId > 0 && quizManager.addQuestionToQuiz(quizId, questionId)) {
				response.put("response", "success");
				response.put("redirect", "/quiz/edit?quizId=" + quizId + "&groupId=" + question.getGroupId());
			}
			else {
				response.put("response", "fail");
				response.put("message", "Unable to create your question.");
			}
			return Response.ok(response).build();
		}
		catch(Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/add")
	public Response getQuestionAddView(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		else {
			Quiz quiz = quizManager.findQuiz(quizId);
			List<Question> questions = quizManager.findUnaddedGroupQuestions(groupId, quizId);
			if(quiz.getCreatorId() == user.getId()) {
				//only the creator can add to this quiz
				//TODO: Collaborators
				return Response.ok(new QuestionAddView(user, questions, quizId, groupId)).build();
			}
			else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
	}
	
	@POST
	@Path("/add")
	public Response addQuestion(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			String url = "edit?groupId=" + groupId + "&quizId=" + quizId;
			return Response.ok(new LoginView(url)).build();
		}
		if(quizId > 0) {
			if(quizManager.addQuestionToQuiz(quizId, questionId)) {
				return Response.ok().build();
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("/remove")
	public Response removeQuestion(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("quizId") long quizId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		if(questionId == 0 || quizId == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		//remove and return ok
		if(quizManager.removeQuestionFromQuiz(quizId, questionId)) {
			return Response.ok().build();
		}
		//removal failed
		return Response.status(Status.BAD_REQUEST).build();
	}
}
