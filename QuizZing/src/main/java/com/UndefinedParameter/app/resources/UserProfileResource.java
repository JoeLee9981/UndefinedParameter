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

import org.apache.commons.lang.StringUtils;

import com.UndefinedParameter.app.core.CategoryScore;
import com.UndefinedParameter.app.core.GroupManager;
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
import com.UndefinedParameter.views.ScoreView;
import com.UndefinedParameter.views.UserProfileView;

@Path("/user")
@Produces(MediaType.TEXT_HTML)
public class UserProfileResource {
	
	public UserManager userManager;
	public QuizManager quizManager;
	public GroupManager groupManager;
	
	/**
	 * This class handles all the GET and POST requests to the user profile-related pages.
	 * 
	 * @param userdao
	 * @param quizdao
	 * @param questiondao
	 * @param orgdao
	 * @param groupdao
	 * @param quizScoredao
	 * @param orgMemberDAO
	 * @param userGroupDAO
	 */
	public UserProfileResource(UserDAO userdao, QuizDAO quizdao, QuestionDAO questiondao, OrganizationDAO orgdao, GroupDAO groupdao, QuizScoreDAO quizScoredao, OrgMemberDAO orgMemberDAO, UserGroupDAO userGroupDAO)
	{
		this.userManager = new UserManager(userdao);
		this.quizManager = new QuizManager(quizdao, questiondao, quizScoredao);
		this.groupManager = new GroupManager(orgdao, groupdao, orgMemberDAO, userGroupDAO);
	}

	/*
	 * Retrieves user profile page, returning a unique view based on whether 
	 * it's the user's profile or not.
	 */
	@GET
	public Response getUserProfileView(@Auth(required = false) User user, @QueryParam("userid") long userID, @QueryParam("tab") String tab) {
		
		if(user != null && user.getId() == userID) {
			return Response.ok(new UserProfileView("profile.ftl", user, user, quizManager.findQuizzesByCreatorId(user.getId()), 
								groupManager.findRegisteredGroups(user.getId()), true, userManager.getBadgesByUser(userID), userManager.getBadgesByOrg(userID),
								userManager.getSentMessages(userID), userManager.getReceivedMessages(userID), tab)).build();
		}
		else {
			User currentUser = userManager.findUserById(userID);
			return Response.ok(new UserProfileView("profile.ftl", currentUser, user, quizManager.findQuizzesByCreatorId(currentUser.getId()), 
								groupManager.findRegisteredGroups(userID), false, userManager.getBadgesByUser(userID), userManager.getBadgesByOrg(userID),
								null, null)).build();
		}
	}
	
	/**
	 * Returns the edit profile view for the current user.
	 * 
	 * @param user
	 * @param userid
	 * @return response
	 */
	@GET
	@Path("/edit")
	public Response getProfileEditView(@Auth(required = false) User user, @QueryParam("userid") long userid) {
	
		if(userid < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if(user == null || user.getId() != userid) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		return Response.ok(new UserProfileView("edit_profile.ftl", user, user, quizManager.findQuizzesByCreatorId(user.getId()), groupManager.findRegisteredGroups(user.getId()), false, null, null, null, null)).build();
	}
	
	/**
	 * Takes the new user profile information and commits it to the database.
	 * 
	 * @param user
	 * @param updateduser
	 * @return response
	 */
	@POST
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(@Auth(required = false) User user, @Valid User updateduser) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();

		try {
			if(userManager.updateUser(updateduser)) {
				response.put("response", "success");
				return Response.ok(response).build();
			}
			else {
				response.put("response", "error");
				response.put("message", "User update failed.");
				return Response.ok(response).build();
			}
			
		}
		catch(Exception e) {
			//TODO: return error message
			return Response.status(500).build();
		}
		
	}
	
