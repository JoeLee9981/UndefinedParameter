package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class GroupCreatorView extends View{

	//parent organization of the group
	private Organization organization;
	private User user;
	
	public GroupCreatorView(User user, Organization org) {
		super("group_create.ftl");
		this.organization = org;
		this.user = user;
	}
	
	//return the organization
	public Organization getOrganization() {
		return this.organization;
	}
	
	public User getUser() {
		return user;
	}
}
