package com.UndefinedParameter.app.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
//import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.quizzing.QuizZingApplication;

/*
 * Class is responsible for managing of quizzes
 * 	Contains methods for creating, editing, removing
 * 	quizzes. Functions as a model layer over the database.
 */
public class QuizManager {
	
	private QuizDAO quizDAO;
	private QuestionDAO questionDAO;
	private QuizScoreDAO quizScoreDAO;
	
	public QuizManager(QuizDAO quizDAO, QuestionDAO questionDAO, QuizScoreDAO quizScoreDAO) {
		this.quizDAO = quizDAO;
		this.questionDAO = questionDAO;
		this.quizScoreDAO = quizScoreDAO;
	}
	
	final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	final static int defaultNumOfQuestions = 10;
	final static int maxNumOfQuestions = 100;
	
	/*
	 * 	--------------- Retrieve Methods ---------------
	 */
	
	/*
	 *	generateRandomQuiz - Pulls questions from database by groupId, 
	 *	puts them in a Quiz object.
	 */
	public Quiz generateRandomQuiz(int groupId) {
		Quiz quiz = null;//new Quiz(QuizDAO.retrieveQuiz(groupId));
		
		//TODO: Narrow quiz to numOfQuestions.
		
		return quiz;
	}
	
	/*
	 * 	generateRandomQuiz - Pulls questions from database by tags,
	 * 	puts them in a Quiz object.
	 */
	public Quiz generateRandomQuiz(int[] tagIds) {
		
		Quiz quiz = null;//new Quiz(QuizDAO.retrieveQuiz(tagIds));
		
		//TODO: Narrow quiz to numOfQuestions
		
		return quiz;
	}
	
	/*
	 *	deleteQuiz - Deletes quiz based on quiz ID.
	 * TODO: Do we re-implement this?
	 */
	public void deleteQuiz(int qID) {
		//QuizDAO.deleteQuiz(qID);
		//TODO: do we re-implement this?
	}
	
	/*
	 * TODO: Add methods used for quiz management
	 */
	public Quiz findQuiz(long qID) {
		
		Quiz quiz = quizDAO.retrieveExistingQuizDetails(qID);
		
		if(quiz.getQuizId() > 0) {
			quiz.setQuestions(getQuestions(qID));
			logger.debug(String.format("<QuizManager> -- Quiz %d retrieved from database", quiz.getQuizId()));
		}
		else {
			logger.warn("<QuizManager> -- Quiz was not found in database");
		}
		return quiz;
	}
	
	public Quiz getRandomizedQuestions(long quizId)
	{
		//get and randomize questions
		List<Question> randomizedQuestionList = questionDAO.retrieveExistingQuiz(quizId);
		Collections.shuffle(randomizedQuestionList);
		//get the quiz from db
		Quiz quiz = quizDAO.retrieveExistingQuizDetails(quizId);
		
		//validate the quiz
		if(quiz.getQuizId() > 0)
			quiz.setQuestions(randomizedQuestionList);
		
		return quiz;
	}
	
	public Quiz getRandomizedQuestions(long quizId, long userId) {
		//get and randomize questions
		List<Question> randomizedQuestionList = questionDAO.retrieveExistingQuiz(quizId);
		
		for(Question question : randomizedQuestionList) {
			question.setUserDifficulty(findUserQuestionDifficulty(userId, question.getQuestionId()));
			question.setUserRating(findUserQuestionRating(userId, question.getQuestionId()));
		}
		
		Collections.shuffle(randomizedQuestionList);
		//get the quiz from db
		Quiz quiz = quizDAO.retrieveExistingQuizDetails(quizId);
		
		//validate the quiz
		if(quiz.getQuizId() > 0)
			quiz.setQuestions(randomizedQuestionList);
		
		return quiz;
	}
	
	public Question findQuestionById(long questionId) {
		
		if(questionId < 1)
			return null;
		
		Question question = questionDAO.getQuestion(questionId);
		
		if(question != null && questionId > 0)
			question.setCategories(questionDAO.getCategoriesByQuestion(question.getQuestionId()));
		
		return question;
	}
	
	/*
	 * Find all quizzes participated in by a user
	 */
	public List<Quiz> findQuizzesParticipated(long userId) {
		if(userId < 1) {
			return null;
		}
		else {
			return quizScoreDAO.retrieveQuizzesParticipated(userId);
		}
	}
	
	/*
	 * Find all quizzes created by a user
	 */
	public List<Quiz> findQuizzesByCreatorId(long creatorId) {
		if(creatorId < 1) {
			return null;
		}
		else {
			return quizDAO.retrieveQuizzesByCreatorId(creatorId);
		}
	}
	
