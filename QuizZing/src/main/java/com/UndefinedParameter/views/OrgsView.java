package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;

public class OrgsView extends View {
	
	ArrayList<Organization> orgs;
	
	public OrgsView(List<Organization> orgs) {
		super("orgs.ftl");
		this.orgs = (ArrayList<Organization>)orgs;
	}
	
	public ArrayList<Organization> getOrganizations() {
		return orgs;
	}
	
	public int getContributionScore(int orgId) {
		//TODO: get the user id
		//return UserManager.retrieveContributionScore(0, orgId);
		return 100;
	}
	
	public int getQuizzesParticipated(int orgId) {
		//TODO; get the user id
		//return UserManager.retreiveQuizzesParticipated(0, orgId);
		return 10;
	}
}
