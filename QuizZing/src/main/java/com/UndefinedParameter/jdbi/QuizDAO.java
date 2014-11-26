package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.UndefinedParameter.app.core.Question;

public class QuizDAO {
	
	private static final String dbPath = "database\\QuizZing";
	
	public static ArrayList<Question> retrieveQuiz(int [] tagIds)
	{
		// TODO: Currently retrieves all questions of that category. Need to
		// fix so no crashing occurs.
		
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
				questions.add(new Question(qid, cid, diff, rate, qType, qText, answer, new String[] {wrongA1, wrongA2, wrongA3, wrongA4}, flag));
			}
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
		return questions;
	}

}