	/**
	 * Retrieves the score view for the current logged-in user.
	 * 
	 * @param user
	 * @param userid
	 * @return response
	 */
	@GET
	@Path("/scores")
	public Response getUserScoresView(@Auth(required = false) User user, @QueryParam("userid") long userid) {
		List<String> bestCategory = new ArrayList<String>();
		long bestCategoryQuizId = 0;
		float averageScore = 0.0f;
		
		if(userid < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		List<QuizScore> userScores = quizManager.findScoresByUser(user.getId());
		if(userScores != null) {
			int count = 0;
			float scoresSum = 0.0f;
			float bestScore = 0.0f;
			
			for(QuizScore score : userScores) {
				// Average scores
				scoresSum += score.getScore();
				count ++;
				
				// Find highest score
				if(score.getScore() > bestScore) {
					bestScore = score.getScore();
					bestCategoryQuizId = score.getQuizId();
				}
			}
			
			if(scoresSum <= 0)
				averageScore = 0.0f;
			else
				averageScore = scoresSum / (float)count;
			
			// Find categories for best quiz
			bestCategory = quizManager.getQuestionCategoriesViaQuizID(bestCategoryQuizId);
			if(bestCategory.isEmpty())
				bestCategory.add("Just for fun");
		}
		
		return Response.ok(new ScoreView("score.ftl", user, quizManager.findQuizzesParticipated(user.getId()), averageScore, bestCategory)).build();
	}
	
	/**
	 * Retrieves the quiz and score information for the selected quiz id and returns the data for display.
	 * 
	 * @param user
	 * @param quizId
	 * @return response
	 */
	@POST
	@Path("/scores")
	@Produces(MediaType.APPLICATION_JSON)
	public Response scores(@Auth(required = false) User user, @QueryParam("quizid") long quizId) {
		
		if(user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		HashMap<String, List<QuizScore>> response = new HashMap<String, List<QuizScore>>();
		List<QuizScore> userScores = quizManager.findScoresByQuizAndUser(quizId, user.getId());

		try {
			if(userScores != null || userScores.size() > 0) {
				response.put("scores", userScores);
				return Response.ok(response).build();
			}	
			else {
				return Response.ok(response).build();
			}
			
		}
		catch(Exception e) {
			//TODO: return error message
			return Response.status(500).build();
		}
		
	}
	
	/**
	 * Retrieves category information from a quiz id and returns it for display.
	 * 
	 * @param user
	 * @param quizId
	 * @return response
	 */
	@GET
	@Path("/scores/category")
	@Produces(MediaType.APPLICATION_JSON)
	public Response categoryScores(@Auth(required = false) User user, @QueryParam("quizId") long quizId) {
		
		HashMap<String, List<CategoryScore>> response = new HashMap<String, List<CategoryScore>>();
		if(user == null || quizId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		List<CategoryScore> scores = quizManager.getCategoryScoresByQuizId(quizId, user.getId());
		
		if(scores != null) {
			response.put("categories", scores);
		}
		
		return Response.ok(response).build();
	}
	
	/**
	 * Takes the information to form a message and send it to the intended recipient.
	 * 
	 * @param user
	 * @param sendeeId
	 * @param senderId
	 * @param message
	 * @return response
	 */
	@POST
	@Path("/message")
	public Response sendMessages(@Auth(required = false) User user, @QueryParam("sendeeId") long sendeeId, @QueryParam("senderId") long senderId, String message) {
		
		if(user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		//send message
		long messageId = userManager.sendMessage(sendeeId, senderId, message);
		if(messageId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	/**
	 * Returns view for specific message id.
	 * 
	 * @param user
	 * @param messageId
	 * @return response
	 */
	@POST
	@Path("/message/view")
	public Response viewMessage(@Auth(required = false) User user, @QueryParam("messageId") long messageId) {
		if(user == null || messageId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if(userManager.setMessageViewed(messageId))
			return Response.ok().build();
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * Display message count.
	 * 
	 * @param user
	 * @return response
	 */
	@GET
	@Path("message/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnreadCount(@Auth(required = false) User user) {
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		if(user == null) {
			response.put("results", 0);
			return Response.ok(response).build();
		}
		response.put("results", userManager.getUnreadMessageCount(user.getId()));
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/message/delete")
	public Response deleteMessage(@Auth(required = false) User user, @QueryParam("messageId") long messageId, @QueryParam("type") String type) {
		if(user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if("SENDER".equals(type)) {
			userManager.senderDeleteMessage(messageId);
			return Response.ok().build();
		}
		else if("SENDEE".equals(type)) {
			userManager.sendeeDeleteMessage(messageId);
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
}
