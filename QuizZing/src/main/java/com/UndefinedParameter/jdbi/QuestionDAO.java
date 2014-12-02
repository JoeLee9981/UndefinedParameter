package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
		String select = "INSERT INTO Question (CreatorID, QuestionDifficulty, QuestionText, "
				+ "CorrectAnswer, WrongAnswer1, WrongAnswer2, WrongAnswer3, WrongAnswer4) "
				+ "VALUES (" 
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
	
}
