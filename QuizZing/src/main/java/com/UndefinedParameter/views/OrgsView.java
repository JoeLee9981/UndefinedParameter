package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Organization;
//import com.UndefinedParameter.app.core.OrganizationType;
import com.UndefinedParameter.app.core.User;

public class OrgsView extends View {
	
	private User user;
	private ArrayList<Organization> orgs = null;
	private ArrayList<Organization> registeredOrgs = null;
	private ArrayList<Organization> newestOrgs = null;
	private ArrayList<Organization> largestOrgs = null;
	private ArrayList<Organization> orgTypes = null;
	
	public OrgsView(List<Organization> orgTypes, List<Organization> orgs, List<Organization> registeredOrgs, List<Organization> newestOrganizations, List<Organization> largestOrganizations,  User user) {
		super("orgs.ftl");
		this.orgs = (ArrayList<Organization>)orgs;
		this.registeredOrgs = (ArrayList<Organization>)registeredOrgs;
		this.newestOrgs = (ArrayList<Organization>)newestOrganizations;
		this.largestOrgs = (ArrayList<Organization>)largestOrganizations;
		this.orgTypes = (ArrayList<Organization>)orgTypes;
		this.user = user;
	}
	
	public ArrayList<Organization> getOrganizationTypes()
	{
		return orgTypes;
	}
	
	public ArrayList<Organization> getOrganizations() {
		return orgs;
	}
	
	public ArrayList<Organization> getRegisteredOrganizations() {
		return registeredOrgs;
	}
	
	public ArrayList<Organization> getNewestOrganizations() {
		return newestOrgs;
	}
	
	public ArrayList<Organization> getLargestOrganizations() {
		return largestOrgs;
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
	
	public User getUser() {
		return this.user;
	}	
}
