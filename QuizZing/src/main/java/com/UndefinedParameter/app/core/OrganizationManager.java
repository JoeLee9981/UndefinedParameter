package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;

public class OrganizationManager {
	
	private OrganizationDAO orgDAO;
	private GroupDAO groupDAO;
	
	public OrganizationManager(OrganizationDAO orgDAO, GroupDAO groupDAO) {
		this.orgDAO = orgDAO;
		this.groupDAO = groupDAO;
	}
	
	/*
	 * Find groups within an organization by id
	 * 		Category is the category the groups belong to
	 * 		for example CS or Computer Science. This would return
	 * 		all classes related to the CS category for the organization
	 */
	public Group[] findGroupsByCategory(long orgId, long categoryId) {
		//TODO: Implement - note may want to use helper methods to group id's
				// then retrieve the groups
		return null;
	}
	
	public Group[] findGroupsByCategory(long orgId, String category) {
		int catId = 0;//TODO: Find the category id from database
		return findGroupsByCategory(orgId, catId);
	}
	
	/*
	 * Finds all of the groups by an organizations id
	 */
	public List<Group> findGroupsById(long orgId) {
		return groupDAO.findGroupsByOrgId(orgId);
	}
	
	public List<Organization> findOrgsByLocation(String city) {
		
		return orgDAO.findOrganizations();
	}
	
	public Organization findOrgById(long id) {
		
		return orgDAO.findOrganization(id);
	}
	
	public long createOrganization(Organization org) {
		try {
			long id = orgDAO.insertOrganization(org.getName(), org.getDescription(), org.getCity(), org.getState(), org.getCountry());
			return id;
		}
		catch(Exception e) {
			return -1;
		}
	}
	
	
	/*
	 * This section is for UserOrganization which controls the association between user and organizations
	 * 	Includes register for organization
	 * 	Leave organization and return all organizations registered for
	 */
	
	/*
	 * find organizations that a user is registered by the user id
	 */
	public List<Organization> findOrgsByUser(User user) {
		//TODO: Get by user id registered
		return orgDAO.findOrganizations();
	}
	
	public List<Organization> findOrgsByUserId(long userId) {
		return orgDAO.findOrganizationsByUserId(userId);
	}
	
	public boolean registerOrganization(long orgId, long userId) {
		try {
			orgDAO.registerOrganization(orgId, userId);
			orgDAO.incrementOrgMembers(orgId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean leaveOrganization(long orgId, long userId) {
		try {
			orgDAO.removeUserOrganization(orgId, userId);
			orgDAO.decrementOrgMembers(orgId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/*
	 * 	Methods below here are for UserGroup registration
	 * 		includes ability to add a user into a group
	 * 		remove a user from a group
	 * 		retrieve all groups a user is registered by orgid
	 */
	
	
	/*
	 * Finds all of the groups by an organization id and user id
	 */
	public List<Group> findRegisteredGroupsById(long orgId, long userId) {
		return groupDAO.findGroupsByUser(userId, orgId);
	}
	
	public boolean registerUserForGroup(long groupId, long userId) {
		try {
			groupDAO.registerGroup(userId, groupId);
			groupDAO.incrementGroupMembers(groupId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean  removeuserFromGroupById(long groupId, long userId) {
		try {
			groupDAO.removeUserGroup(groupId, userId);
			groupDAO.decrementGroupMembers(groupId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
