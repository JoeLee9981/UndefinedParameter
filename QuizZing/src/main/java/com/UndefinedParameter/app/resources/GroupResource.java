package com.UndefinedParameter.app.resources;


import io.dropwizard.auth.Auth;

import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.views.GroupView;
import com.UndefinedParameter.views.GroupsView;
import com.UndefinedParameter.views.LoginView;


@Path("/group")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupResource {

	public GroupManager manager;
	public QuizManager quizManager;
	
	public GroupResource(OrganizationDAO orgsDAO, GroupDAO groupDAO, QuizDAO quizDAO, QuestionDAO questionDAO) {
		manager = new GroupManager(orgsDAO, groupDAO);
		this.quizManager = new QuizManager(quizDAO, questionDAO);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroup(@Auth(required = false) User user, @Valid Group group) {
			
		if(user == null) {
			return Response.ok(new LoginView()).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		long groupId = manager.addGroup(group);
		
		if(groupId >= 1) {
			response.put("response", "success");
			response.put("redirect", String.format("/group?groupId=%d", groupId));
		}
		else {
			response.put("response", "fail");
			response.put("message", "Unable to create group " + group.getName());
		}
		
		return Response.ok(response).build();
	}
	
	@GET
	public GroupView getGroupView(@QueryParam("groupId") int groupId) {
		Group group = manager.findGroupById(groupId);
		return new GroupView(group, manager.findParentOrganization(group.getOrganizationId()), quizManager);
	}
	
	
}
