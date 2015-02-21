package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;

public class GroupManager {

	private OrganizationDAO orgsDAO;
	private GroupDAO groupDAO;
	
	public GroupManager(OrganizationDAO orgsDAO, GroupDAO groupDAO) {
		this.orgsDAO = orgsDAO;
		this.groupDAO = groupDAO;
	}
	
	public long addGroup(Group group) {
		
		/*
		 * Important - The id of the group will not be instantiated
		 * 	group.id will be set to -1. This will be auto assigned by the
		 * 	database, therefore don't pass it in
		 */
		
		if(group.getOrganizationId() < 1 || group.getName() == null || group.getName().length() == 0) {
			//invalid ID
			return -1;
		}
		return groupDAO.insertGroup(group.getName(), group.getDescription(), group.getOrganizationId());
	}
	
	/*
	 * Find the groups within an organization by the organizations id
	 * 	return as an array of Group classes.
	 */
	public List<Group> findGroupsByOrg(long orgId) {
		
		//TODO: get groups from database - this should be ok to retreive a complete listing
		//		as not there should be a manageable number of groups inside an org, however,
		//		we may want to consider limiting the number we can grab from DAO at once.
		
		return groupDAO.findGroupsByOrgId(orgId);
	}
	
	/*
	 * Get a group from the database by the
	 * 	groups id
	 */
	public Group findGroupById(long id) {
		//TODO: Get this from the datatbase
		
		return groupDAO.findGroupById(id);
	}
	
	public List<Group> findTopGroups() {
		//TODO: Restrict this to prevent pulling 1000 entries from the DB
		List<Group> groups = groupDAO.findTopGroups(); 
		
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(groupDAO.countQuestions(g.getId()));
			g.setQuizCount(groupDAO.countQuizzes(g.getId()));
		}
		
		return groups;
	}
	
	public List<Group> findRegisteredGroups(long userId) {
		
		List<Group> groups = groupDAO.findGroupsByUser(userId);
		
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(groupDAO.countQuestions(g.getId()));
			g.setQuizCount(groupDAO.countQuizzes(g.getId()));
		}
		
		return groups;
	}
	
	public Organization findParentOrganization(long orgId) {
		return orgsDAO.findOrganization(orgId);
	}
	
	public int countQuestionsByGroup(long groupId) {
		try {
			int count = groupDAO.countQuestions(groupId);
			return count;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public int countQuestionsByQuiz(long groupId) {
		try {
			int count = groupDAO.countQuizzes(groupId);
			return count;
		}
		catch(Exception e) {
			return 0;
		}
	}
}
