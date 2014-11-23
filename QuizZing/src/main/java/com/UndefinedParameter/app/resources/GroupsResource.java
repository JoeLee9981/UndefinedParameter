package com.UndefinedParameter.app.resources;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpTester.Response;

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
	public Response addGroup(@FormParam("id") Set<Integer> ids, 
							 @FormParam("name") Set<String> names) {
		return new Response();
	}
}
