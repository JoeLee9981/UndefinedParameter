package com.UndefinedParameter.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.views.QuizView;

@Path("/quiz/{id}")
@Produces(MediaType.TEXT_HTML)
public class QuizResource {
	
	@GET
	public QuizView getQuizView(@PathParam("id") int id) {
		
		return new QuizView(QuizManager.findQuiz(id));
	}
}