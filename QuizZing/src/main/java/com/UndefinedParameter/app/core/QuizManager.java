package com.UndefinedParameter.app.core;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.UndefinedParameter.jdbi.QuestionDAO;
//import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.quizzing.QuizZingApplication;

/*
 * Class is responsible for managing of quizzes
 * 	Contains methods for creating, editing, removing
 * 	quizzes. Functions as a model layer over the database.
 */
public class QuizManager {
	
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
	public static Quiz generateRandomQuiz(int groupId) {
		Quiz quiz = null;//new Quiz(QuizDAO.retrieveQuiz(groupId));
		
		//TODO: Narrow quiz to numOfQuestions.
		
		return quiz;
	}
	
	/*
	 * 	generateRandomQuiz - Pulls questions from database by tags,
	 * 	puts them in a Quiz object.
	 */
	public static Quiz generateRandomQuiz(int[] tagIds) {
		
		Quiz quiz = null;//new Quiz(QuizDAO.retrieveQuiz(tagIds));
		
		//TODO: Narrow quiz to numOfQuestions
		
		return quiz;
	}
	
	/*
	 *	deleteQuiz - Deletes quiz based on quiz ID.
	 */
	public static void deleteQuiz(int qID) {
		//QuizDAO.deleteQuiz(qID);
	}
	
	/*
	 * TODO: Add methods used for quiz management
	 */
	public static Quiz findQuiz(int qID) {
		//TODO: Uncomment code below when we're ready to implement. 
		
		//Quiz quiz = new Quiz(QuizDAO.retrieveExistingQuiz(qID));
		
		Quiz quiz = new Quiz();
		
		quiz.setQuizId(qID);
		quiz.setCreatorId(100);
		quiz.setDescription("THIS IS A FUN QUIZ");
		quiz.setRating(5);
		quiz.setDifficulty(1);
		quiz.setTime(1000);
		quiz.setQuestions(getQuestions(qID));
		return quiz;
	}
	
	/*
	 * TODO: Implement this
	 */
	private static ArrayList<Question> getQuestions(int quizId) {
		ArrayList<Question> questions = new ArrayList<Question>(); 
		
		for(int i = 0; i < 5; i++) {
			questions.add(getQuestion(i));
		}
		return questions;
	}
	
	/*
	 *  TODO: Implement this - NOTE THIS WILL NOT WORK FOR REAL CODING
	 */
	private static Question getQuestion(int questionId) {
		Question question = new Question();
		question.setQuestionId(questionId);
		question.setType(Question.QuestionType.MULTIPLE_CHOICE);
		question.setCreatorId(questionId * questionId);
		question.setQuestionDifficulty(5);
		question.setQuestionText("This is question " + questionId + ", please pick your answer");
		question.setCorrectAnswer("This is the correct answer");
		question.setCorrectPosition(questionId);
		String[] wrongAnswers = {
				"This is a wrong answer", 
				"This is another wrong answer", 
				"This is the 3rd wrong answer", 
				"Last wrong answere here"
			};
		question.setWrongAnswers(wrongAnswers);
		
		return question;
	}
	
	
	
	/*
	 * 	--------------- Creation Methods ---------------
	 */
	
	public static void createQuestion(Question question) throws Exception
	{
		// TODO: Implement a return check. True for success, false for failure.
		
		// Check for invalid parameters in the question.
		if(question.getCreatorId() < 0)
			throw new Exception("Invalid creator ID. Must be greater than 0.");
		else if(question.getAnswerCount() <= 0 )
			throw new Exception("No answers were provided for this question.");
		else if(question.getQuestionText() == null || question.getQuestionText() == "")
			throw new Exception("No question text was provided.");
		else
			QuestionDAO.createQuestion(question);
	}
	
	public static void createQuiz(Quiz quiz) throws Exception
	{
		
	}
}
