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
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.views.GroupListView;
import com.UndefinedParameter.views.GroupMemberView;
import com.UndefinedParameter.views.GroupQuestionView;
import com.UndefinedParameter.views.GroupView;
import com.UndefinedParameter.views.GroupsView;
import com.UndefinedParameter.views.LoginView;


@Path("/group")
@Produces(MediaType.TEXT_HTML)
public class GroupResource {

	public GroupManager manager;
	public QuizManager quizManager;
	
	public GroupResource(OrganizationDAO orgsDAO, GroupDAO groupDAO, QuizDAO quizDAO, QuestionDAO questionDAO, QuizScoreDAO quizScoreDAO, OrgMemberDAO orgMemberDOA) {
		manager = new GroupManager(orgsDAO, groupDAO, orgMemberDOA);
		this.quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
	}
	
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
			//user is logged in, can display all information
			List<Quiz> userQuizzes = quizManager.findQuizzesByCreatorId(user.getId(), groupId);
			return Response.ok(new GroupView(group, organization, quizzes, userQuizzes, true, user, manager.findRegisteredGroups(user.getId()), questionCount)).build();
		}
		else {
			//user is not logged in, can't display edit or user quizzes
			return Response.ok(new GroupView(group, organization, quizzes, null, false, user, null, questionCount)).build();
		}
		
	}
	
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
	
	@GET
	@Path("/questions")
	public Response getGroupQuestions(@Auth(required = false) User user, @QueryParam("groupId") long groupId) {
		
		if(groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.ok(new GroupQuestionView(user, quizManager.findQuestionsByGroup(groupId), groupId, null)).build();
	}
	
	@GET
	@Path("/members")
	public Response getGroupMembers(@QueryParam("groupId") long groupId) {
		
		if(groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(new GroupMemberView(manager.findGroupMembers(groupId))).build();
	}
	
	@GET
	@Path("/top_groups")
	public Response getTopGroups() {
		return Response.ok(new GroupListView("../includes/top_groups.ftl", manager.findTopGroups())).build();
	}
	
	@GET
	@Path("mygroups")
	public Response getMyGroups(@QueryParam("userId") long userId) {
		return Response.ok(new GroupListView("../includes/mygroups.ftl", manager.findRegisteredGroups(userId))).build();
	}
}
