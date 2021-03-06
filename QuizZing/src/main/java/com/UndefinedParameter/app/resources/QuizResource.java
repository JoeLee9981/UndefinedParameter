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
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.QuizScore;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserGroupManager;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.jdbi.UserGroupDAO;
import com.UndefinedParameter.views.CategoriesView;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuizCreatorView;
import com.UndefinedParameter.views.QuizEditQuestionsView;
import com.UndefinedParameter.views.QuizEditView;
import com.UndefinedParameter.views.QuizListView;
import com.UndefinedParameter.views.QuizView;
import com.UndefinedParameter.views.QuizzesView;
import com.UndefinedParameter.views.ScoreView;

@Path("/quiz")
@Produces(MediaType.TEXT_HTML)
public class QuizResource {
	
	private QuizManager quizManager;
	private OrganizationManager organizationManager;
	private GroupManager groupManager;
	private UserGroupManager userGroupManager;
	
	/**
	 * This class responds to all the GET and POST requests related to quizzes.
	 * 
	 * @param quizDAO
	 * @param questionDAO
	 * @param orgDAO
	 * @param groupDAO
	 * @param orgMemberDAO
	 * @param quizScoreDAO
	 * @param userGroupDAO
	 */
	public QuizResource(QuizDAO quizDAO, QuestionDAO questionDAO, OrganizationDAO orgDAO, GroupDAO groupDAO, OrgMemberDAO orgMemberDAO, QuizScoreDAO quizScoreDAO, UserGroupDAO userGroupDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
		groupManager = new GroupManager(orgDAO, groupDAO, orgMemberDAO, userGroupDAO);
		organizationManager = new OrganizationManager(orgDAO, groupDAO, orgMemberDAO);
		userGroupManager = new UserGroupManager(userGroupDAO);
	}
	
	/**
	 * Returns response and view to display quiz.
	 * 
	 * @param user
	 * @param id
	 * @param groupId
	 * @return response
	 */
	@GET
	public Response getQuizView(@Auth(required = false) User user, @QueryParam("quizId") long id, @QueryParam("groupId") long groupId) {
		
		//invalid id return bad request
		if(id < 1)
			return Response.status(Status.BAD_REQUEST).build();

		float userBestScore = -1.0f;
		
		//if user is not logged in
		if(user == null) {
			Quiz quiz = quizManager.getRandomizedQuestions(id);
			
			//return bad request if no quiz found
			if(quiz == null)
				return Response.status(Status.BAD_REQUEST).build();
			
			if(groupId < 1) {
				groupId = quiz.getParentGroupId();
			}
			return Response.ok(new QuizView(user, quiz, groupId, false, false, 0, 0, userBestScore)).build();
		}
		
		//obtain quiz with user ratings intact
		Quiz quiz = quizManager.getRandomizedQuestions(id, user.getId());
		
		//return bad request if no quiz found
		if(quiz == null)
			return Response.status(Status.BAD_REQUEST).build();
		if(groupId < 1) {
			groupId = quiz.getParentGroupId();
		}
		
		//obtain the user rating
		int userRating = quizManager.findUserRating(user.getId(), quiz.getQuizId());
		int userDiff = quizManager.findUserDifficulty(user.getId(), quiz.getQuizId());
		List<QuizScore> userScores = quizManager.findScoresByQuizAndUser(id, user.getId());
		
		for(QuizScore score : userScores) {
			if(score.getScore() > userBestScore)
				userBestScore = score.getScore();
		}
		
		//set editable to true if the user is the creator
		if(user != null && (user.getId() == quiz.getCreatorId() || user.isAdmin() || quiz.isOpen())) {
			//user is logged in and owner
			return Response.ok(new QuizView(user, quiz, groupId, true, true, userRating, userDiff, userBestScore)).build();
		}
		else {
			//user is logged in but not the owner
			return Response.ok(new QuizView(user, quiz, groupId, true, false, userRating, userDiff, userBestScore)).build();
		}
	}
	
