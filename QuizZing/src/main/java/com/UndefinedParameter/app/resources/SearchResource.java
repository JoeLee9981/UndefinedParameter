package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.ArrayList;
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
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.QuizScore;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserManager;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.jdbi.UserDAO;
import com.UndefinedParameter.jdbi.UserGroupDAO;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.ScoreView;
import com.UndefinedParameter.views.SearchView;
import com.UndefinedParameter.views.UserProfileView;

@Path("/search")
@Produces(MediaType.TEXT_HTML)
public class SearchResource {
	
	public UserManager userManager;
	public QuizManager quizManager;
	public GroupManager groupManager;
	public OrganizationManager orgManager;
	
	public SearchResource(UserDAO userdao, QuizDAO quizdao, QuestionDAO questiondao, OrganizationDAO orgdao, GroupDAO groupdao, QuizScoreDAO quizScoredao, OrgMemberDAO orgMemberDAO, UserGroupDAO userGroupDAO)
	{
		this.userManager = new UserManager(userdao);
		this.quizManager = new QuizManager(quizdao, questiondao, quizScoredao);
		this.groupManager = new GroupManager(orgdao, groupdao, orgMemberDAO, userGroupDAO);
		this.orgManager = new OrganizationManager(orgdao, groupdao, orgMemberDAO);
	}
	
	
	/*
	 * Retrieves the search page
	 */
	@GET
	public Response getSearchView(@Auth(required = false) User user, @QueryParam("keywords") String keywords, @QueryParam("dest") String destination) {
		
		// Find the organization which match the keyword
		List<Organization> orgResults = orgManager.findOrgssByKeywords(keywords);
		List<Quiz> quizResults = quizManager.findQuizByKeywords(keywords);
		List<Group> groupResults = groupManager.findGroupsByKeywords(keywords);
		 
		return Response.ok(new SearchView(user, keywords, orgResults, quizResults, groupResults, destination)).build();
		
	}

}
