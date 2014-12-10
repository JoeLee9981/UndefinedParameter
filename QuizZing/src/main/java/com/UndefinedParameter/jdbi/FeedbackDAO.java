package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.core.Feedback;
import com.UndefinedParameter.quizzing.QuizZingApplication;

public class FeedbackDAO {

	final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	private static final String dbPath = "database/QuizZing";
	
	public static boolean addFeedback(Feedback feedback) {
		
		// TODO: Check if the INSERT statement works.
		int result = -1;
		String select = "INSERT INTO Feedback (feature, improvement, miscellaneous) VALUES(?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// TODO: Return result == 0 for failure?
			statement = connection.prepareStatement(select);
			statement.setString(1, feedback.getSuggestedFeature());
			statement.setString(2, feedback.getImprovement());
			statement.setString(3, feedback.getMiscellaneous());
			
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
	
	public static ArrayList<Feedback> retrieveFeedback() {
		// TODO: Currently retrieves all feedback. Need to
		// narrow that number down to 100.
		
		ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
		
		String select = "SELECT * FROM Feedback";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			while(results.next()) {
				String feature = results.getString("feature");
				String improve = results.getString("improvement");
				String misc = results.getString("miscellaneous");
				Feedback feedback = new Feedback();
				feedback.setImprovement(improve);
				feedback.setSuggestedFeature(feature);
				feedback.setMiscellaneous(misc);
				feedbackList.add(feedback);
			}
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e) {
			logger.error("Error retrieving feedback from database : " + e.toString());
			e.printStackTrace();
		}	
		
		return feedbackList;
	}	
}
