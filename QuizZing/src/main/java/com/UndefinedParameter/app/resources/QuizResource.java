package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.jetty.http.HttpGenerator.Result;

import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuizCreatorView;
import com.UndefinedParameter.views.QuizView;
import com.UndefinedParameter.views.ScoreView;

@Path("/quiz")
@Produces(MediaType.TEXT_HTML)
public class QuizResource {
	
	private QuizManager quizManager;
	private GroupManager groupManager;
	
	public QuizResource(QuizDAO quizDAO, QuestionDAO questionDAO, OrganizationDAO orgDAO, GroupDAO groupDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO);
		groupManager = new GroupManager(orgDAO, groupDAO);
	}
	
	@GET
	public QuizView getQuizView(@QueryParam("quizId") long id) {
		
		return new QuizView(quizManager.getRandomizedQuestions(id));
	}
	
	@GET
	@Path("/create")
	public Response getQuizCreatorView(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView()).build();
		}
		else if(groupId > 0) {
			return Response.ok(new QuizCreatorView(groupManager.findGroupById(groupId))).build();
		}
		//not a valid group id return a bad request
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createQuiz(@Auth(required = false) User user, @Valid Quiz quiz, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		//TODO: Find some of these instead of insert
		quiz.setCreatorId(user.getId());
		quiz.setDifficulty(3);
		quiz.setRating(3);
		//this is for future use
		quiz.setTime(10000);
		
		long quizId = quizManager.createQuiz(quiz);
		if(quizId >= 1) {
			if(quizManager.addQuizToGroup(quizId, groupId)) {
				response.put("response", "success");
				response.put("redirect", "/group?groupId=" + groupId);
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
		return Response.ok(response).build();
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