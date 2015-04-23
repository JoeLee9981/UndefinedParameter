package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.UndefinedParameter.app.core.Badge;
import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class UserProfileView extends View {
	
	private User user;
	private User userProf;
	private List<Quiz> userQuizzes;
	private List<Group> userGroups;
	private List<Badge> groupBadges;
	private List<Badge> orgBadges;
	private boolean editable;
	
	public UserProfileView(String page) {
		super(page);

		this.userProf = null;
	}
	
	public UserProfileView(String page, User userProf, User user, List<Quiz> userQuizzes, List<Group> userGroups, boolean editable, List<Badge> groupBadges, List<Badge> orgBadges) {
		super(page);
		this.userProf = userProf;
		this.userQuizzes = userQuizzes;
		this.userGroups = userGroups;
		this.editable = editable;
		this.user = user;
		this.groupBadges = groupBadges;
		this.orgBadges = orgBadges;
	}
	
	public User getuserProf() {
		return userProf;
	}
	
	public String getDisplayName() {
		if(editable) {
			StringBuilder builder = new StringBuilder();
			builder.append(userProf.getFirstName());
			
			if(StringUtils.isNotBlank(userProf.getMiddleName())) {
				builder.append(" " + userProf.getMiddleName());
				builder.append(" ");
			}
			builder.append(userProf.getLastName());
			
			return builder.toString();
		}
		else {
			return userProf.getDisplayName();
		}
	}
	
	public List<Quiz> getUserQuizzes(){
		return userQuizzes;
	}
	
	public List<Group> getUserGroups(){
		return userGroups;
	}
	
	public boolean isEditable(){
		return editable;
	}
	
	public User getUser() {
		return user;
	}
	
	public int getGroupCount() {
		if(userGroups == null)
			return 0;
		return userGroups.size();
	}
	
	public int getQuizCount() {
		if(userQuizzes == null)
			return 0;
		return userQuizzes.size();
	}
	
	public List<Badge> getGroupBadges() {
		return this.groupBadges;
	}
	
	public List<Badge> getOrgBadges() {
		return this.orgBadges;
	}
	
	public int getBadgeCount() {
		int count = 0;
		if(groupBadges != null)
			count +=  groupBadges.size();
		if(orgBadges != null)
			count += orgBadges.size();
		return count;
	}

}
