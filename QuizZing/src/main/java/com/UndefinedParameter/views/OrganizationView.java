package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class OrganizationView extends View {
	
	private Organization organization;
	private boolean loggedIn;
	private List<Group> registeredGroups;
	private List<Group> groups;
	private User user;
	private int userRating;
	
	public OrganizationView(Organization org, List<Group> groups, List<Group> registeredGroups, boolean loggedIn, User user, int userRating) {
		super("org.ftl");
		this.loggedIn = loggedIn;
		this.organization = org;
		this.groups = groups;
		this.registeredGroups = registeredGroups;
		this.user = user;
		this.userRating = userRating;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public List<Group> getRegisteredGroups() {
		return registeredGroups;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public int getUserRating() {
		return userRating;
	}
}
