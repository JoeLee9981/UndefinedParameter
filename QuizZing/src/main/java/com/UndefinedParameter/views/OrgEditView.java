package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class OrgEditView extends View {

	private User user;
	private Organization organization;
	
	public OrgEditView(User user, Organization org) {
		super("org_edit.ftl");
		this.user = user;
		this.organization = org;
	}
	
	public User getUser() {
		return user;
	}
	
	public Organization getOrganization() {
		return organization;
	}
}
