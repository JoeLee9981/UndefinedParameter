package com.UndefinedParameter.views;

import io.dropwizard.views.View;
import com.UndefinedParameter.app.core.User;

public class UserProfileView extends View {
	
	private User user;
	
	public UserProfileView(User user) {
		super("user_profile.ftl");
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

}
