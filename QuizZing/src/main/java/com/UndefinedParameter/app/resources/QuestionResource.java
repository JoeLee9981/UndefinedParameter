package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserGroupManager;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.jdbi.UserGroupDAO;
import com.UndefinedParameter.views.GroupQuestionView;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.QuestionAddView;
import com.UndefinedParameter.views.QuestionCreatorView;
import com.UndefinedParameter.views.QuestionEditView;
import com.UndefinedParameter.views.QuizEditQuestionsView;

/**
 * This is the resource used to handle all paths and subpaths related to questions
 *
 */
@Path("/question")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource {
	
	//managers related to objects
	private QuizManager quizManager;
	private UserGroupManager userGroupManager;
	
	//constructors
	public QuestionResource(QuizDAO quizDAO, QuestionDAO questionDAO, QuizScoreDAO quizScoreDAO, UserGroupDAO userGroupDAO) {
		quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
		userGroupManager = new UserGroupManager(userGroupDAO);
	}
	
	/**
	 * gets a view to create a question
	 * @param user
	 * @param quizId
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/create")
	public Response getAddQuestionView(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		return Response.ok(new QuestionCreatorView(user, quizId, groupId)).build();
	}
	
	/**
	 * Gets a view to create a question based on the type of the question
	 * @param user
	 * @param quizId
	 * @param groupId
	 * @param type
	 * @return
	 */
	@GET
	@Path("/create/type")
	public Response getCreateQuestionType(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId, @QueryParam("type") String type) {
		
		if(user == null) {
			return Response.ok(new LoginView("/question/create?quizId=" + quizId + "&groupId=" + groupId)).build();
		}
		
		if(quizId < 1 || groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		String file = "../includes/multiple_choice.ftl";
		if("TRUE_FALSE".equals(type)) {
			file = "../includes/true_false.ftl";
		}
		else if("SHORT_ANSWER".equals(type)) {
			file = "../includes/short_answer.ftl";
		}
		else if("FILL_IN_THE_BLANK".equals(type)) {
			file = "../includes/fill_blank.ftl";
		}
		else if("MATCHING".equals(type)) {
			file = "../includes/matching.ftl";
		}
		return Response.ok(new QuestionCreatorView(file, user, quizId, groupId)).build();
	}
	
	/**
	 * post to create a question and add to a database
	 * @param user
	 * @param quizId
	 * @param question
	 * @return
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creatQuestionForQuiz(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @Valid Question question) {
		//User must be logged in to perform this
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if(question == null || question.getGroupId() < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		
		try {
			question.setRating(3.0);
			question.setDifficulty(3.0);
			long questionId = quizManager.createQuestion(question);
			if(questionId > 0 && quizManager.addQuestionToQuiz(quizId, questionId)) {
				
				userGroupManager.addPoints(user.getId(), question.getGroupId(), 1);
				response.put("response", "success");
				response.put("redirect", "/quiz/edit?quizId=" + quizId + "&groupId=" + question.getGroupId());
				response.put("questionId", "" + questionId);
				response.put("textFirstLine", question.getQuestionTextFirstLine());
				response.put("text", question.getQuestionText());
				response.put("categoryString", question.getCategoriesString());
				response.put("rating", "" + question.getRating());
				response.put("difficulty", "" + question.getDifficulty());
			}
			else {
				response.put("response", "fail");
				response.put("message", "Unable to create your question.");
			}
			return Response.ok(response).build();
		}
		catch(Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	/**
	 * gets a view used to add a question
	 * @param user
	 * @param quizId
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/add")
	public Response getQuestionAddView(@Auth(required = false) User user, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		else {
			Quiz quiz = quizManager.findQuiz(quizId);
			List<Question> questions = quizManager.findUnaddedGroupQuestions(groupId, quizId);
			//TODO: Moderators can access this
			if(quiz != null && (quiz.getCreatorId() == user.getId() || quiz.isOpen() || user.isAdmin())) {
				return Response.ok(new QuestionAddView(user, questions, quizId, groupId)).build();
			}
			else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
	}
	
	/**
	 * Post to add a question and stash into the database
	 * @param user
	 * @param questionId
	 * @param quizId
	 * @param groupId
	 * @return
	 */
	@POST
	@Path("/add")
	public Response addQuestion(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("quizId") long quizId, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			String url = "edit?groupId=" + groupId + "&quizId=" + quizId;
			return Response.ok(new LoginView(url)).build();
		}
		if(quizId > 0) {
			Quiz quiz = quizManager.findQuiz(quizId);
			if(quiz != null && (quiz.getCreatorId() == user.getId() || quiz.isOpen() || user.isAdmin())) {
				if(quizManager.addQuestionToQuiz(quizId, questionId)) {
					return Response.ok().build();
				}
			}
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * Removes an association question to a quiz
	 * @param user
	 * @param questionId
	 * @param quizId
	 * @return
	 */
	@DELETE
	@Path("/remove")
	public Response removeQuestion(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("quizId") long quizId) {
		
		if(user == null) {
			return Response.ok(new LoginView(user)).build();
		}
		
		if(questionId == 0 || quizId == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Quiz quiz = quizManager.findQuiz(quizId);
		if(quiz != null && (quiz.getCreatorId() == user.getId() || quiz.isOpen() || user.isAdmin())) {
			//remove and return ok
			if(quizManager.removeQuestionFromQuiz(quizId, questionId)) {
				//reload the quiz now
				quiz = quizManager.findQuiz(quizId);
				return Response.ok(new QuizEditQuestionsView(quiz)).build();
			}
		}
		//removal failed
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * post a rating onto a question
	 * @param user
	 * @param questionId
	 * @param rating
	 * @param groupId
	 * @return
	 */
	@POST
	@Path("/rate/rating")
	public Response rateQuizQuality(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("rating") int rating, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if(quizManager.rateQuestionQuality(user.getId(), questionId, groupId, rating)) {
			userGroupManager.addPoints(user.getId(), groupId, 2);
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	/**
	 * add a difficulty onto a question in the database
	 * @param user
	 * @param questionId
	 * @param rating
	 * @param groupId
	 * @return
	 */
	@POST
	@Path("/rate/difficulty")
	public Response rateQuizDifficulty(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("rating") int rating, @QueryParam("groupId") long groupId) {
		
		if(user == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if(quizManager.rateQuestionDifficulty(user.getId(), questionId, groupId, rating)) {
			userGroupManager.addPoints(user.getId(), groupId, 4);
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	/**
	 * get an edit view to edit a quiz
	 * @param user
	 * @param questionId
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/edit")
	public Response getQuizEdit(@Auth(required = false) User user, @QueryParam("questionId") long questionId, @QueryParam("groupId") long groupId) {
		if(user == null || questionId < 1) {
			//user may not be null for editing
			return Response.status(Status.BAD_REQUEST).build();
		}
		Question question = quizManager.findQuestionById(questionId);
		//TODO: Moderators also have access here
		if(question != null && (user.getId() == question.getCreatorId() || user.isAdmin())) {
			return Response.ok(new QuestionEditView(question, groupId, quizManager.getAllCategories())).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * PUT to update a quiz and save changes into the db
	 * @param user
	 * @param groupId
	 * @param question
	 * @return
	 */
	@PUT
	@Path("edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editQuiz(@Auth(required = false) User user, @QueryParam("groupId") long groupId, @Valid Question question) {
		
		if(user == null || groupId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Question existingQuestion = quizManager.findQuestionById(question.getQuestionId());
		
		if(existingQuestion != null && (user.getId() == question.getCreatorId() || user.isAdmin() || userGroupManager.findIfUserMod(user.getId(), groupId))) {
			if(quizManager.updateQuestion(question))
				return Response.ok(new GroupQuestionView(user, quizManager.findQuestionsByGroup(groupId), groupId, "Question has been updated.", true)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	/**
	 * Post to add a flag on a question to flag as wrong
	 * @param user
	 * @param questionId
	 * @param reason
	 * @return
	 */
	@POST
	@Path("/flag")
	public Response flagQuestion(@Auth(required = false) User user, @QueryParam("questionId") long questionId, String reason) {
		
		if(questionId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		quizManager.flagQuestion(questionId, reason);
		return Response.ok().build();
	}
	
	/**
	 * Post to remove a flag from a question and update in the database
	 * @param user
	 * @param questionId
	 * @return
	 */
	@POST
	@Path("/unflag")
	public Response unflagQuestion(@Auth(required = false) User user, @QueryParam("questionId") long questionId) {
		if(user == null || questionId < 1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		quizManager.unflagQuestion(questionId);
		return Response.ok().build();
	}
	
}
