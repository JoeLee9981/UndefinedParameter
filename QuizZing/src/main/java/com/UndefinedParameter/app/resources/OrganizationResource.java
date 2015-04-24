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
import com.UndefinedParameter.app.core.OrgMember;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.views.GroupCreatorView;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.OrgEditView;
import com.UndefinedParameter.views.OrganizationCreatorView;
import com.UndefinedParameter.views.OrganizationView;
import com.UndefinedParameter.views.OrgsView;

/**
 * This is the resorce responsible for any path and sub
 * 	path related to organizatoins
 *
 */
@Path("/orgs")
@Produces(MediaType.TEXT_HTML)
public class OrganizationResource {

	//manager
	private OrganizationManager manager;
	
	//constructor
	public OrganizationResource(OrganizationDAO orgDAO, GroupDAO groupDAO, OrgMemberDAO orgMemberDAO) {
		manager = new OrganizationManager(orgDAO, groupDAO, orgMemberDAO);
	}
	
	/**
	 * Returns the main organizations view (notice the plural)
	 * @param user
	 * @return
	 */
	@GET
	public Response getOrgsView(@Auth(required=false) User user) {
		if(user != null)
			return Response.ok(new OrgsView(manager.findAllOrganizationTypes(), manager.findUnregisteredOrgs(user.getId()), manager.findOrgsByUserId(user.getId()), manager.findNewestOrganizations(0, 10), manager.findLargestOrganizations(0, 10), user)).build();
		else
			return Response.ok(new OrgsView(manager.findAllOrganizationTypes(), manager.findOrgsByLocation("city"), null, manager.findNewestOrganizations(0, 10), manager.findLargestOrganizations(0, 10), null)).build();
	}
	
	/**
	 * Creates the view for a specific organization view (single)
	 * @param user
	 * @param id
	 * @return
	 */
	@GET
	@Path("/org")
	public Response getOrganizationView(@Auth(required = false) User user, @QueryParam("id") int id) {
		if(id <= 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		//get a list of organization members
		List<OrgMember> members = manager.getMemberList(id);
		
		if(user != null) {
			boolean moderator = manager.getModStatus(id, user.getId()) || user.isAdmin();
			List<Group> unregGroups = manager.findUnregisteredGroupsByOrg(user.getId(), id);
			List<Group> regGroups = manager.findRegisteredGroupsById(id, user.getId());
			
			int userRating = 0;
			return Response.ok(new OrganizationView(manager.findOrgById(id), manager.findOrgsByUserId(user.getId()), unregGroups, regGroups, members, manager.findQuizzesByOrg(id), true, user, userRating, moderator)).build();
		}
		else {
			List<Group> groups = manager.findGroupsById(id);
			return Response.ok(new OrganizationView(manager.findOrgById(id), null, groups, null, members, manager.findQuizzesByOrg(id), false, user, 0, false)).build();
		}
	}
	
	/**
	 * Gets a view for the creation of an organization
	 * @param user
	 * @return
	 */
	@GET
	@Path("/create") 
	public Response getCreateOrgView(@Auth(required = false) User user) {
		if(user != null)
			return Response.ok(new OrganizationCreatorView(user)).build();
		else
			return Response.ok(new LoginView("/orgs/create")).build();
	}
	
	/**
	 * Gets a view for the creation of a group in an organization
	 * @param user
	 * @param orgId
	 * @return
	 */
	@GET
	@Path("/org/create")
	public Response getCreateGroupView(@Auth(required = false) User user, @QueryParam("orgId") long orgId) {
		if(user != null)
			return Response.ok(new GroupCreatorView(user, manager.findOrgById(orgId))).build();
		else
			return Response.ok(new LoginView("/orgs/org/create?orgId=" + orgId)).build();
	}
	
	/**
	 * Creates a view for the editing of an org
	 * @param user
	 * @param orgId
	 * @return
	 */
	@GET
	@Path("/org/edit")
	public Response getEditOrgView(@Auth(required = false) User user, @QueryParam("orgId") long orgId) {
		if(user == null) {
			return Response.ok(new LoginView("/orgs/org/edit?orgId=" + orgId)).build();
		}
		if(orgId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Organization org = manager.findOrgById(orgId);
		if(manager.getModStatus(orgId, user.getId()) || user.isAdmin()) {
			
			return Response.ok(new OrgEditView(user, org)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * Posts edited changes of an organization and saves them in the database
	 * @param user
	 * @param orgId
	 * @param organization
	 * @return
	 */
	@POST
	@Path("/org/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editOrg(@Auth(required = false) User user, @QueryParam("orgId") long orgId, Organization organization) {
		if(user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if(!user.isAdmin() && !manager.getModStatus(orgId, user.getId())) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		manager.updateOrganization(organization);
		
		return Response.ok().build();
	}
	
	/**
	 * Posts the registration of a user to an organization
	 * @param user
	 * @param orgId
	 * @return
	 */
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
	
	/**
	 * Returns a JSON list of groups that a user has joined
	 * @param user
	 * @param orgId
	 * @return
	 */
	@POST
	@Path("/getJoinedGroups")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJoinedGroupsInOrganization(@Auth(required = false) User user, @QueryParam("orgId") long orgId) 
	{	
		HashMap<String, String> response = new HashMap<String, String>();
		
		// Get the list of joined groups in the specified organization
		List<Group> regGroups = manager.findRegisteredGroupsById(orgId, user.getId());
		
		for (Group group:regGroups)
		{
			response.put(group.getName(), "" + group.getId());
		}
		
		return Response.ok(response).build();
	}
	
	/**
	 * Performs a delete removing the user registration from an organization
	 * @param user
	 * @param orgId
	 * @return
	 */
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
	
	/**
	 * Creates a new organization and stashes in the database
	 * @param user
	 * @param org
	 * @return
	 */
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrg(@Auth(required = false) User user, Organization org) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
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
	
	/**
	 * Registers a user for the org and saves in the database
	 * @param user
	 * @param groupId
	 * @return
	 */
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
	
	/**
	 * Removes the registration of a user to an organization from the database
	 * @param user
	 * @param groupId
	 * @return
	 */
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