	/*
	 * Find all quizzes in a group created by the user
	 */
	public List<Quiz> findQuizzesByCreatorId(long creatorId, long groupId) {
		if(creatorId < 1 || groupId < 1) {
			return null;
		}
		else {
			return quizDAO.retrieveQuizzesByCreatorAndGroup(creatorId, groupId);
		}
	}
	
	private List<Question> getQuestions(long quizId) {
		
		List<Question> questions = questionDAO.retrieveExistingQuiz(quizId);
		
		if(questions != null) {
			for(Question question: questions) {
				question.setCategories(questionDAO.getCategoriesByQuestion(question.getQuestionId()));
			}
		}
		
		return questions;
	}
	
	public List<Question> findQuestionsByGroup(long groupId) {
		
		if(groupId < 1) {
			return null;
		}
		
		List<Question> questions = questionDAO.getQuestionsByGroupId(groupId);
		
		if(questions != null) {
			for(Question question: questions) {
				question.setCategories(questionDAO.getCategoriesByQuestion(question.getQuestionId()));
			}
		}
		
		return questions;
	}
	
	public List<Question> findUnaddedGroupQuestions(long groupId, long quizId) {
		if(groupId > 0 && quizId > 0) {
			
			List<Question> questions = questionDAO.getUnaddedQuizQuestionsByGroup(groupId, quizId);
			
			if(questions != null) {
				for(Question question: questions) {
					question.setCategories(questionDAO.getCategoriesByQuestion(question.getQuestionId()));
				}
			}
			
			return questions;
		}
		else {
			return null;
		}
	}
	
	public List<QuizScore> findScoresByUser(long userId)
	{
		if(userId > 0)
			return quizScoreDAO.findScoresByUserId(userId);
		else
			return null;
	}
	
	public List<QuizScore> findScoresByQuiz(long quizId)
	{
		if(quizId > 0)
			return quizScoreDAO.findScoresByQuizID(quizId);
		else
			return null;
	}
	
	public List<QuizScore> findScoresByQuizAndUser(long quizId, long userId)
	{
		if(quizId > 0 || userId > 0)
			return quizScoreDAO.findScoresByQuizAndUser(quizId, userId);
		else
			return null;
	}
	
	
	/*
	 * 	--------------- Creation Methods ---------------
	 */
	
	public long createQuestion(Question question) throws Exception
	{
		// TODO: Implement a return check. True for success, false for failure.
		
		// Check for invalid parameters in the question.
		if(question.getCreatorId() < 0) {
			throw new Exception("Invalid creator ID. Must be greater than 0.");
		}
		else if(question.getAnswerCount() <= 0 ) {
			throw new Exception("No answers were provided for this question.");
		}
		else if(question.getQuestionText() == null || question.getQuestionText() == "") {
			throw new Exception("No question text was provided.");
		}
		else {
			List<String> wrongAnswers = question.getWrongAnswers();
			String reference = question.getReference();
			if(question.getReferenceLink() != null && !"".equals(question.getReferenceLink())) {
				reference = "<a href=\"" + question.getReferenceLink() + "\">" + reference + "</a>";
			}
			
			long id = questionDAO.createQuestion(question.getCreatorId(), 
												 question.getGroupId(), 
												 InputUtils.sanitizeInput(question.getQuestionText()), 
												 InputUtils.sanitizeInput(question.getCorrectAnswer()), 
												 InputUtils.sanitizeInput(question.getQuestionType().toString()), 
												 InputUtils.sanitizeInput(wrongAnswers.get(0)), 
												 InputUtils.sanitizeInput(wrongAnswers.get(1)),
												 InputUtils.sanitizeInput(wrongAnswers.get(2)), 
												 InputUtils.sanitizeInput(wrongAnswers.get(3)), 
												 question.isFlagged(), 
												 InputUtils.sanitizeInput(question.getExplanation()), 
												 InputUtils.sanitizeInput(reference), 
												 question.isOrdered(), 
												 question.getCorrectPosition());
			
			setCategoriesForQuestion(question.getQuestionId(), question.getCategories());
			
			//Give points for creating the question
			UserGroupManager usrGrpM = null;
			//usrGrpM.addPoints(question.getCreatorId(), question.getGroupId(), 1);
			
			return id;
		}
	}
	
