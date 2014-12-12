package com.UndefinedParameter.app.core;

import java.util.ArrayList;

import com.UndefinedParameter.jdbi.OrganizationDAO;

public class GroupManager {

	
	/*
	 * Note for Melynda - Feel free to refactor -> rename methods to meet
	 * 		the naming convention you are using
	 */
	
	public static int addGroup(Group group) {
		
		/*
		 * Important - The id of the group will not be instantiated
		 * 	group.id will be set to -1. This will be auto assigned by the
		 * 	database, therefore don't pass it in
		 */
		
		if(group.getOrganizationId() < 1 || group.getName() == null || group.getName().length() == 0) {
			//invalid ID
			return -1;
		}
		return OrganizationDAO.createGroup(group);
	}
	
	/*
	 * Find the groups within an organization by the organizations id
	 * 	return as an array of Group classes.
	 */
	public static ArrayList<Group> findGroupsByOrg(int orgId) {
		
		//TODO: get groups from database - this should be ok to retreive a complete listing
		//		as not there should be a manageable number of groups inside an org, however,
		//		we may want to consider limiting the number we can grab from DAO at once.
		
		return OrganizationDAO.findGroupsByOrgId(orgId);
	}
	
	/*
	 * Get a group from the database by the
	 * 	groups id
	 */
	public static Group findGroupById(int id) {
		//TODO: Get this from the datatbase
		
		return OrganizationDAO.findGroupById(id);
	}
}
