package com.UndefinedParameter.views;

import com.UndefinedParameter.app.core.User;

import io.dropwizard.views.View;

public class LoginView extends View {

	//  This needs to be null if no redirect is necessary as the .ftl file does a null check
	private String redirectUrl = null;
	private User user;
	
	public LoginView(User user) {
		super("login.ftl");
		this.user = user;
	}
	
	public LoginView(String redirectUrl) {
		super("login.ftl");
		this.redirectUrl = redirectUrl;
	}
	
	public String getRedirectUrl()
	{
		return this.redirectUrl;
	}
	
	public User getUser() {
		return user;
	}

}
