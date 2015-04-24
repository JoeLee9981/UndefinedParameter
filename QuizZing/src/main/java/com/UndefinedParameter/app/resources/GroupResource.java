package com.UndefinedParameter.app.resources;


import io.dropwizard.auth.Auth;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserGroupManager;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.jdbi.UserGroupDAO;
import com.UndefinedParameter.views.GroupEditView;
import com.UndefinedParameter.views.GroupListView;
import com.UndefinedParameter.views.GroupMemberView;
import com.UndefinedParameter.views.GroupQuestionView;
import com.UndefinedParameter.views.GroupView;
import com.UndefinedParameter.views.GroupsView;
import com.UndefinedParameter.views.LoginView;

/**
 * 
 * This is the resource for all paths and subpaths of /group
 *
 */
@Path("/group")
@Produces(MediaType.TEXT_HTML)
public class GroupResource {

	/*** Manager objects ***/
	public GroupManager manager;
	public QuizManager quizManager;
	private UserGroupManager userGroupManager;
	
	/**
	 * Constructor
	 * @param orgsDAO
	 * @param groupDAO
	 * @param quizDAO
	 * @param questionDAO
	 * @param quizScoreDAO
	 * @param orgMemberDOA
	 * @param userGroupDAO
	 */
	public GroupResource(OrganizationDAO orgsDAO, GroupDAO groupDAO, QuizDAO quizDAO, QuestionDAO questionDAO, QuizScoreDAO quizScoreDAO, OrgMemberDAO orgMemberDOA, UserGroupDAO userGroupDAO) {
		manager = new GroupManager(orgsDAO, groupDAO, orgMemberDOA, userGroupDAO);
		userGroupManager = new UserGroupManager(userGroupDAO);
		this.quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
	}
	
	/**
	 * Adds a group into the database
	 * @param user
	 * @param group
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroup(@Auth(required = false) User user, @Valid Group group) {
			
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		long groupId = manager.addGroup(group, user);
		
		if(groupId >= 1) {
			response.put("response", "success");
			response.put("redirect", String.format("/group?groupId=%d", groupId));
		}
		else {
			response.put("response", "fail");
			response.put("message", "Unable to create group " + group.getName());
		}
		
		return Response.ok(response).build();
	}
	
	/**
	 * Returns the group view
	 * @param user
	 * @param groupId
	 * @return
	 */
	@GET
	public Response getGroupView(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		Group group = manager.findGroupById(groupId);
		
		//no group found, return bad request
		if(group == null)
			return Response.status(Status.BAD_REQUEST).build();
		
		Organization organization = manager.findParentOrganization(group.getOrganizationId());
		List<Quiz> quizzes = quizManager.findQuizzesByGroup(groupId);
		int questionCount = manager.countQuestionsByGroup(groupId);
		
		if(user != null) {
			boolean moderator = userGroupManager.findIfUserMod(user.getId(), groupId) || user.isAdmin();
			if(moderator) {
				group.setFlagCount(manager.countFlagsByGroup(groupId));
			}
			else {
				group.setFlagCount(manager.countFlagsByuser(user.getId(), groupId));
			}
			//user is logged in, can display all information
			List<Quiz> userQuizzes = quizManager.findQuizzesByCreatorId(user.getId(), groupId);
			return Response.ok(new GroupView(group, organization, quizzes, userQuizzes, true, user, manager.findRegisteredGroups(user.getId()), questionCount, moderator)).build();
		}
		else {
			//user is not logged in, can't display edit or user quizzes
			return Response.ok(new GroupView(group, organization, quizzes, null, false, user, null, questionCount, false)).build();
		}
		
	}
	
	/**
	 * Creates a view used to edit a group
	 * @param user
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/edit")
	public Response getGroupEditView(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null || groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Group group = manager.findGroupById(groupId);
		if(group == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Organization org = manager.findParentOrganization(group.getOrganizationId());
		if(user.isAdmin() || userGroupManager.findIfUserMod(user.getId(), groupId)) {
			return Response.ok(new GroupEditView(user, org, group)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
		
	}
	
	/**
	 * Posts edits made to a group and updates the database
	 * @param user
	 * @param groupId
	 * @param group
	 * @return
	 */
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editGroup(@Auth(required = false) User user, @QueryParam("groupId") long groupId, @Valid Group group) {
		
		if(user == null || group == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if(!user.isAdmin() && !userGroupManager.findIfUserMod(user.getId(), groupId)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Group existingGroup = manager.findGroupById(groupId);
		if(existingGroup == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if(manager.editGroup(group))
			return Response.ok().build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * Creates a view to display top groups
	 * @param user
	 * @return
	 */
	@GET
	@Path("/top")
	public Response getTopGroups(@Auth(required = false) User user) {
		if(user != null) {
			return Response.ok(new GroupsView(user, manager.findUnregisteredTopGroups(user.getId()), manager.findRegisteredGroups(user.getId()), true)).build();
		}
		else {
			return Response.ok(new GroupsView(user, manager.findTopGroups(), null, false)).build();
		}
	}
	
	/**
	 * gets a view showing all the questions in a group
	 * @param user
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/questions")
	public Response getGroupQuestions(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		boolean moderator = user != null && (userGroupManager.findIfUserMod(user.getId(), groupId) || user.isAdmin());
		
		return Response.ok(new GroupQuestionView(user, quizManager.findQuestionsByGroup(groupId), groupId, null, moderator)).build();
	}
	
	/**
	 * Gets a view showing all members in a group
	 * @param user
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/members")
	public Response getGroupMembers(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(new GroupMemberView(user, manager.findGroupMembers(groupId))).build();
	}
	
	/**
	 * Gets a view showing the top groups
	 * @return
	 */
	@GET
	@Path("/top_groups")
	public Response getTopGroups() {
		return Response.ok(new GroupListView("../includes/top_groups.ftl", manager.findTopGroups())).build();
	}
	
	/**
	 * Gets a view showing all groups a user is registered for
	 * @param userId
	 * @return
	 */
	@GET
	@Path("mygroups")
	public Response getMyGroups(@QueryParam("userId") long userId) {
		return Response.ok(new GroupListView("../includes/mygroups.ftl", manager.findRegisteredGroups(userId))).build();
	}
	
	/**
	 * Gets a veiw showing all questions flagged in a group
	 * @param user
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/flagged")
	public Response getFlaggedQuestions(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(user == null || groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<Question> questions;
		if(userGroupManager.findIfUserMod(user.getId(), groupId) || user.isAdmin()) {
			questions = quizManager.findFlaggedQuestionsByGroup(groupId);
		}
		else {
			questions = quizManager.findFlaggedQuestionsByUser(groupId, user.getId());
		}
		
		boolean moderator = user != null && (userGroupManager.findIfUserMod(user.getId(), groupId) || user.isAdmin());
		
		return Response.ok(new GroupQuestionView(user, questions, groupId, null, moderator)).build();
	}
	
	/**
	 * Gets a view showing all categories in a group
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupCategories(@QueryParam("groupId") long groupId) {
		if(groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<String> categories = manager.findCategoriesByGroup(groupId);
		return Response.ok(categories).build();
	}
}
