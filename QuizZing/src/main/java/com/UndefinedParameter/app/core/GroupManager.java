package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.UserGroupDAO;

/**
 * Group Manager object used for communication between resources
 * 		and the database
 *	This is responsible for handling any management needed on Groups
 */
public class GroupManager {

	/***** Database objects needed by this manager ****/
	
	private OrganizationDAO orgsDAO;
	private GroupDAO groupDAO;
	private OrgMemberDAO orgMemberDAO;
	private UserGroupDAO userGroupDAO;
	
	/*
	 * Paramterized constructor.
	 */
	public GroupManager(OrganizationDAO orgsDAO, GroupDAO groupDAO, OrgMemberDAO orgMemberDAO, UserGroupDAO userGroupDAO) {
		this.orgsDAO = orgsDAO;
		this.groupDAO = groupDAO;
		this.orgMemberDAO = orgMemberDAO;
		this.userGroupDAO = userGroupDAO;
	}
	
	/**
	 * Create a new group
	 * @param group the group to create
	 * @param user the user who is creating the group
	 * @return the creatd group id, -1 if creation failed
	 */
	public long addGroup(Group group, User user) {
		
		/*
		 * Important - The id of the group will not be instantiated
		 * 	group.id will be set to -1. This will be auto assigned by the
		 * 	database, therefore don't pass it in
		 */
		
		if(group.getOrganizationId() < 1 || group.getName() == null || group.getName().length() == 0) {
			//invalid ID
			return -1;
		}
		long rvalue = groupDAO.insertGroup(InputUtils.sanitizeInput(group.getName()), 
				InputUtils.sanitizeInput(group.getDescription()), 
				group.getOrganizationId());

		UserGroupManager usrGrpM = new UserGroupManager(userGroupDAO);
		userGroupDAO.insert(user.getId(), rvalue);
		usrGrpM.addPoints(user.getId(), rvalue, 99);
		
		//groupDAO.insert(userid.getId(), rvalue);

		//groupDAO.addInUserGroupEarnedPoints(rvalue, userid.getId(), 99);

		return rvalue;
	}
	
	/**
	 * Edit the group details
	 * @param group the new group details
	 * @return true if edit succeeds
	 */
	public boolean editGroup(Group group) {
		groupDAO.updateGroup(group.getId(), InputUtils.sanitizeInput(group.getName()), InputUtils.sanitizeInput(group.getDescription()));
		return true;
	}
	
	/*
	 * Find the groups within an organization by the organizations id
	 * 	return as an array of Group classes.
	 */
	public List<Group> findGroupsByOrg(long orgId) {
		
		
		return groupDAO.findGroupsByOrgId(orgId);
	}
	
	/*
	 * Get a group from the database by the
	 * 	groups id
	 */
	public Group findGroupById(long id) {
		
		return groupDAO.findGroupById(id);
	}
	
	/*
	 * Uses a quiz id to find a group from the database
	 */
	public Group findGroupByQuizId(long quizId) {
		
		return groupDAO.findGroupByQuizId(quizId);
	}
	
	/**
	 * Find the top groups
	 * @return a list of the top groups
	 */
	public List<Group> findTopGroups() {

		List<Group> groups = groupDAO.findTopGroups(); 
		
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(groupDAO.countQuestions(g.getId()));
			g.setQuizCount(groupDAO.countQuizzes(g.getId()));
		}
		
		return groups;
	}
	
	/*
	 * Return a list of top groups that a user is not registered for
	 */
	public List<Group> findUnregisteredTopGroups(long userId) {
		//TODO: Restrict this to prevent pulling 1000 entries from the DB
		List<Group> groups = groupDAO.findUnregisteredTopGroups(userId); 
		
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(groupDAO.countQuestions(g.getId()));
			g.setQuizCount(groupDAO.countQuizzes(g.getId()));
		}
		
		return groups;
	}
	
	/**
	 * Find all the groups a user is the member of
	 * @param userId the id of the user
	 * @return a list of all registred groups
	 */
	public List<Group> findRegisteredGroups(long userId) {
		
		List<Group> groups = groupDAO.findGroupsByUser(userId);
		
		for(int i = 0; i < groups.size(); i++) {
			Group g = groups.get(i);
			g.setQuestionCount(groupDAO.countQuestions(g.getId()));
			g.setQuizCount(groupDAO.countQuizzes(g.getId()));
		}
		
		return groups;
	}
	
	/**
	 * find the parent organization using its id
	 * @param orgId the id of the parent organization
	 * @return full organization details
	 */
	public Organization findParentOrganization(long orgId) {
		return orgsDAO.findOrganization(orgId);
	}
	
	/**
	 * Count the questions in a group
	 * @param groupId the group to find
	 * @return the count of questions found
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
	
	/**
	 * Count the flagged questions in a group
	 * @param groupId the group to find
	 * @return the count of flags found
	 */
	public int countFlagsByGroup(long groupId) {
		if(groupId < 1) {
			return 0;
		}
		return groupDAO.countFlagsByGroup(groupId);
	}
	
	/**
	 * count the flags for a user in a group
	 * @param userId the id of the user
	 * @param groupId the id of the group
	 * @return a count of the flags found
	 */
	public int countFlagsByuser(long userId, long groupId) {
		if(groupId < 1 || userId < 1) {
			return 0;
		}
		return groupDAO.countFlagsByUser(userId, groupId);
	}
	
	/**
	 * count the questions in a quiz
	 * @param groupId the group the quiz belongs to
	 * @return the count found
	 */
	public int countQuestionsByQuiz(long groupId) {
		try {
			int count = groupDAO.countQuizzes(groupId);
			return count;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * Find group members by a group id
	 * @param groupId is the id of the group
	 * @return
	 */
	public List<GroupMember> findGroupMembers(long groupId) {
		List<GroupMember> members = orgMemberDAO.retrieveMembersByGroup(groupId);
		
		for(GroupMember member: members) {
			member.setQuestions(groupDAO.countUserQuestions(groupId, member.getUserId()));
			member.setQuizzes(groupDAO.countUserQuizzes(groupId, member.getUserId()));
		}
		
		return members;
	}
	
	/**
	 * Find all the categories in a group
	 * @param groupId the group id
	 * @return a list of all categories as strings
	 */
	public List<String> findCategoriesByGroup(long groupId) {
		return groupDAO.findCategoriesByGroup(groupId);
	}

	/**
	 * Finds groups associated with the provided keywords
	 * @param keywords the words to search the groups for
	 * @return the list of matching groups
	 */
	public List<Group> findGroupsByKeywords(String keywords) {
		
		return groupDAO.findGroupsByKeywords(keywords);
	}
}