	/**
	 * Commits new scores to database and returns all scores for this quiz.
	 * 
	 * @param user
	 * @param newScore
	 * @return response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuizView(@Auth(required = false) User user, @Valid QuizScore newScore) {
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if(quizManager.insertScore(newScore.getQuizId(), newScore.getUserId(), newScore.getScore())) {
			HashMap<String, List<QuizScore>> response = new HashMap<String, List<QuizScore>>();
			List<QuizScore> quizScores = quizManager.findScoresByQuiz(newScore.getQuizId());
			List<QuizScore>	userScores = quizManager.findScoresByQuizAndUser(newScore.getQuizId(), user.getId());
			
			response.put("quizScores", quizScores);
			response.put("userScores", userScores);
			return Response.ok(response).build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Returns response and view for creating a quiz.
	 * 
	 * @param user
	 * @param groupId
	 * @return response
	 */
	@GET
	@Path("/create")
	public Response getQuizCreatorView(@Auth(required = false) User user, @QueryParam("groupId") long groupId)
	{	
		if(user == null)
		{
			return Response.ok(new LoginView(user)).build();
		}
		else if(groupId > 0)
		{
			Group group = groupManager.findGroupById(groupId);
			List<String> categories = null;
			if(group != null)
				categories = groupManager.findCategoriesByGroup(group.getId());
			return Response.ok(new QuizCreatorView(user, organizationManager.findOrgsByUser(user), organizationManager.findRegisteredGroupsById(group.getOrganizationId(), user.getId()), group, categories)).build();
		}
		else
		{
			return Response.ok(new QuizCreatorView(user, organizationManager.findOrgsByUser(user), null, null, null)).build();
		}
	}
	
