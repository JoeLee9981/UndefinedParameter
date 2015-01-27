package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.GroupManager;
import com.UndefinedParameter.app.core.Organization;

public class OrganizationView extends View {
	
	private Organization organization;
	private List<Group> groups;
	
	public OrganizationView(Organization org, List<Group> groups) {
		super("org.ftl");
		this.organization = org;
		this.groups = groups;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
}