	public long createQuiz(Quiz quiz, long groupID) {
		
		
		//TODO Commenting this out for now since the framework is not in place
		//			to allow these validations, once it is in place will need to edit this
		
		
		// Check for invalid parameters.
		/*if(quiz.getCreatorId() < 0)
			throw new Exception("Invalid creator ID. Must be greater than 0.");
		else if(quiz.getQuestionCount() <= 0) {
			throw new Exception("There aren't any questions in this quiz.");
		}
		else
		{ 
			// Check that all quiz questions exist within the database.
			Boolean allQuestionsExist = true;
			Question[] existingQuestions = quiz.getQuestions();
			
			for(int i = 0; i < existingQuestions.length; i++)
			{
				// Default ID value is -1, meaning not added to the database.
				if(existingQuestions[i].getQuestionId() < 0)
					allQuestionsExist = false;
			}
			
			if(allQuestionsExist)
				QuizDAO.createQuiz(quiz);
			else
				throw new Exception("Not all questions exist in the database.");
		}*/
		
		long rvalue = quizDAO.createQuiz(quiz.getCreatorId(),
				InputUtils.sanitizeInput(quiz.getName()), 
								  quiz.getDifficulty(), 
								  quiz.getRating(), 
								  InputUtils.sanitizeInput(quiz.getDescription()),
								  quiz.getTime(),
								  quiz.isOpen());
		
		//Give points for creating the quiz
		//TODO check if this is the right spot?		
		UserGroupManager usrGrpM = null;
		//usrGrpM.addPoints(quiz.getCreatorId(), groupID, 5);
		
		return rvalue;
	}
	
