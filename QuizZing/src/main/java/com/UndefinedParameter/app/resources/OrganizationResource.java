package com.UndefinedParameter.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpTester.Response;

import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.views.OrganizationView;
import com.UndefinedParameter.views.OrgsView;

@Path("/orgs")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class OrganizationResource {

	@GET
	public OrgsView getOrgsView() {
		return new OrgsView();
	}
	
	@GET
	@Path("/{id}")
	public OrganizationView getOrganizationView(@PathParam("id") int id) {
		return new OrganizationView(OrganizationManager.findOrgById(id));
	}
	
	@POST
	@Path("/add")
	public Response addOrg() {

			int a = 5;
			
		
		//if(group.getId() == 0 || group.getName() == null) {
			//TODO: Return a failure response
		//}
			
		//GroupManager.addGroup(group);
		return new Response();
	}
}
