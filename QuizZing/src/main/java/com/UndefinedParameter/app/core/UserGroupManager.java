package com.UndefinedParameter.app.core;

import com.UndefinedParameter.jdbi.UserGroupDAO;

public class UserGroupManager {

	
	private UserGroupDAO usergroupDAO;
	
	public UserGroupManager(UserGroupDAO userDAO) {
		this.usergroupDAO = userDAO;
	}
	
	public boolean registerNewUserGroup(int user, int group) throws Exception {
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
	
}
