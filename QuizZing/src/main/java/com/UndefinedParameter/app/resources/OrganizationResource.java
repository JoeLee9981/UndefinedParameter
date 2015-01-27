package com.UndefinedParameter.app.resources;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.views.OrganizationCreatorView;
import com.UndefinedParameter.views.OrganizationView;
import com.UndefinedParameter.views.OrgsView;

@Path("/orgs")
@Produces(MediaType.TEXT_HTML)
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrg(Organization org) {
		
		HashMap<String, String> response = new HashMap<String, String>();
		long id = manager.createOrganization(org);
		
		if(id > 0) {
			response.put("response", "success");
			response.put("redirect", String.format("/orgs/org?id=%d", id));
		}
		else {
			response.put("response", "error");
			response.put("message", "Unable to create your organization.");
		}
		return Response.ok(response).build();
	}
}
