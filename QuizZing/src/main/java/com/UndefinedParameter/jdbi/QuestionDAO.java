package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
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
	public static void createQuestion(Question question)
	{
		// TODO: Check if the INSERT statement works.
		int result = -1;
		String select = "INSERT INTO Question(CreatorID, QuestionDifficulty, QuestionText, "
				+ "CorrectAnswer, WrongAnswer1, WrongAnswer2, WrongAnswer3, WrongAnswer4) "
				+ "VALUES(" 
				+ question.getCreatorId() + ", "
				+ question.getQuestionDifficulty() + ", "
				+ question.getQuestionText() + ", "
				+ question.getCorrectAnswer();
		
		for(int i = 0; i < question.getAnswerCount(); i++)
		{
			select += ", " + question.getAnswerAt(i);
		}
		
		select += ") ";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// TODO: Return result == 0 for failure?
			statement = connection.createStatement();
			result = statement.executeUpdate(select);

			statement.close();
			connection.close();		
		}
		catch(Exception e){
			String errorMsg = "Question could not be inserted into the database. result = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		// 	TODO: Return a failure or success?
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
	 * 	retrieveQuiz - Retrieves questions from a specific group id.
	 */
	public static ArrayList<Question> retrieveQuestions(int gID)
	{
		// TODO: Implement retrieve questions query based on group id.
		ArrayList<Question> questions = new ArrayList<Question>();
		
		try{
			
		}
		catch(Exception e){
			String errorMsg = "Could not retrieve questions. Group " + gID + " or their questions may not exist."; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return questions;
	}
}
