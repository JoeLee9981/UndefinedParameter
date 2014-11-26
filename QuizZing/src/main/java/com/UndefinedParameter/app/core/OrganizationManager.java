package com.UndefinedParameter.app.core;

public class OrganizationManager {
	
	/*
	 * Find groups within an organization by id
	 * 		Category is the category the groups belong to
	 * 		for example CS or Computer Science. This would return
	 * 		all classes related to the CS category for the organization
	 */
	public static Group[] findGroupsByCategory(int orgId, int category) {
		//TODO: Implement - note may want to use helper methods to group id's
				// then retrieve the groups
		return null;
	}
	
	public static Group[] findGroupsByCategory(int orgId, String category) {
		int catId = 0;//TODO: Find the category id from database
		return findGroupsByCategory(orgId, catId);
	}
	
	/*
	 * Finds all of the groups by an organizations id
	 */
	public static Group[] findGroupsById(int orgId) {
		//TOOD: Implement
		return null;
	}
}
