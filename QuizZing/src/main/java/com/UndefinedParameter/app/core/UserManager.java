package com.UndefinedParameter.app.core;

import java.util.Random;

public class UserManager {

	/*
	 * Gets the users contribution score by the user id and the organization id
	 * 	to which the user has contributed to
	 */
	public static int retrieveContributionScore(int userId, int orgId) {
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
	public static int retreiveQuizzesParticipated(int userId, int orgId) {
		//TODO: Implement
		
		Random random = new Random();
		return random.nextInt(100);
	}
	
	public static User findUserById(int userId) {
		return null;
	}
	
	public static User findUserByUserName(String userName) {
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
	
}
