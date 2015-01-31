package com.UndefinedParameter.app.resources;

import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.views.QuestionCreatorView;

@Path("/quiz/create/question")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionCreatorResource {
	
	private QuizManager quizManager;
	
	public QuestionCreatorResource(QuizDAO quizDAO, QuestionDAO questionDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO);
	}
	
	@GET
	public QuestionCreatorView getQuestionCreatorView() {
		
		return new QuestionCreatorView(-1);
	}
	
	@Path("/{quizId}")
	@GET
	public QuestionCreatorView getAddQuestionView(@PathParam("quizId") int quizId) {
		return new QuestionCreatorView(quizId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> creatQuestion(@Valid Question question) {
		HashMap<String, String> response = new HashMap<String, String>();
		
		//TODO: Clean me up - return Response, handle all exceptions and messages
		try {
			if(quizManager.createQuestion(question) > 0) {
				response.put("response", "success");
				response.put("message", "Your question has been created.");
			}
			else {
				response.put("response", "fail");
				response.put("message", "Unable to create your question.");
			}
			return response;
		}
		catch(Exception e) {
			//return a bad response
			return null;
		}
	}
	
	@POST
	@Path("/{quizId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> creatQuestionForQuiz(@PathParam("quizId") int quizId, @Valid Question question) {
		HashMap<String, String> response = new HashMap<String, String>();
		
		//TODO: Clean me up - return Response and handle all exceptions
		try {
			long questionId = quizManager.createQuestion(question);
			if(questionId > 0 && quizManager.addQuestionToQuiz(quizId, questionId)) {
				response.put("response", "success");
				response.put("message", "Your question has been created.");
			}
			else {
				response.put("response", "fail");
				response.put("message", "Unable to create your question.");
			}
			return response;
		}
		catch(Exception e) {
			//TODO: Return a response error
			return null;
		}
	}
	
}
