package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;

public class GroupView extends View {

	private Group group;
	
	public GroupView(Group group) {
		super("group.ftl");
		this.group = group;
	}
	
	public Group getGroup() {
		return group;
	}
}