	/**
	 * Takes quiz information from creating a quiz, creates the quiz, and returns an appropriate response on success or fail.
	 * 
	 * @param user
	 * @param quiz
	 * @param groupId
	 * @param auto
	 * @param questionCount
	 * @param rating
	 * @param difficulty
	 * @return response
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createQuiz(@Auth(required = false) User user, @Valid Quiz quiz, @QueryParam("groupId") long groupId, 
			@QueryParam("auto") boolean auto, @QueryParam("questionCount") int questionCount, 
			@QueryParam("rating") int rating, @QueryParam("difficulty") int difficulty) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		//TODO: Find some of these instead of insert
		quiz.setCreatorId(user.getId());
		quiz.setDifficulty(3);
		quiz.setRating(3);
		//this is for future use
		quiz.setTime(0);
		
		long quizId = quizManager.createQuiz(quiz, groupId);
		if(quizId >= 1) {
			
			userGroupManager.addPoints(user.getId(), groupId, 5);
			
			if(quizManager.addQuizToGroup(quizId, groupId)) {
				if(auto) {
					quizManager.autoCreateQuiz(groupId, quizId, quiz.getCategories(), rating, difficulty, questionCount);
				}
				response.put("response", "success");
				response.put("redirect", "/quiz/edit?groupId=" + groupId + "&quizId=" + quizId);
			}
			else {
				response.put("response", "fail");
				response.put("message", "Unable to add the quiz to the group");
			}
		}
		else {
			response.put("response", "fail");
			response.put("message", "Unable to create the quiz");
		}
		return Response.ok(response).build();
	}
	
	/**
	 * Returns response and view for the quiz edit pages.
	 * 
	 * @param user
	 * @param id
	 * @param groupId
	 * @return response
	 */
	@GET
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editQuiz(@Auth(required = false) User user, @QueryParam("quizId") long id, @QueryParam("groupId") long groupId) {
		
		//invalid id
		if(id == 0)
			return Response.status(Status.BAD_REQUEST).build();
		//user must be logged in
		if(user == null)
			return Response.ok(new LoginView(user)).build();
		//find quiz
		Quiz quiz = quizManager.findQuiz(id);
		Group group = null;
		if(groupId > 0)
			group = groupManager.findGroupById(groupId);
		else
			group = groupManager.findGroupByQuizId(id);
		
		List<Question> unaddedGroupQuestions = quizManager.findUnaddedGroupQuestions(groupId, id);
		if(quiz != null && (user.getId() == quiz.getCreatorId() || quiz.isOpen() || user.isAdmin())) {
			return Response.ok(new QuizEditView(user, quiz, group, unaddedGroupQuestions)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}	
	
	/**
	 * Takes the new quiz name, commits the change, and returns an appropriate response on success or fail.
	 * 
	 * @param user
	 * @param quiz
	 * @return response
	 */
	@POST
	@Path("/edit/name")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editQuizName(@Auth(required = false) User user, @Valid Quiz quiz)
	{
		if(user == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		quizManager.editQuizName(quiz.getQuizId(), quiz.getName());
		HashMap<String, String> response = new HashMap<String, String>();

		return Response.ok(response).build();
	}
	
	/**
	 * Takes the new quiz description, commits the change, and returns an appropriate response on success or fail.
	 * 
	 * @param user
	 * @param quiz
	 * @return response
	 */
	@POST
	@Path("/edit/description")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editQuizDescription(@Auth(required = false) User user, @Valid Quiz quiz)
	{
		if(user == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		quizManager.editQuizDescription(quiz.getQuizId(), quiz.getDescription());
		HashMap<String, String> response = new HashMap<String, String>();

		return Response.ok(response).build();
	}
	
	/**
	 * Commits all quiz changes to the database and returns an appropriate response.
	 * 
	 * @param user
	 * @param quiz
	 * @return response
	 */
	@POST
	@Path("/edit/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveQuiz(@Auth(required = false) User user, @Valid Quiz quiz)
	{
		if(user == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		quizManager.saveQuiz(quiz.getQuizId(), quiz.getName(), quiz.getDescription(), quiz.isOpen(), quiz.getTime());
		HashMap<String, String> response = new HashMap<String, String>();

		return Response.ok(response).build();
	}
	
	/**
	 * Returns response and view to edit quiz questions.
	 * 
	 * @param user
	 * @param quizId
	 * @return response
	 */
	@GET
	@Path("/edit/questions")
	public Response getQuizQuestions(@Auth(required = false) User user, @QueryParam("quizId") long quizId) {
		
		if(quizId == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		Quiz quiz = quizManager.findQuiz(quizId);
		
		if(quiz != null && (user.getId() == quiz.getCreatorId() || quiz.isOpen() || user.isAdmin())) {
			return Response.ok(new QuizEditQuestionsView(quiz)).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * Retrieves top quizzes to display on home page.
	 * 
	 * @param user
	 * @return response
	 */
	@GET
	@Path("/quizzes")
	public Response getQuizzesPage(@Auth(required = false) User user) {
		if(user != null) {
			return Response.ok(new QuizzesView(user, quizManager.findTopQuizzes(), quizManager.findQuizzesByCreatorId(user.getId()))).build();
		}
		else {
			return Response.ok(new QuizzesView(user, quizManager.findTopQuizzes(), null)).build();
		}
	}
	
	/**
	 * Commits quiz quality rating to the database.
	 * 
	 * @param user
	 * @param quizId
	 * @param rating
	 * @param groupId
	 * @return response
	 */
	@POST
	@Path("/rate/rating")
	public Response rateQuizQuality(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("rating") int rating, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if(quizManager.rateQuizQuality(user.getId(), quizId, groupId, rating)) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Commits quiz difficulty rating to the database.
	 * 
	 * @param user
	 * @param quizId
	 * @param rating
	 * @param groupId
	 * @return response
	 */
	@POST
	@Path("/rate/difficulty")
	public Response rateQuizDifficulty(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("rating") int rating, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if(quizManager.rateQuizDifficulty(user.getId(), quizId, groupId, rating)) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Retrieves top quizzes for display on home page.
	 * 
	 * @return response
	 */
	@GET
	@Path("/top")
	public Response getTopQuizzes() {
		return Response.ok(new QuizListView("../includes/top_quizzes.ftl", quizManager.findTopQuizzes())).build();
	}
	
	/**
	 * Retrieves recent quizzes for display on home pages.
	 * 
	 * @return response
	 */
	@GET
	@Path("/recent")
	public Response getRecentQuizzes() {
		return Response.ok(new QuizListView("../includes/recent_quizzes.ftl", quizManager.findRecentQuizzes())).build();
	}
	
	/**
	 * Retrieves user-owned quizzes for display on home pages.
	 * 
	 * @return response
	 */
	@GET
	@Path("/myquizzes")
	public Response getRecentQuizzes(@QueryParam("userId") long userId) {
		List<Quiz> quizzes = quizManager.findQuizzesByCreatorId(userId);
		return Response.ok(new QuizListView("../includes/myquizzes.ftl", quizzes)).build();
	}
	
	/**
	 * Retrieves category search view for display on home pages.
	 * 
	 * @return response
	 */
	@GET
	@Path("/categories")
	public Response getCategoryView(@Auth(required = false) User user) {
		List<String> categories = quizManager.getAllCategories();
		return Response.ok(new CategoriesView(user, categories)).build();
	}
	
	/**
	 * Retrieves quizzes by user-selected categories for display on category page.
	 * 
	 * @return response
	 */
	@POST
	@Path("/categories")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCategoryQuizzes(List<String> categories) {
		return Response.ok(new QuizListView("../includes/quiz_list.ftl", quizManager.getQuizzesFromCategories(categories))).build();
	}
	
	/**
	 * Retrieves top categories for display on category page.
	 * 
	 * @return response
	 */
	@GET
	@Path("/top_categories")
	public Response getTopCategoryView() {
		List<String> categories = quizManager.getTopCategories();
		return Response.ok(new CategoriesView("../includes/top_categories.ftl", categories)).build();
	}
	
	/**
	 * Retrieves quizzes by user-selected categories for display on home pages.
	 * 
	 * @return response
	 */
	@POST
	@Path("/top_categories")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTopCategoryQuizzes(List<String> categories) {
		return Response.ok(new QuizListView("../includes/quiz_list.ftl", quizManager.getQuizzesFromCategories(categories))).build();
	}
 }