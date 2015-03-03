package com.UndefinedParameter.views;

import com.UndefinedParameter.app.core.User;

import io.dropwizard.views.View;

public class ForgotView extends View {
	
	private User userRecover;
	private String userEmail;
	
	public ForgotView(String page) {
		super(page);
		this.userRecover = null;
	}
	
	public ForgotView(String page, User userRecover) {
		super(page);
		this.userRecover = userRecover;
	}
	
	public ForgotView(String page, String email) {
		super(page);
		this.userEmail = email;
	}
	
	public User getUserRecover() {
		return userRecover;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
}
