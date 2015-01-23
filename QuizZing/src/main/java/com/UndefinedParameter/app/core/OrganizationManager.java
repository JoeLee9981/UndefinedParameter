package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.OrganizationDAO;

public class OrganizationManager {
	
	private OrganizationDAO orgDAO;
	
	public OrganizationManager(OrganizationDAO orgDAO) {
		this.orgDAO = orgDAO;
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
		return orgDAO.findGroupsByOrgId(orgId);
	}
	
	public List<Organization> findOrgsByLocation(String city) {
		
		return orgDAO.findOrganizations();
	}
	
	public Organization findOrgById(int id) {
		
		return orgDAO.findOrganization(id);
	}
}
