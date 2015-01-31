package com.UndefinedParameter.app.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
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
	
	public QuizManager(QuizDAO quizDAO, QuestionDAO questionDAO) {
		this.quizDAO = quizDAO;
		this.questionDAO = questionDAO;
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
	 */
	public void deleteQuiz(int qID) {
		//QuizDAO.deleteQuiz(qID);
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
	
	/*
	 * TODO: Implement this
	 */
	private List<Question> getQuestions(long quizId) {
		//TODO: Validation
		return questionDAO.retrieveExistingQuiz(quizId);
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
			return questionDAO.createQuestion(question.getCreatorId(), question.getQuestionDifficulty(), question.getRating(),
					question.getQuestionText(), question.getCorrectAnswer(), question.getQuestionType(), wrongAnswers.get(0), wrongAnswers.get(1),
					wrongAnswers.get(2), wrongAnswers.get(3));
		}
	}
	
	public long createQuiz(Quiz quiz) {
		
		
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
		
		return quizDAO.createQuiz(quiz.getCreatorId(), quiz.getDifficulty(), quiz.getRating(), quiz.getDescription(), quiz.getTime());
	}
	
	/*
	 * Adds a quiz to question association into the QuizQuestion table of the database
	 */
	public boolean addQuestionToQuiz(long quizId, long questionId) {
		if(quizId < 1 || questionId < 1)
			return false;
		quizDAO.addQuestion(quizId, questionId);
		return true;
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

}
