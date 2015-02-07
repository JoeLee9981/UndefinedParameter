package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class OrgsView extends View {
	
	private User user;
	private ArrayList<Organization> orgs = null;
	private ArrayList<Organization> registeredOrgs = null;
	
	public OrgsView(List<Organization> orgs, List<Organization> registeredOrgs, User user) {
		super("orgs.ftl");
		this.orgs = (ArrayList<Organization>)orgs;
		this.registeredOrgs = (ArrayList<Organization>)registeredOrgs;
		this.user = user;
	}
	
	public ArrayList<Organization> getOrganizations() {
		return orgs;
	}
	
	public ArrayList<Organization> getRegisteredOrganizations() {
		return registeredOrgs;
	}
	
	public long getUserId() {
		return user.getId();
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
	
	public boolean isLoggedIn() {
		return user != null;
	}
}
