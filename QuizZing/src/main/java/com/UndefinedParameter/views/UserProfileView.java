package com.UndefinedParameter.views;

import io.dropwizard.views.View;
import com.UndefinedParameter.app.core.User;

public class UserProfileView extends View {
	
	private User user;
	
	public UserProfileView(String page) {
		super(page);
		// TODO: Is this okay?
		this.user = null;
	}
	
	public UserProfileView(String page, User user) {
		super(page);
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

}
