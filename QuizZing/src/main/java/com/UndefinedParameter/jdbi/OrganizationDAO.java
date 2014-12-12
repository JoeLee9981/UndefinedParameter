package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.quizzing.QuizZingApplication;

public class OrganizationDAO {
	
	final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	private static final String dbPath = "database/QuizZing";
	
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
	
	/*
	 * Finds the groups that belong to an organization based upon the organizations id
	 */
	public static ArrayList<Group> findGroupsByOrgId(int orgId) {
		// TODO: Implement retrieve quiz questions query based on quiz id.
		ArrayList<Group> groups = new ArrayList<Group>();
		
		if(orgId < 0)
		{
			return groups;
		}
		
		// Retrieve all questions with quiz id.
		String select = "SELECT * FROM SubGroup WHERE OrgID = ?";
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(select);
			statement.setInt(1, orgId);
			ResultSet results = statement.executeQuery();
			
			while(results.next())
			{				
				int groupId = results.getInt("GroupID");
				String name = results.getString("Name");
				String description = results.getString("Description");
				int memberCount = results.getInt("MemberCount");
				int quizCount = results.getInt("QuizCount");
				int questionCount = results.getInt("QuestionCount");
				
				Group group = new Group();
				group.setId(groupId);
				group.setOrganizationId(orgId);
				group.setName(name);
				group.setDescription(description);
				group.setMemberCount(memberCount);
				group.setQuizCount(quizCount);
				group.setQuestionCount(questionCount);
				
				groups.add(group);
			}
			
			statement.close();
			connection.close();	
		}
		catch(Exception e)
		{
			String errorMsg = "Unable to find any groups associated with org: " + orgId; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return groups;
	
	}
	
	/*
	 * Finds the groups that belong to an organization based upon the organizations id
	 */
	public static Group findGroupById(int groupId) {
		// TODO: Implement retrieve quiz questions query based on quiz id.
		Group group = new Group();
		
		if(groupId < 0)
		{
			return group;
		}
		
		// Retrieve all questions with quiz id.
		String select = "SELECT * FROM SubGroup WHERE GroupId = ?";
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(select);
			statement.setInt(1, groupId);
			ResultSet results = statement.executeQuery();
			
			if(results.next())
			{				
				int orgId = results.getInt("OrgID");
				String name = results.getString("Name");
				String description = results.getString("Description");
				int memberCount = results.getInt("MemberCount");
				int quizCount = results.getInt("QuizCount");
				int questionCount = results.getInt("QuestionCount");
				
				group.setId(groupId);
				group.setOrganizationId(orgId);
				group.setName(name);
				group.setDescription(description);
				group.setMemberCount(memberCount);
				group.setQuizCount(quizCount);
				group.setQuestionCount(questionCount);
			}
			
			statement.close();
			connection.close();	
		}
		catch(Exception e)
		{
			String errorMsg = "Unable to find any group by ID: " + groupId; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return group;
	
	}
	
	/*
	 * Add a group into the SubGroup table of the database. Group must have an organizationid
	 */
	public static int createGroup(Group group) {
		
		int key = -1;
		String select = "INSERT INTO SubGroup (Name, Description, OrgID) VALUES(?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			// TODO: Return result == 0 for failure?
			statement = connection.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, group.getName());
			statement.setString(2, group.getDescription());
			statement.setInt(3, group.getOrganizationId());
			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result != null && result.next())
				key = result.getInt(1);
			
			connection.commit();
			
			statement.close();
			connection.close();	
			
			return key;
		}
		catch(Exception e){
			String errorMsg = "Question could not be inserted into the database. result = "; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
		
		return key;
	}
	

}
