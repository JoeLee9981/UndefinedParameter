package com.UndefinedParameter.views;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class UserProfileView extends View {
	
	private User user;
	private User userProf;
	private List<Quiz> userQuizzes;
	private List<Group> userGroups;
	private boolean editable;
	
	public UserProfileView(String page) {
		super(page);
		// TODO: Is this okay?
		this.userProf = null;
	}
	
	public UserProfileView(String page, User userProf, User user, List<Quiz> userQuizzes, List<Group> userGroups, boolean editable) {
		super(page);
		this.userProf = userProf;
		this.userQuizzes = userQuizzes;
		this.userGroups = userGroups;
		this.editable = editable;
		this.user = user;
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

}
