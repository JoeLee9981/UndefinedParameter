package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.OrganizationDAO;

public class GroupManager {

	private OrganizationDAO orgsDAO;
	
	public GroupManager(OrganizationDAO orgsDAO) {
		this.orgsDAO = orgsDAO;
	}
	
	public int addGroup(Group group) {
		
		/*
		 * Important - The id of the group will not be instantiated
		 * 	group.id will be set to -1. This will be auto assigned by the
		 * 	database, therefore don't pass it in
		 */
		
		if(group.getOrganizationId() < 1 || group.getName() == null || group.getName().length() == 0) {
			//invalid ID
			return -1;
		}
		orgsDAO.insertGroup(group.getName(), group.getDescription(), group.getOrganizationId());
		return 1;
	}
	
	/*
	 * Find the groups within an organization by the organizations id
	 * 	return as an array of Group classes.
	 */
	public List<Group> findGroupsByOrg(int orgId) {
		
		//TODO: get groups from database - this should be ok to retreive a complete listing
		//		as not there should be a manageable number of groups inside an org, however,
		//		we may want to consider limiting the number we can grab from DAO at once.
		
		return orgsDAO.findGroupsByOrgId(orgId);
	}
	
	/*
	 * Get a group from the database by the
	 * 	groups id
	 */
	public Group findGroupById(int id) {
		//TODO: Get this from the datatbase
		
		return orgsDAO.findGroupById(id);
	}
	
	public Organization findParentOrganization(int orgId) {
		return orgsDAO.findOrganization(orgId);
	}
}
