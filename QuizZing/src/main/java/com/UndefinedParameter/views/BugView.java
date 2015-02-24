package com.UndefinedParameter.views;

import com.UndefinedParameter.app.core.User;

import io.dropwizard.views.View;

public class BugView extends View{

	private User user;
	
	public BugView(User user) {
		super("bug.ftl");
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
