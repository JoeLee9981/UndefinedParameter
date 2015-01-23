package com.UndefinedParameter.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.views.OrganizationCreatorView;
import com.UndefinedParameter.views.OrganizationView;
import com.UndefinedParameter.views.OrgsView;

@Path("/orgs")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class OrganizationResource {

	private OrganizationManager manager;
	
	public OrganizationResource(OrganizationDAO orgDAO) {
		manager = new OrganizationManager(orgDAO);
	}
	
	@GET
	public OrgsView getOrgsView() {
		return new OrgsView(manager.findOrgsByLocation("city"));
	}
	
	@GET
	@Path("/org")
	public OrganizationView getOrganizationView(@QueryParam("id") int id) {
		return new OrganizationView(manager.findOrgById(id), manager.findGroupsById(id));
	}
	
	@GET
	@Path("/create") 
	public Response getCreateOrgView() {
		
		return Response.ok(new OrganizationCreatorView()).build();
	}
	
	@POST
	@Path("/add")
	public Response addOrg() {

			int a = 5;
			
		
		//if(group.getId() == 0 || group.getName() == null) {
			//TODO: Return a failure response
		//}
			
		//GroupManager.addGroup(group);
		return Response.ok().build();
	}
}
