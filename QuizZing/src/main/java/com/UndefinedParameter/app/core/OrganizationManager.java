package com.UndefinedParameter.app.core;

import java.util.ArrayList;

import com.UndefinedParameter.jdbi.OrganizationDAO;

public class OrganizationManager {
	
	/*
	 * Find groups within an organization by id
	 * 		Category is the category the groups belong to
	 * 		for example CS or Computer Science. This would return
	 * 		all classes related to the CS category for the organization
	 */
	public static Group[] findGroupsByCategory(int orgId, int categoryId) {
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
	
	public static ArrayList<Organization> findOrgsByLocation(String city) {
		
		//TODO: Get these from the database instead of hard coding
		
		return OrganizationDAO.findOrganizations();
	}
	
	public static Organization findOrgById(int id) {
		
		//TODO Validate, if not found id returned is -1;
		return OrganizationDAO.findOrganization(id);
	}
}
