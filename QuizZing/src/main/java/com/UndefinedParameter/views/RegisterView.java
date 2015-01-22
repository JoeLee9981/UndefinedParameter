package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.User;

public class RegisterView extends View {

	private User user;
	
	public RegisterView(User user) {
		super("register.ftl");
		this.user = user;	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
