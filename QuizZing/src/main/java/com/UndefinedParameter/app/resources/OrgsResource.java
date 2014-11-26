package com.UndefinedParameter.app.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.views.OrgsView;

@Path("/orgs")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class OrgsResource {

	@GET
	public OrgsView getOrgsView() {
		return new OrgsView();
	}
	
	@POST
	public void addOrg(@Valid Organization org) {
		//if(group.getId() == 0 || group.getName() == null) {
			//TODO: Return a failure response
		//}
			
		//GroupManager.addGroup(group);
		//return new Response();
	}
}
