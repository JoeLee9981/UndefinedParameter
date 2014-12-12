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

import org.eclipse.jetty.http.HttpGenerator.Result;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.views.QuizCreatorView;
import com.UndefinedParameter.views.QuizView;
import com.UndefinedParameter.views.ScoreView;

@Path("/quiz")
@Produces(MediaType.TEXT_HTML)
public class QuizResource {
	
	@GET
	@Path("/{id}")
	public QuizView getQuizView(@PathParam("id") int id) {
		
		return new QuizView(QuizManager.getRandomizedQuestions(id));
	}
	
	@GET
	@Path("/create")
	public QuizCreatorView getQuizCreatorView() {
		
		return new QuizCreatorView();
	}
	
	@POST
	@Path("/{groupId}/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> createQuiz(@Valid Quiz quiz, @PathParam("groupId") int groupId) {
		
		HashMap<String, String> response = new HashMap<String, String>();
		//TODO: Find some of these instead of insert
		quiz.setCreatorId(1);
		quiz.setDifficulty(3);
		quiz.setRating(3);
		quiz.setTime(10000);
		
		int quizId = QuizManager.createQuiz(quiz);
		if(quizId >= 1) {
			if(QuizManager.addQuizToGroup(quizId, groupId)) {
				response.put("response", "success");
				response.put("message", "Quiz is successfully created.");
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
		return response;
	}
	
	@GET
	@Path("/score/{id}")
	public ScoreView getScoreView(@PathParam("id") int id) {
		
		return new ScoreView(id);
	}
	
	@POST
	@Path("/question/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Result createQuestion(@Valid Question question)
	{
		return null;
	}
	
}