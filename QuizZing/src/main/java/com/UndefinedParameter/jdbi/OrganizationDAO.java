package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.quizzing.QuizZingApplication;

public class OrganizationDAO {
	
	final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	private static final String dbPath = "database\\QuizZing";
	
	/*
	 * Fetches all organizations from the db
	 * 		TODO: For production this has to be limited
	 */
	public static ArrayList<Organization> findOrganizations() {
				
		ArrayList<Organization> orgs = new ArrayList<Organization>();
		String select = "SELECT * FROM Organization";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// TODO: Return result == 0 for failure?
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			while(results.next()) {
				int id = results.getInt("OrgID");
				String name = results.getString("Name");
				String desc = results.getString("Description");
				String city = results.getString("City");
				String state = results.getString("State");
				String country = results.getString("Country");
				int memberCount = results.getInt("MemberCount");
				int quizCount = results.getInt("QuizCount");
				int questionCount = results.getInt("QuestionCount");
				
				Organization org = new Organization(id, name);
				org.setDescription(desc);
				org.setCity(city);
				org.setState(state);
				org.setCountry(country);
				org.setMemberCount(memberCount);
				org.setQuizCount(quizCount);
				org.setQuestionCount(questionCount);
				
				orgs.add(org);
			}
			
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e){ 
			logger.error("DATABASE ERROR: Unable to get organizations from Database");
			e.printStackTrace();
		}
		return orgs;
	}
	
	/*
	 * Fetches all organizations from the db
	 * 		TODO: For production this has to be limited
	 */
	public static Organization findOrganization(int id) {
				
		Organization org = new Organization();
		String select = "SELECT * FROM Organization WHERE OrgID = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// TODO: Return result == 0 for failure?
			statement = connection.prepareStatement(select);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			
			if(results.next()) {
				String name = results.getString("Name");
				String desc = results.getString("Description");
				String city = results.getString("City");
				String state = results.getString("State");
				String country = results.getString("Country");
				int memberCount = results.getInt("MemberCount");
				int quizCount = results.getInt("QuizCount");
				int questionCount = results.getInt("QuestionCount");
				
				org.setId(id);
				org.setName(name);
				org.setDescription(desc);
				org.setCity(city);
				org.setState(state);
				org.setCountry(country);
				org.setMemberCount(memberCount);
				org.setQuizCount(quizCount);
				org.setQuestionCount(questionCount);
				
			}
			
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e){ 
			logger.error("DATABASE ERROR: Unable to get organizations from Database");
			e.printStackTrace();
		}
		return org;
	}
	

}