package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Organization;

public class GroupCreatorView extends View{

	//parent organization of the group
	private Organization organization;
	
	public GroupCreatorView(Organization org) {
		super("group_create.ftl");
		this.organization = org;
	}
	
	//return the organization
	public Organization getOrganization() {
		return this.organization;
	}
}
