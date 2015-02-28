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
	
	public List<OrganizationType> findAllOrganizationTypes()
	{
		List<OrganizationType> x = orgDAO.findAllOrganizationTypes();
		return x;
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
		
		List<Group> groups = groupDAO.findGroupsByOrgId(orgId);
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(countQuestionsByGroup(g.getId()));
			g.setQuizCount(countQuizzesByGroup(g.getId()));
		}
		return groups;
	}
	
	public List<Organization> findOrgsByLocation(String city) {
		
		List<Organization> orgs = orgDAO.findOrganizations();
		for(int i = 0; i < orgs.size(); i++) {
			Organization org = orgs.get(i);
			org.setQuestionCount(orgDAO.countQuestions(org.getId()));
			org.setQuizCount(orgDAO.countQuizzes(org.getId()));
		}
		
		return orgs;
	}
	
	/*
	 * Returns a list of unregistered organizations for a user
	 */
	public List<Organization> findUnregisteredOrgs(long userId) {
		List<Organization> orgs = orgDAO.findUnregisteredOrganizations(userId);
		
		for(int i = 0; i < orgs.size(); i++) {
			Organization org = orgs.get(i);
			org.setQuestionCount(orgDAO.countQuestions(org.getId()));
			org.setQuizCount(orgDAO.countQuizzes(org.getId()));
		}
		
		return orgs;
	}
	
	/*
	 * Returns a list of unregistered Groups for a user
	 */
	public List<Group> findUnregisteredGroupsByOrg(long userId, long orgId) {
		List<Group> groups = groupDAO.findUnregisteredGroupsByUser(userId, orgId);
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(countQuestionsByGroup(g.getId()));
			g.setQuizCount(countQuizzesByGroup(g.getId()));
		}
		return groups;
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
	
	/* Find the newest organizations sorted.  Return rows from StartCount to EndCount */
	public List<Organization> findNewestOrganizations(int startCount, int endCount)
	{
		return orgDAO.findNewestOrganizations(startCount, endCount);
	}
	
	/* Find the largest organizations sorted.  Return rows from StartCount to EndCount */
	public List<Organization> findLargestOrganizations(int startCount, int endCount)
	{
		return orgDAO.findLargestOrganizations(startCount, endCount);
	}
	
	public List<Organization> findOrgsByUserId(long userId) {
		List<Organization> orgs = orgDAO.findOrganizationsByUserId(userId);
		
		for(int i = 0; i < orgs.size(); i++) {
			Organization org = orgs.get(i);
			org.setQuestionCount(orgDAO.countQuestions(org.getId()));
			org.setQuizCount(orgDAO.countQuizzes(org.getId()));
		}
		
		return orgs; 
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
		
		List<Group> groups = groupDAO.findGroupsByUser(userId, orgId);
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(countQuestionsByGroup(g.getId()));
			g.setQuizCount(countQuizzesByGroup(g.getId()));
		}
		return groups;
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
	
	/*
	 * Count the questions inside of a group by the group id
	 */
	public int countQuestionsByGroup(long groupId) {
		try {
			int count = groupDAO.countQuestions(groupId);
			return count;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	/*
	 * Count the quizzes belonging to a group by the group id
	 */
	public int countQuizzesByGroup(long groupId) {
		try {
			int count = groupDAO.countQuizzes(groupId);
			return count;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	/*
	 * Count quizzes in all groups of an organization
	 */
	public int countQuizzesByOrg(long orgId) {
		try {
			return orgDAO.countQuizzes(orgId);
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	/*
	 * Count questions in all groups of an organization
	 */
	public int countQuestionsByOrg(long orgId) {
		try {
			return orgDAO.countQuestions(orgId);
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public boolean rateOrganization(long userId, long orgId, int rating) {
		//Goes to mananger
		if(userId < 1 || orgId < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = orgDAO.getOrganizationRating(orgId);
			int existingRatingAmount = orgDAO.getOrganizationRatingCount(orgId);
			int existingUserRating = orgDAO.getUserOrganizationRating(orgId, userId);
			
			if(existingUserRating == 0)
			{
				existingRatingAmount = existingRatingAmount + 1;
				existingRating = existingRating + rating;
				
				orgDAO.updateUserOrgRating(orgId, userId, rating);
				orgDAO.updateOrganizationRating(orgId, existingRating);
				orgDAO.updateOrganizationRatingCount(orgId);
				
				//orgDAO.updateQuizRating(userId, orgId, rating);
				//orgDAO.updateQuizQualityRating(rating - existingRating, orgId);
			}
			else
			{
				int newrating = rating - existingRating;
				existingRating = existingRating + newrating;
				
				orgDAO.updateOrganizationRating(orgId, newrating);
				orgDAO.updateUserOrgRating(orgId, userId, rating);
			}
			return true;
		}
		catch(Exception e)
		{
			//database insert fails
			return false;
		}
	}
}
