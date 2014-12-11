package com.UndefinedParameter.app.resources;

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
		
		return new QuizView(QuizManager.findQuiz(id));
	}
	
	@GET
	@Path("/create")
	public QuizCreatorView getQuizCreatorView() {
		
		return new QuizCreatorView();
	}
	
	@POST
	@Path("/create")
	public Result createQuiz(@Valid Quiz quiz) {
		
		return null;
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