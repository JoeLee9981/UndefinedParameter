package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.UserGroupDAO;

public class UserGroupManager {

	
	private UserGroupDAO usergroupDAO;
	
	public UserGroupManager(UserGroupDAO userDAO) {
		this.usergroupDAO = userDAO;
	}	
	
	public UserGroup findUserByUserId(int userName) {
		if(usergroupDAO == null)
			return null;
		
		return usergroupDAO.findUserByUserId(userName);
	}
	
	public UserGroup findUserByGroupId(int groupid) {
		if(usergroupDAO == null)
			return null;
		
		return usergroupDAO.findUserByGroupId(groupid);
	}
	
	
	
	
	public boolean rateGroup(long userId, long groupID, int rating) {
		//Goes to mananger
		if(userId < 1 || groupID < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = usergroupDAO.getSubGroupRating(groupID);
			int existingRatingAmount = usergroupDAO.updateSubGroupRatingCount(groupID);
			int existingUserRating = usergroupDAO.getUserGroupRating(groupID, userId);
			
			if(existingUserRating == 0)
			{
				existingRatingAmount = existingRatingAmount + 1;
				existingRating = existingRating + rating;
				
				usergroupDAO.updateUserGroupRating(groupID, userId, rating);
				
				usergroupDAO.updateSubGroupRating(groupID, existingRating);
				usergroupDAO.updateSubGroupRatingCount(groupID);
				
				UserGroupManager usrGrpM = null;
				usrGrpM.addPoints(userId, groupID, 9);
				//orgDAO.updateQuizRating(userId, orgId, rating);
				//orgDAO.updateQuizQualityRating(rating - existingRating, orgId);
			}
			else
			{
				int newrating = rating - existingRating;
				existingRating = existingRating + newrating;
				
				usergroupDAO.updateUserGroupRating(groupID, userId, rating);
				
				usergroupDAO.updateSubGroupRating(groupID, newrating);
				
			}
			return true;
		}
		catch(Exception e)
		{
			//database insert fails
			return false;
		}
	}
	
	
	public boolean findIfUserMod(long userID, long groupID)
	{
		if(userID < 0 || groupID < 0)
		{
			return false;
		}
		//0 is false, 1 is true
		int result = usergroupDAO.getUserGroupModStatus(groupID, userID);
		
		if(result == 1)
		{
			return true;
		}
		
		return false;
	}
	
	public List<Long> findModsInGroup(long groupID)
	{
		List<Long> found = null;
		
		List<Long> input = usergroupDAO.getUsersBasedOnModStatus(groupID, 1);
				
		return input;
	}
	
	public List<Long> findNOTModsInGroup(long groupID)
	{
		List<Long> found = null;
		
		List<Long> input = usergroupDAO.getUsersBasedOnModStatus(groupID, 0);
				
		return input;
	}
	
	public List<Long> findGroupsUserIsMod(long userID)
	{
		List<Long> found = null;
		
		List<Long> input = usergroupDAO.getGroupsBasedOnModStatus(userID, 1);
				
		return input;
	}
	
	public List<Long> findGroupsUserIsNOTMod(long userID)
	{
		List<Long> found = null;
		
		List<Long> input = usergroupDAO.getGroupsBasedOnModStatus(userID, 0);
				
		return input;
	}
	
	/**
	 * Option-Points:
	 * 	1-1	Creating a question.
	 *	2-1	Rating a question.
	 *	3-3	When people give Feedback to question.
	 *	4-1	When people say how difficult the question is.
	 *
	 *	5-2	Creating a quiz.
	 *	6-2	Rating a quiz.
	 *	7-6	When people give Feedback to quiz.
	 *	8-2	When people say how difficult the quiz is.
	 *
	 *	9-10 Rate the group (Should be a one time only thing)
	 * 	99-300	Made the group.
	 * 
	 * @param userID
	 * @param groupID
	 * @param option
	 * @return
	 */
	public int addPoints(long userID, long groupID, int option)
	{
		long points;
		if(userID < 0 || groupID < 0)
		{
			return -1;
		}
		
		if(option == 99)
		{
			points = 300;
		}
		else if(option == 1)
		{
			points = 1;
		}
		else if(option == 2)
		{
			points = 1;
		}
		else if(option == 3)
		{
			points = 3;
		}
		else if(option == 4)
		{
			points = 1;
		}
		else if(option == 5)
		{
			points = 2;
		}
		else if(option == 6)
		{
			points = 2;
		}
		else if(option == 7)
		{
			points = 6;
		}
		else if(option == 8)
		{
			points = 2;
		}
		else if(option == 9)
		{
			points = 10;
		}
		else
		{
			points = 0;
		}
		
		usergroupDAO.addInUserGroupEarnedPoints(groupID, userID, points);
		
		//0 is false, 1 is true
		int result = usergroupDAO.getUserGroupModStatus(groupID, userID);
		int totalpoints = usergroupDAO.getUserGroupEarnedPoints(groupID, userID);
		
		if(totalpoints >= 300 && result == 0)
		{
			usergroupDAO.updateUserGroupModStatus(groupID, userID, 1);
		}
		else if(totalpoints < 300 && result == 1)
		{
			int totalmods = usergroupDAO.getTotalUserGroupModStatus(groupID, 1);
			if(totalmods == 1)
			{
				
			}
			else
			{
				usergroupDAO.updateUserGroupModStatus(groupID, userID, 0);
			}
		}
		
		return 0;
	} 
	

	public int checkModWhileRemovingUser(long userID, long groupID)
	{
		if(userID < 0 || groupID < 0)
		{
			return -1;
		}
		
		
		//0 is false, 1 is true
		int result = usergroupDAO.getUserGroupModStatus(groupID, userID);
		int totalmembers = usergroupDAO.getTotalUserGroup(groupID);
		if(result == 0)
		{
			usergroupDAO.delete(userID, groupID);
			if(totalmembers == 1)
			{
				//Find out if delete group when no users left. For now, no.
			}
			return 0;
		}
		
		int totalmods = usergroupDAO.getTotalUserGroupModStatus(groupID, 1);
		
		if(totalmods > 1)
		{
			usergroupDAO.delete(userID, groupID);
			return 0;
		}
		
		
		if(totalmembers == 1)
		{
			//Find out if delete group when no users left. For now, no.
		}
		
		int maxPoints = usergroupDAO.getMaxPointsUserGroup(groupID);
		int secondMaxPoints = usergroupDAO.getSecondMaxPointsUserGroup(groupID, maxPoints);
		
		int newUserID = usergroupDAO.getUserByPointsAndGroup(groupID, secondMaxPoints);
	
		if(secondMaxPoints < 300)
		{
			int remainderpoints = 300 - secondMaxPoints;
			usergroupDAO.addInUserGroupEarnedPoints(groupID, newUserID, remainderpoints);
		}
		usergroupDAO.updateUserGroupModStatus(groupID, newUserID, 1);
		usergroupDAO.delete(userID, groupID);
		
		return 0;
	}
}
