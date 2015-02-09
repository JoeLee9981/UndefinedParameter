package com.UndefinedParameter.views;

import com.UndefinedParameter.app.core.User;

import io.dropwizard.views.View;

public class OrganizationCreatorView extends View {

	private User user;
	
	public OrganizationCreatorView(User user) {
		super("organization_create.ftl");
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}
}
