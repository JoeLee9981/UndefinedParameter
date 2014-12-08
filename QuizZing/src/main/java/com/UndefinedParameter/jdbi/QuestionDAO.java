package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.quizzing.QuizZingApplication;

public class QuestionDAO {

	final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	private static final String dbPath = "database\\QuizZing";
	
	/*
	 * 	createQuestion - Given a question, insert it into the database assuming all
	 * 	necessary parameters are present. 
	 */
	public static boolean createQuestion(Question question)
	{
		// TODO: Check if the INSERT statement works.
		int result = -1;
		String select = "INSERT INTO Question (CreatorID, QuestionDifficulty, QuestionText, "
				+ "CorrectAnswer, WrongAnswer1, WrongAnswer2, WrongAnswer3, WrongAnswer4, QuestionType) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// TODO: Return result == 0 for failure?
			statement = connection.prepareStatement(select);
			statement.setInt(1, question.getCreatorId());
			statement.setInt(2, 3);
			statement.setString(3, question.getQuestionText());
			statement.setString(4, question.getCorrectAnswer());
			String[] wrongAnswers = question.getWrongAnswers();
			for(int i = 0; i < wrongAnswers.length; i++) {
				statement.setString((i + 5), wrongAnswers[i]);
			}
			statement.setString(9, question.getType().toString());
			result = statement.executeUpdate();
			connection.commit();
			
			statement.close();
			connection.close();	
			
			return true;
		}
		catch(Exception e){
			String errorMsg = "Question could not be inserted into the database. result = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * 	retrieveQuestions - Retrieves all the questions that are tagged with a given list of tags.
	 */
	public static ArrayList<Question> retrieveQuestions(int[] tagIds)
	{
		// TODO: Currently retrieves all questions of that category. Need to
		// narrow that number down to 100.
		
		ArrayList<Question> questions = new ArrayList<Question>();
		
		// TODO: Do we want to return an empty array of questions on tags = 0?
		if(tagIds.length == 0)
			return questions;
		
		// Retrieve all questions with tag ids.
		String select = "SELECT Q "
				+ "FROM Question Q, TagQuestion TQ, Tags T "
				+ "WHERE Q.QuestionID = TQ.QuestionID "
				+ "AND TQ.TagID = T.TagID "
				+ "AND TagID = (";
			
		// TODO: Check to see if this behemoth query actually works.
		// Append all tag ids to the select statement.
		for(int index : tagIds)
		{
			if(index == tagIds.length - 1)
				select += index + ") ";
			else
				select += index + ",";
		}
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			if(results.next()) {
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
				questions.add(new Question(qid, cid, diff, rate, qType, qText, answer, 
						new String[] {wrongA1, wrongA2, wrongA3, wrongA4}, flag));
			}
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e) {
			logger.error("Could not retrieve questions.");
			e.printStackTrace();
		}	
		
		return questions;
	}
	
	
	/*
	 * 	Will get a question from the database by Question ID.
	 */
	private static Question getQuestion(int qID)
	{

		int result = -1;
		Question question = null;
		
		String select = "SELECT * FROM Question WHERE QuestionID = " + qID;
		
		Connection connection = null;
		Statement statement = null;
		
		try {

			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			while(results.next())
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
				question = new Question(qid, cid, diff, rate, qType, qText, answer, new String[] {wrongA1, wrongA2, wrongA3, wrongA4}, flag);
			}			
		}
		catch(Exception e){
			String errorMsg = "Could not get selected question by ID from database. Database respone = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		return question;
	}
	
	
	/*
	 * 	retrieveQuiz - Retrieves questions from a specific group id.
	 */
	public static ArrayList<Question> retrieveQuestions(int gID)
	{
		// TODO: Implement retrieve questions query based on group id.
		ArrayList<Question> questions = new ArrayList<Question>();
		
		int result = -1;
		Question question = null;
		
		String select = "SELECT q.* FROM Question q, UserGroups u WHERE u.UserID = q.UserID AND u.GroupID = " + gID;
		
		Connection connection = null;
		Statement statement = null;
		ArrayList<Question> array = null;
		
		try
		{
			
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			while(results.next())
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
				question = new Question(qid, cid, diff, rate, qType, qText, answer, new String[] {wrongA1, wrongA2, wrongA3, wrongA4}, flag);
				
				array.add(question);
			}
			
		}
		catch(Exception e){
			String errorMsg = "Could not retrieve questions. Group " + gID + " or their questions may not exist."; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return questions;
	}
	
	
	
	/*
	 * Get all questions
	 */
	private static ArrayList<Question> getXQuestions(int maxamount)
	{
		int i = 0;
		int result = -1;
		Question question = null;
		ArrayList<Question> array = null;
		
		
		String select = "SELECT * FROM Question ORDER BY QuestionID";
		
		Connection connection = null;
		Statement statement = null;
		
		try {

			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			while(results.next())
			{				
				if(i > maxamount)
				{
					break;
				}
				else
				{
					i++;
				}
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
				question = new Question(qid, cid, diff, rate, qType, qText, answer, new String[] {wrongA1, wrongA2, wrongA3, wrongA4}, flag);
				
				array.add(question);
			}			
		}
		catch(Exception e){
			String errorMsg = "Could not get question from database. Database respone = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		return array;
	}
	
	
}
