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
	public Group[] findGroupsByCategory(int orgId, int categoryId) {
		//TODO: Implement - note may want to use helper methods to group id's
				// then retrieve the groups
		return null;
	}
	
	public Group[] findGroupsByCategory(int orgId, String category) {
		int catId = 0;//TODO: Find the category id from database
		return findGroupsByCategory(orgId, catId);
	}
	
	/*
	 * Finds all of the groups by an organizations id
	 */
	public List<Group> findGroupsById(int orgId) {
		return groupDAO.findGroupsByOrgId(orgId);
	}
	
	public List<Organization> findOrgsByLocation(String city) {
		
		return orgDAO.findOrganizations();
	}
	
	public List<Organization> findOrgsByUserId(long userId) {
		return orgDAO.findOrganizationsByUserId(userId);
	}
	
	public Organization findOrgById(int id) {
		
		return orgDAO.findOrganization(id);
	}
	
	public List<Organization> findOrgsByUser(User user) {
		//TODO: Get by user id registered
		return orgDAO.findOrganizations();
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
	
	public boolean registerOrganization(long orgId, long userId) {
		try {
			orgDAO.registerOrganization(orgId, userId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean leaveOrganization(long orgId, long userId) {
		try {
			orgDAO.removeUserOrganization(orgId, userId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
