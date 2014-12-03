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
	
}
