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

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuestionCreatorView;

@Path("/question")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionCreatorResource {
	
	private QuizManager quizManager;
	
	public QuestionCreatorResource(QuizDAO quizDAO, QuestionDAO questionDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO);
	}
	
	@GET
	@Path("/create")
	public Response getAddQuestionView(@Auth(required = false) User user, @QueryParam("quizId") int quizId) {
		
		if(user == null) {
			return Response.ok(new LoginView()).build();
		}
		
		return Response.ok(new QuestionCreatorView(quizId)).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creatQuestionForQuiz(@QueryParam("quizId") int quizId, @Valid Question question) {
		HashMap<String, String> response = new HashMap<String, String>();
		
		try {
			long questionId = quizManager.createQuestion(question);
			if(questionId > 0 && quizManager.addQuestionToQuiz(quizId, questionId)) {
				response.put("response", "success");
				response.put("redirect", "/quiz/edit?quizId=" + quizId);
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
	
}