	/*
	 * Adds a quiz to question association into the QuizQuestion table of the database
	 */
	public boolean addQuestionToQuiz(long quizId, long questionId) {
		if(quizId < 1 || questionId < 1)
			return false;
		long id = quizDAO.addQuestion(quizId, questionId);
		
		if(id > 0) {
			quizDAO.incrementQuestionCount(quizId);
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Removes a question from a quiz
	 */
	public boolean removeQuestionFromQuiz(long quizId, long questionId) {
		//invalid id's return fail
		if(quizId < 1 || questionId < 1) {
			return false;
		}
		//remove and return true
		try {
			//TODO: Must check if question exists before we remove and decrement.
			quizDAO.deleteQuizQuestion(quizId, questionId);
			quizDAO.decrementQuestionCount(quizId);
			return true;
		}
		catch(Exception e) {
			//any exceptions removal failed, return false
			return false;
		}
	}
	
	/*
	 * Links a quiz to a group - this creates an association in GroupQuiz table
	 * 	of a GroupID to QuizID
	 */
	public boolean addQuizToGroup(long quizId, long groupId) {
		if(quizId < 1 || groupId < 1)
			return false;
		quizDAO.linkToGroup(quizId, groupId);
		return true;
	}

	/*
	 * Return a list of quizzes from the database
	 */
	public List<Quiz> findQuizzesByGroup(long groupId) {
		if(groupId >= 1)
			return quizDAO.retrieveQuizzesByGroup(groupId);
		return null;
	}

	
	public List<Quiz> findTopQuizzes() {
		//TODO Restrict this to avoid returning the entire database
		return quizDAO.retrieveTopQuizzes();
	}
	
	public List<Quiz> findRecentQuizzes() {
		//TODO Restrict this to avoid returning the entire database
		return quizDAO.retrieveRecentQuizzes();
	}
	
	/*
	 * Find a user's entered quality rating for a quiz
	 */
	public int findUserRating(long userId, long quizId) {
		if(userId == 0 || quizId == 0) {
			//this is invalid
			return 0;
		}
		return quizDAO.getQuizRating(quizId, userId);
	}
	
	/*
	 * Find a user's difficulty rating for a quiz
	 */
	public int findUserDifficulty(long userId, long quizId) {
		if(userId == 0 || quizId == 0) {
			//this is invalid
			return 0;
		}
		return quizDAO.getQuizDifficulty(quizId, userId);
	}
	
	public boolean rateQuizQuality(long userId, long quizId, int rating) {
		
		if(userId < 1 || quizId < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = quizDAO.getQuizRating(quizId, userId);
			
			if(existingRating > 0) {
				quizDAO.updateQuizRating(userId, quizId, rating);
				quizDAO.updateQuizQualityRating(rating - existingRating, quizId);
			}
			else {
				long key = quizDAO.insertQuizRating(quizId, userId, rating);
				if(key > 0) {
					quizDAO.rateQuizQuality(rating, quizId);
					
					//TODO check if this is the right spot?		
					//long rvalue = quizDAO.		//NEED TO GET GROUP ID
					//UserGroupManager usrGrpM = null;
					//usrGrpM.addPoints(userId, 4, 6);
					return true;
				}
				else {
					//insert failed, return false
					return false;
				}
			}
			
			return true;
		}
		catch(Exception e) {
			//database insert fails
			return false;
		}
	}
	
	public boolean rateQuizDifficulty(long userId, long quizId, int rating) {
		
		if(userId < 1 || quizId < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = quizDAO.getQuizDifficulty(quizId, userId);
			
			if(existingRating > 0) {
				quizDAO.updateQuizDifficulty(userId, quizId, rating);
				quizDAO.updateQuizDifficultyRating(rating - existingRating, quizId);
			}
			else {
				long key = quizDAO.insertQuizDifficulty(quizId, userId, rating);
				if(key > 0) {
					quizDAO.rateQuizDifficulty(rating, quizId);
					return true;
				}
				else {
					//insert failed, return false
					return false;
				}
			}
			
			return true;
		}
		catch(Exception e) {
			//database insert fails
			return false;
		}
	}
	
	public boolean updateQuestion(Question question) {
		
		setCategoriesForQuestion(question.getQuestionId(), question.getCategories());
		questionDAO.updateQuestion(InputUtils.sanitizeInput(question.getQuestionText()), 
								   InputUtils.sanitizeInput(question.getCorrectAnswer()), 
								   InputUtils.sanitizeInput(question.getWrongAnswers().get(0)), 
								   InputUtils.sanitizeInput(question.getWrongAnswers().get(1)), 
								   InputUtils.sanitizeInput( question.getWrongAnswers().get(2)), 
								   InputUtils.sanitizeInput(question.getWrongAnswers().get(3)), 
								   InputUtils.sanitizeInput(question.getExplanation()), 
								   InputUtils.sanitizeInput(question.getReference()), 
								   question.isOrdered(), 
								   question.getCorrectPosition(),
								   question.getQuestionId());
		return true;
	}
	
	private void setCategoriesForQuestion(long questionId, List<String> categories) {
		
		if(categories == null || questionId < 1) {
			return;
		}
		
		questionDAO.removeCategories(questionId);
		for(String cat: categories) {
			
			long catid = questionDAO.getCategoryId(cat);

			if(catid < 1) {
				catid = questionDAO.createCategory(cat);
			}
			
			if(catid > 0)
				questionDAO.addCategoryToQuestion(questionId, catid);
		}
		
	}
	
	public List<String> getAllCategories() {
		return questionDAO.getAllCategories();
	}
	
	/************************** Question Ratings and Difficulties *************************************/
	/*
	 * Find a user's entered quality rating for a quiz
	 */
	public int findUserQuestionRating(long userId, long questionId) {
		if(userId == 0 || questionId == 0) {
			//this is invalid
			return 0;
		}
		return questionDAO.getQuestionRating(questionId, userId);
	}
	
	/*
	 * Find a user's difficulty rating for a quiz
	 */
	public int findUserQuestionDifficulty(long userId, long questionId) {
		if(userId == 0 || questionId == 0) {
			//this is invalid
			return 0;
		}
		return questionDAO.getQuestionDifficulty(questionId, userId);
	}
	
	public boolean rateQuestionQuality(long userId, long questionId, int rating) {
		
		if(userId < 1 || questionId < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = questionDAO.getQuestionRating(questionId, userId);
			
			if(existingRating > 0) {
				questionDAO.updateQuestionRating(userId, questionId, rating);
				questionDAO.updateQuestionQualityRating(rating - existingRating, questionId);
			}
			else {
				long key = questionDAO.insertQuestionRating(questionId, userId, rating);
				if(key > 0) {
					questionDAO.rateQuestionQuality(rating, questionId);
					return true;
				}
				else {
					//insert failed, return false
					return false;
				}
			}
			
			return true;
		}
		catch(Exception e) {
			//database insert fails
			return false;
		}
	}
	
	public boolean rateQuestionDifficulty(long userId, long questionId, int rating) {
		
		if(userId < 1 || questionId < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = questionDAO.getQuestionDifficulty(questionId, userId);
			
			if(existingRating > 0) {
				questionDAO.updateQuestionDifficulty(userId, questionId, rating);
				questionDAO.updateQuestionDifficultyRating(rating - existingRating, questionId);
			}
			else {
				long key = questionDAO.insertQuestionDifficulty(questionId, userId, rating);
				if(key > 0) {
					questionDAO.rateQuestionDifficulty(rating, questionId);
					return true;
				}
				else {
					//insert failed, return false
					return false;
				}
			}
			
			return true;
		}
		catch(Exception e) {
			//database insert fails
			return false;
		}
	}
	
	
	public boolean insertScore(long quizId, long userId, float score)
	{
		if(quizId > 0 || userId > 0) {
			quizScoreDAO.insert(userId, quizId, score);
			return true;
		}
		else
			return false;
	}
}

