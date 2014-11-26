package com.UndefinedParameter.app.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpGenerator.Result;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.views.QuestionCreatorView;

@Path("/quiz/create/question")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionCreatorResource {
	
	@GET
	public QuestionCreatorView getQuestionCreatorView() {
		
		return new QuestionCreatorView();
	}
	
	@POST
	public Result creatQuestion(@Valid Question question) {
		
		return null;
	}
	
}
