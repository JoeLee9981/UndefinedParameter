package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;

public class GroupListView extends View {
	
	private List<Group> groups;
	
	public GroupListView(String ftl, List<Group> groups) {
		super(ftl);
		this.groups = groups;


	}
	
	public List<Group> getGroups() {
		return groups;
	}

}

