package com.UndefinedParameter.app.core;

//import com.UndefinedParameter.jdbi.UserGroupDAO;
import com.UndefinedParameter.jdbi.GroupDAO;

public class UserGroupManager {

	
	private GroupDAO usergroupDAO;
	
	public UserGroupManager(GroupDAO userDAO) {
		this.usergroupDAO = userDAO;
	}
	
	/*public boolean registerNewUserGroup(int user, int group) throws Exception {
		try {
			usergroupDAO.insert(user, group);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean deleteNewUserGroup(int user, int group) throws Exception {
		try {
			usergroupDAO.delete(user, group);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	public UserGroup findUserByUserId(int userName) {
		if(usergroupDAO == null)
			return null;
		
		//TODO: Fix this
		return null;
		//return usergroupDAO.findUserByUserId(userName);
	}
	
	public UserGroup findUserByGroupId(int groupid) {
		if(usergroupDAO == null)
			return null;
		
		//TODO: Fix this
		return null;
		//return usergroupDAO.findUserByGroupId(groupid);
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
	} */
	
	/*
	 * Whatever, 		Option = 0: 1 point
	 * Took a quiz, 	Option = 1: 2 points.
	 * Made a comment,	Option = 2: 3 point.
	 * Made a quiz, 	Option = 3: 5 points.
	 * Made the group,	Option = 99: 300 points.
	 * More later.
	 * */
	/*public int addPoints(long userID, long groupID, int option)
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
			points = 2;
		}
		else if(option == 2)
		{
			points = 3;
		}
		else if(option == 3)
		{
			points = 5;
		}
		else
		{
			points = 1;
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
	} */
	
	/*
	 * Take a quiz, 2 points.
	 * Make a quiz, 5 points.
	 * Makes a comment, 1 point.
	 * More later.
	 * */
	/*public int checkModWhileRemovingUser(long userID, long groupID)
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
		usergroupDAO.updateUserGroupModStatus(groupID, newUserID, 1);
		usergroupDAO.delete(userID, groupID);
		
		return 0;
	} */
}
