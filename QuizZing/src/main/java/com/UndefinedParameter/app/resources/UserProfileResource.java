package com.UndefinedParameter.app.resources;

import java.util.HashMap;

import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;

import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserManager;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.UserDAO;
import com.UndefinedParameter.views.UserProfileView;

@Path("/user")
@Produces(MediaType.TEXT_HTML)
public class UserProfileResource {
	
	public UserManager userManager;
	public QuizManager quizManager;
	public GroupManager groupManager;
	
	public UserProfileResource(UserDAO userdao, QuizDAO quizdao, QuestionDAO questiondao, OrganizationDAO orgdao, GroupDAO groupdao)
	{
		this.userManager = new UserManager(userdao);
		this.quizManager = new QuizManager(quizdao, questiondao);
		this.groupManager = new GroupManager(orgdao, groupdao);
	}

	/*
	 * Retrieves user profile page, returning a unique view based on whether 
	 * it's the user's profile or not.
	 */
	@GET
	public Response getUserProfileView(@Auth(required = false) User user, @QueryParam("userid") long userID) {
		
		if(user != null && user.getId() == userID) {
			return Response.ok(new UserProfileView("profile.ftl", user, quizManager.findQuizzesByCreatorId(user.getId()), groupManager.findRegisteredGroups(user.getId()), true)).build();
		}
		else {
			User currentUser = userManager.findUserById(userID);
			return Response.ok(new UserProfileView("profile.ftl", currentUser, quizManager.findQuizzesByCreatorId(currentUser.getId()), groupManager.findRegisteredGroups(userID), false)).build();
		}
	}
	
	
	@GET
	@Path("/edit")
	public Response getProfileEditView(@Auth User user) {
		return Response.ok(new UserProfileView("edit_profile.ftl", user, quizManager.findQuizzesByCreatorId(user.getId()), groupManager.findRegisteredGroups(user.getId()), false)).build();
	}
	
	@POST
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(@Valid User user) {
		HashMap<String, String> response = new HashMap<String, String>();

		try {
			if(userManager.updateUser(user)) {
				response.put("response", "success");
				return Response.ok(response).build();
			}
			else {
				response.put("response", "error");
				response.put("message", "User update failed.");
				return Response.ok(response).build();
			}
			
		}
		catch(Exception e) {
			//TODO: return error message
			return Response.status(500).build();
		}
		
	}
}
