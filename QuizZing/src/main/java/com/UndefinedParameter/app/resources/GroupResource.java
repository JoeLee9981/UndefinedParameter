package com.UndefinedParameter.app.resources;


import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.views.GroupView;
import com.UndefinedParameter.views.GroupsView;


@Path("/group")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupResource {

	public GroupManager manager;
	
	public GroupResource(OrganizationDAO orgsDAO, GroupDAO groupDAO) {
		manager = new GroupManager(orgsDAO, groupDAO);
	}
	
	@GET
	public GroupsView getGroupsView() {
		return new GroupsView();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> addGroup(@Valid Group group) {
			
		HashMap<String, String> response = new HashMap<String, String>();
		int groupId = manager.addGroup(group);
		
		if(groupId >= 1) {
			response.put("response", "success");
			response.put("message", "Group " + group.getName() + " has been created.");
		}
		else {
			response.put("response", "fail");
			response.put("message", "Unable to create group " + group.getName());
		}
		
		return response;
	}
	
	@GET
	@Path("/{groupId}")
	public GroupView getGroupView(@PathParam("groupId") int groupId) {
		Group group = manager.findGroupById(groupId);
		return new GroupView(group, manager.findParentOrganization(group.getOrganizationId()));
	}
	
	
}
