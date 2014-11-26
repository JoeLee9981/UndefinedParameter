package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;

public class OrgsView extends View {
	
	public OrgsView() {
		super("orgs.ftl");
	}
	
	public Organization[] getOrganizations(String city) {
		Organization[] orgs = OrganizationManager.findOrgsByLocation("city");
		return orgs;
	}
	
}
