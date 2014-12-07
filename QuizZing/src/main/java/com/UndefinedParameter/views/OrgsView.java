package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.UserManager;

public class OrgsView extends View {
	
	public OrgsView() {
		super("orgs.ftl");
	}
	
	public ArrayList<Organization> getOrganizations(String city) {
		ArrayList<Organization> orgs = OrganizationManager.findOrgsByLocation("city");
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
