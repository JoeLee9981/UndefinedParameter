package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;

public class GroupsView extends View {
	
	private List<Group> topGroups;
	private List<Group> userGroups;
	private boolean loggedIn;
	
	public GroupsView(List<Group> topGroups, List<Group> userGroups, boolean loggedIn) {
		super("groups.ftl");
		this.topGroups = topGroups;
		this.userGroups = userGroups;
		this.loggedIn = loggedIn;
	}
	
	public List<Group> getTopGroups() {
		return topGroups;
	}
	
	public List<Group> getUserGroups() {
		return userGroups;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
}

