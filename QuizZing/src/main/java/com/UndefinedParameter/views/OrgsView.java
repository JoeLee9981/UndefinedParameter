package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.UserManager;

public class OrgsView extends View {
	
	public OrgsView() {
		super("orgs.ftl");
	}
	
	public Organization[] getOrganizations(String city) {
		Organization[] orgs = OrganizationManager.findOrgsByLocation("city");
		return orgs;
	}
	
	public int getContributionScore(int orgId) {
		//TODO: get the user id
		return UserManager.retrieveContributionScore(0, orgId);
	}
	
	public int getQuizzesParticipated(int orgId) {
		//TODO; get the user id
		return UserManager.retreiveQuizzesParticipated(0, orgId);
	}
}
