package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class GroupEditView extends View{

	//parent organization of the group
	private Organization organization;
	private Group group;
	private User user;
	
	public GroupEditView(User user, Organization org, Group group) {
		super("group_edit.ftl");
		this.organization = org;
		this.user = user;
		this.group = group;
	}
	
	//return the organization
	public Organization getOrganization() {
		return this.organization;
	}
	
	public User getUser() {
		return user;
	}
	
	public Group getGroup() {
		return group;
	}
}
