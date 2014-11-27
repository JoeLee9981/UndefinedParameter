package com.UndefinedParameter.app.core;

public class GroupManager {

	
	/*
	 * Note for Melynda - Feel free to refactor -> rename methods to meet
	 * 		the naming convention you are using
	 */
	
	public static void addGroup(Group group) {
		
		/*
		 * Important - The id of the group will not be instantiated
		 * 	group.id will be set to -1. This will be auto assigned by the
		 * 	database, therefore don't pass it in
		 */
		
		//TODO: Add a group into the database
	}
	
	/*
	 * Find the groups within an organization by the organizations id
	 * 	return as an array of Group classes.
	 */
	public static Group[] findGroupsByOrg(int orgId) {
		
		//TODO: get groups from database - this should be ok to retreive a complete listing
		//		as not there should be a manageable number of groups inside an org, however,
		//		we may want to consider limiting the number we can grab from DAO at once.
		
		Group[] groups = new Group[5];
		
		Group group = new Group(0, orgId, "CS-4400");
		groups[0] = group;
		group = new Group(1, orgId, "CS-4962");
		groups[1] = group;
		group = new Group(2, orgId, "MATH-3020");
		groups[2] = group;
		group = new Group(3, orgId, "MATH-1010");
		groups[3] = group;
		group = new Group(4, orgId, "BIO-2010");
		groups[4] = group;
		
		return groups;
	}
	
	/*
	 * Get a group from the database by the
	 * 	groups id
	 */
	public static Group findGroupById(int id) {
		//TODO: Get this from the datatbase
		
		Group[] groups = new Group[5];
		
		Group group = new Group(0, 0, "CS-4400");
		groups[0] = group;
		group = new Group(1, 1, "CS-4962");
		groups[1] = group;
		group = new Group(2, 2, "MATH-3020");
		groups[2] = group;
		group = new Group(3, 3, "MATH-1010");
		groups[3] = group;
		group = new Group(4, 4, "BIO-2010");
		groups[4] = group;
		
		return groups[id];
	}
}
