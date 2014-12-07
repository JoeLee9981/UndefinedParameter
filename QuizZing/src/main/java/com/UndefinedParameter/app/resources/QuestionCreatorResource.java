package com.UndefinedParameter.app.resources;

import java.awt.List;
import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.jdbi.QuestionDAO;
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> creatQuestion(@Valid Question question) {
		HashMap<String, String> response = new HashMap<String, String>();
		if(QuestionDAO.createQuestion(question)) {
			response.put("response", "success");
		}
		else {
			response.put("response", "fail");
		}
		return response;
	}
	
	public List	getQuestionTypes()
	{
		return null;
	}
	
}
