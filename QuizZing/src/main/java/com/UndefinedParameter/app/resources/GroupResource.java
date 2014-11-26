package com.UndefinedParameter.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.views.GroupView;

@Path("/orgs/{orgId}/{groupId}")
@Produces(MediaType.TEXT_HTML)
public class GroupResource {

	@GET
	public GroupView getGroupView(@PathParam("orgId") int orgId, @PathParam("groupId") int groupId) {
		return new GroupView(new Group(groupId, orgId, "My Group"));
	}
}
