package com.UndefinedParameter.app.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.views.GroupsView;

@Path("/group")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupsResource {

	@GET
	public GroupsView getGroupsView() {
		return new GroupsView();
	}
	
	@POST
	public void addGroup(@Valid Group group) {
		if(group.getId() == 0 || group.getName() == null) {
			//TODO: Return a failure response
		}
			
		GroupManager.addGroup(group);
		//return new Response();
	}
}
