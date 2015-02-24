package com.UndefinedParameter.views;

import com.UndefinedParameter.app.core.User;

import io.dropwizard.views.View;

public class FeedbackView extends View {
	
	private User user;
	
	public FeedbackView(User user) {
		super("feedback.ftl");
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
