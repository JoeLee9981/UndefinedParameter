package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.views.OrganizationCreatorView;
import com.UndefinedParameter.views.OrganizationView;
import com.UndefinedParameter.views.OrgsView;

@Path("/orgs")
@Produces(MediaType.TEXT_HTML)
public class OrganizationResource {

	private OrganizationManager manager;
	
	public OrganizationResource(OrganizationDAO orgDAO, GroupDAO groupDAO) {
		manager = new OrganizationManager(orgDAO, groupDAO);
	}
	
	@GET
	public Response getOrgsView(@Auth(required=false) User user) {
		if(user != null)
			return Response.ok(new OrgsView(manager.findOrgsByLocation("city"), manager.findOrgsByUserId(user.getId()), user)).build();
		else
			return Response.ok(new OrgsView(manager.findOrgsByLocation("city"), null, null)).build();
	}
	
	@GET
	@Path("/org")
	public Response getOrganizationView(@QueryParam("id") int id) {
		return Response.ok(new OrganizationView(manager.findOrgById(id), manager.findGroupsById(id))).build();
	}
	
	@GET
	@Path("/create") 
	public Response getCreateOrgView() {
		
		return Response.ok(new OrganizationCreatorView()).build();
	}
	
	@POST
	@Path("/register")
	public Response registerOrg(@Auth User user, @QueryParam("orgId") long orgId) {
		if(orgId > 0 && user != null) {
			if(manager.registerOrganization(orgId, user.getId())) {
				return Response.ok().build();
			}
			else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/leave")
	public Response leaveOrg(@Auth User user, @QueryParam("orgId") long orgId) {
		if(orgId > 0 && user != null) {
			if(manager.leaveOrganization(orgId, user.getId())) {
				return Response.ok().build();
			}
			else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
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
