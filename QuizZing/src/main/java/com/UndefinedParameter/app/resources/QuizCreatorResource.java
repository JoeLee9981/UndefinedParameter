package com.UndefinedParameter.app.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpGenerator.Result;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.views.QuizCreatorView;

@Path("/quiz/create")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizCreatorResource {
	
	@GET
	public QuizCreatorView getQuizCreatorView() {
		
		return new QuizCreatorView();
	}
	
	@POST
	public Result createQuiz(@Valid Quiz quiz) {
		
		return null;
	}
}