package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserManager;
import com.UndefinedParameter.jdbi.UserDAO;
import com.UndefinedParameter.views.UserProfileView;

@Path("/user")
@Produces(MediaType.TEXT_HTML)
public class UserProfileResource {
	
	private UserManager userManager;
	
	public UserProfileResource(UserDAO userdao)
	{
		this.userManager = new UserManager(userdao);
	}

	/*
	 * Retrieves user profile page, returning a unique view based on whether 
	 * it's the user's profile or not.
	 */
	@GET
	public Response getUserProfileView(@Auth(required = false) User user, @QueryParam("userid") long userID) {
		
		if(user != null && user.getId() == userID) {
			return Response.ok(new UserProfileView("user_profile.ftl", user)).build();
		}
		else {
			return Response.ok(new UserProfileView("profile.ftl")).build();
		}
	}
	
	@GET
	@Path("/edit")
	public Response getProfileEditView(@Auth User user) {
		return Response.ok(new UserProfileView("edit_profile.ftl", user)).build();
	}
}
