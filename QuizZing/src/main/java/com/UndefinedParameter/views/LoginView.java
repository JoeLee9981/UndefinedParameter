package com.UndefinedParameter.views;

import io.dropwizard.views.View;

public class LoginView extends View {

	//  This needs to be null if no redirect is necessary as the .ftl file does a null check
	private String redirectUrl = null;
	
	public LoginView() {
		super("login.ftl");
	}
	
	public LoginView(String redirectUrl) {
		super("login.ftl");
		this.redirectUrl = redirectUrl;
	}
	
	public String getRedirectUrl()
	{
		return this.redirectUrl;
	}

}
