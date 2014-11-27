package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.Organization;

public class OrganizationView extends View {
	
	private Organization organization;
	
	public OrganizationView(Organization org) {
		super("org.ftl");
		this.organization = org;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	
	public Group[] getGroups() {
		return GroupManager.findGroupsByOrg(organization.getId());
	}
}
