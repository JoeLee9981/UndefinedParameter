package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.UserDAO;

@Path("/user")
@Produces(MediaType.TEXT_HTML)
public class UserProfileResource {
	
	//TODO: Need user manager?
	
	public UserProfileResource(UserDAO userdao)
	{
		
	}

	@GET
	public Response getUserProfileView(@Auth(required = false) User user) {
		
		if(user == null) {
			//TODO: Stranger's profile view.
		}
		else {
			//TODO: User's profile view.
		}
		
		return Response.ok().build();
	}
}
