package com.UndefinedParameter.app.core;

import java.util.Random;

import com.UndefinedParameter.jdbi.UserDAO;

public class UserManager {

	
	private UserDAO userDAO;
	
	public UserManager(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/*
	 * Gets the users contribution score by the user id and the organization id
	 * 	to which the user has contributed to
	 */
	public int retrieveContributionScore(int userId, int orgId) {
		//TODO: Implement
		
		Random random = new Random();
		return random.nextInt(100);
	}
	
	/*
	 * Gets the number of quizzes the user has contributed towards
	 * 	This is open to interpretation so we will figure out exactly how
	 * 	this should function before we implement it - This can wait to be done
	 *  next semester
	 */
	public int retreiveQuizzesParticipated(int userId, int orgId) {
		//TODO: Implement
		
		Random random = new Random();
		return random.nextInt(100);
	}
	
	public User findUserById(int userId) {
		return null;
	}
	
	public User findUserByUserName(String userName) {
		User user = new User();
		
		//TODO Get this from the database
		user.setUserName("TestUser");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setCountry("United States");
		user.setCity("Salt Lake City");
		user.setState("Utah");
		user.setEmail("Test@Test.com");
		user.setPassword("password");
		user.setSecretQuestion("Secret Question");
		user.setSecretAnswer("Secret Answer");
		
		return user;
	}
	
	public boolean registerNewUser(User user) throws Exception {
		try {
			userDAO.insert(user.getUserName(), 
						   user.getFirstName(), 
						   user.getLastName(), 
						   user.getMiddleName(),
						   user.getCountry(), 
						   user.getCity(), 
						   user.getState(), 
						   user.getEmail(), 
						   user.getPassword(), 
						   user.getSecretQuestion(), 
						   user.getSecretAnswer());
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
}
