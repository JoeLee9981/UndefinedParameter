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
import com.UndefinedParameter.views.GroupCreatorView;
import com.UndefinedParameter.views.LoginView;
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
	public Response getOrganizationView(@Auth(required = false) User user, @QueryParam("id") int id) {
		if(user != null)
			return Response.ok(new OrganizationView(manager.findOrgById(id), manager.findGroupsById(id), manager.findRegisteredGroupsById(id, user.getId()), true)).build();
		else
			return Response.ok(new OrganizationView(manager.findOrgById(id), manager.findGroupsById(id), null, false)).build();
	}
	
	@GET
	@Path("/create") 
	public Response getCreateOrgView(@Auth(required = false) User user) {
		if(user != null)
			return Response.ok(new OrganizationCreatorView()).build();
		else
			return Response.ok(new LoginView()).build();
	}
	
	@GET
	@Path("/org/create")
	public Response getCreateGroupView(@Auth(required = false) User user, @QueryParam("orgId") long orgId) {
		if(user != null)
			return Response.ok(new GroupCreatorView(manager.findOrgById(orgId))).build();
		else
			return Response.ok(new LoginView()).build();
	}
	
	@POST
	@Path("/register")
	public Response registerOrg(@Auth(required = false) User user, @QueryParam("orgId") long orgId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if(orgId > 0) {
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
	public Response leaveOrg(@Auth(required = false) User user, @QueryParam("orgId") long orgId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if(orgId > 0) {
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
	public Response addOrg(@Auth(required = false) User user, Organization org) {
		
		if(user == null) {
			return Response.ok(new LoginView()).build();
		}
		
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
	
	@POST
	@Path("/org/register")
	public Response registerGroup(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if(groupId > 0) {
			if(manager.registerUserForGroup(groupId, user.getId())) {
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
	@Path("/org/leave")
	public Response leaveGroup(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if(groupId > 0 && user != null) {
			if(manager.removeuserFromGroupById(groupId, user.getId())) {
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
}
