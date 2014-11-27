package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;

public class GroupView extends View {

	//the group itself
	private Group group;
	//the organization this group belongs to
	private Organization organization;
	
	public GroupView(Group group) {
		super("group.ftl");
		this.group = group;
		this.organization = findOrg(group.getId());
	}
	
	public Group getGroup() {
		return group;
	}
	
	
	public Organization getOrganization() {
		return organization;
	}
	
	/*
	 * Find the parent organization
	 * 		This is being set automatically from the constructor
	 */
	private Organization findOrg(int orgId) {
		return OrganizationManager.findOrgById(orgId);
	}
}
