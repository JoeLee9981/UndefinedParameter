package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;
import java.util.List;

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

import com.UndefinedParameter.app.core.Group;
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
			return Response.ok(new OrgsView(manager.findAllOrganizationTypes(), manager.findUnregisteredOrgs(user.getId()), manager.findOrgsByUserId(user.getId()), manager.findNewestOrganizations(0, 15), manager.findLargestOrganizations(0, 15), user)).build();
		else
			return Response.ok(new OrgsView(manager.findAllOrganizationTypes(), manager.findOrgsByLocation("city"), null, manager.findNewestOrganizations(0, 15), manager.findLargestOrganizations(0, 15), null)).build();
	}
	
	@GET
	@Path("/org")
	public Response getOrganizationView(@Auth(required = false) User user, @QueryParam("id") int id) {
		if(user != null) {
			List<Group> unregGroups = manager.findUnregisteredGroupsByOrg(user.getId(), id);
			List<Group> regGroups = manager.findRegisteredGroupsById(id, user.getId());
			
			int userRating = 0;
			return Response.ok(new OrganizationView(manager.findOrgById(id), unregGroups, regGroups, true, user, userRating)).build();
		}
		else {
			List<Group> groups = manager.findGroupsById(id);
			return Response.ok(new OrganizationView(manager.findOrgById(id), groups, null, false, user, 0)).build();
		}
	}
	
	@GET
	@Path("/create") 
	public Response getCreateOrgView(@Auth(required = false) User user) {
		if(user != null)
			return Response.ok(new OrganizationCreatorView(user)).build();
		else
			return Response.ok(new LoginView("/orgs/create")).build();
	}
	
	@GET
	@Path("/org/create")
	public Response getCreateGroupView(@Auth(required = false) User user, @QueryParam("orgId") long orgId) {
		if(user != null)
			return Response.ok(new GroupCreatorView(manager.findOrgById(orgId))).build();
		else
			return Response.ok(new LoginView("/orgs/org/create?orgId=" + orgId)).build();
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
