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
}
