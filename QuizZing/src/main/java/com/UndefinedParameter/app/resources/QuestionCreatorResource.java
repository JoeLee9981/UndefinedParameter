package com.UndefinedParameter.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.views.QuestionCreatorView;

@Path("/quiz/create/question")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionCreatorResource {
	
	@GET
	public QuestionCreatorView getQuestionCreatorView() {
		
		return new QuestionCreatorView();
	}
	
}
