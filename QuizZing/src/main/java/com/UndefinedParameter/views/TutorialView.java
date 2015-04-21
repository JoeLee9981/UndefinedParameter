package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.User;

/**
 * Creates the html view for the tutorial page
 *
 */
public class TutorialView extends View {

	private User user;
	
	/**
	 * Constructor
	 * @param user
	 */
	public TutorialView(User user) {
		super("tutorial.ftl");
		this.user = user;
	}
	
	/**
	 * Get a user, null if not logged in
	 * @return
	 */
	public User getUser() {
		return user;
	}
}
