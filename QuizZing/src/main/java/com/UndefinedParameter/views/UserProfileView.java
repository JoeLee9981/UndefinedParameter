package com.UndefinedParameter.views;

import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class UserProfileView extends View {
	
	private User userProf;
	private List<Quiz> userQuizzes;
	private boolean editable;
	
	public UserProfileView(String page) {
		super(page);
		// TODO: Is this okay?
		this.userProf = null;
	}
	
	public UserProfileView(String page, User userProf, List<Quiz> userQuizzes, boolean editable) {
		super(page);
		this.userProf = userProf;
		this.userQuizzes = userQuizzes;
		this.editable = editable;
	}
	
	public User getuserProf() {
		return userProf;
	}
	
	public List<Quiz> getUserQuizzes(){
		return userQuizzes;
	}
	
	public boolean isEditable(){
		return editable;
	}

}
