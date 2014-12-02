package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.quizzing.QuizZingApplication;

public class QuizDAO {
	
	final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	private static final String dbPath = "database\\QuizZing";
	
	/*
	 * 	retrieveExistingQuiz - Retrieves quiz questions from a specific existing quiz id.
	 */
	public static ArrayList<Question> retrieveExistingQuiz(int qID)
	{
		// TODO: Implement retrieve quiz questions query based on quiz id.
		ArrayList<Question> questions = new ArrayList<Question>();
		
		if(qID == 0)
		{
			return questions;
		}
		
		// Retrieve all questions with quiz id.
		String select = "SELECT Qt.* "
				+ "FROM Question Qt, Quiz Qz, QuizQuestion Qq "
				+ "WHERE Qt.QuestionID = Qq.QuestionID "
				+ "AND Qq.QuizID = Qz.QuizID "
				+ "AND Qz.QuizID = " + qID;
			
		Connection connection = null;
		Statement statement = null;
		
		try{
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			if(results.next())
			{				
				int qid = results.getInt("QuestionID");
				int cid = results.getInt("CreatorID");
				int diff = results.getInt("QuestionDifficulty");
				int rate = results.getInt("Rating");
				String qText = results.getString("QuestionText");
				String answer = results.getString("CorrectAnswer");
				String qType = results.getString("QuestionType");
				String wrongA1 = results.getString("WrongAnswer1");
				String wrongA2 = results.getString("WrongAnswer2");
				String wrongA3 = results.getString("WrongAnswer3");
				String wrongA4 = results.getString("WrongAnswer4");
				Boolean flag = results.getBoolean("Flagged");	
				questions.add(new Question(qid, cid, diff, rate, qType, qText, answer,	new String[] {wrongA1, wrongA2, wrongA3, wrongA4}, flag));
			}
		}
		catch(Exception e)
		{
			String errorMsg = "Could not retrieve quiz. Quiz " + qID + " may not exist."; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return questions;
	}
	
	/*
	 * 	retrieveExistingQuizDetails - Retrieves quiz details from the database.
	 */
	public static Quiz retrieveExistingQuizDetails(int qID)
	{
		// TODO: The necessity of this method may change later. As in, I don't think we'll need all the details, just specific details.
		// TODO: Implement the retrieve quiz details query using the quiz id.
		Quiz quiz = new Quiz();
		
		Connection connection = null;
		Statement statement = null;
		
		String select = "SELECT Description "
				+ "FROM Quiz "
				+ "WHEREQuizID = " + qID;
			
		
		
		try{
			
			
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			String descript = results.getString("Description");
			
			quiz.setQuizId(qID);
			quiz.setDescription(descript);
			
			
		}
		catch(Exception e){
			String errorMsg = "Could not retrieve quiz details. Quiz " + qID + " may not exist."; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return quiz;
	}
	
	/*
	 * 	deleteQuiz - Deletes quiz from quiz id.
	 */
	public static void deleteQuiz(int qID)
	{
		// TODO: Implement delete query based on quiz id.
		
		
		String deletequiz = "DELETE FROM Quiz WHERE QuizID = " + qID;
		String deletequizquestion = "DELETE FROM QuizQuestion WHERE QuizID = " + qID;
		String deletetagquiz = "DELETE FROM TagQuiz WHERE QuizID = " + qID;
		
		Connection connection = null;
		Statement statement = null;
		

		
		
		try{
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			
			statement.executeUpdate(deletequiz);
			statement.executeUpdate(deletequizquestion);
			statement.executeUpdate(deletetagquiz);
			
		}
		catch(Exception e){
			String errorMsg = "Could not delete quiz. Quiz " + qID + " may not exist."; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
	}
	
	/*
	 * 	createQuiz - Adds a quiz to the database. Assume all questions
	 * 	already exist in the database.
	 */
	public static void createQuiz(Quiz quiz)
	{
		int result = -1;
		int id = -1;
		
		String insert = "INSERT INTO Question(CreatorID, Difficulty, Description, Time) "
				+ "VALUES(" 
				+ quiz.getCreatorId() + ", "
				+ quiz.getDifficulty() + ", "
				+ quiz.getDescription() + ", "
				+ quiz.getTime() + ") ";
		String select = "SELECT last_insert_rowid() "
				+ "FROM Quiz ";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// Insert quiz information in the Quiz table.
			statement = connection.createStatement();
			result = statement.executeUpdate(insert);
			
			// Get the last id put in the Quiz table, which is presumably this quiz's.
			ResultSet quizID = statement.executeQuery(select);
			
			if(quizID.next()) {
				id = quizID.getInt("QuizID");
			}
			
			quizID.close();			
			statement.close();
			connection.close();	
			
			// Set quiz id and send it to the linking method.
			quiz.setQuizId(id);
			linkToQuestions(quiz);
		}
		catch(Exception e){
			String errorMsg = "Quiz could not be created. result = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
	}
	
	/*
	 * 	linkToQuestions - Helper method will link quiz to its questions
	 * 	in the database.
	 */
	private static void linkToQuestions(Quiz quiz)
	{
		int result = -1;
		int quizID = quiz.getQuizId();
		Question[] questions = quiz.getQuestions();
		
		// Using UNION to add multiple rows at a time to the database.
		String insert = "INSERT INTO TagQuestion "
				+ "SELECT " + quizID + " AS QuizID, " + questions[0].getQuestionId() + " AS QuestionID ";
		
		for(int i = 1; i < questions.length; i++)
			insert += "UNION SELECT " + quizID + ", " + questions[i].getQuestionId() + " ";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			result = statement.executeUpdate(insert);
			
			statement.close();
			connection.close();	
		}
		catch(Exception e){
			String errorMsg = "Quiz " + quizID + " could not be linked to its questions. result = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
	}
}
