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
	
}
