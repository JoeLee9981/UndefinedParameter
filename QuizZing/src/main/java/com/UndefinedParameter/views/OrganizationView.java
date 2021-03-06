package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.OrgMember;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class OrganizationView extends View {
	
	private Organization organization;
	private boolean loggedIn;
	private List<Group> registeredGroups;
	private List<Group> groups;
	private List<Organization> registeredOrganizations;
	private List<OrgMember> members;
	private List<Quiz> quizzes;
	private User user;
	private int userRating;
	private boolean moderator;
	
	public OrganizationView(Organization org, List<Organization> registeredOrganizations, List<Group> groups, List<Group> registeredGroups, List<OrgMember> members, List<Quiz> quizzes, boolean loggedIn, User user, int userRating, boolean moderator) {
		super("org.ftl");
		this.loggedIn = loggedIn;
		this.registeredOrganizations = registeredOrganizations;
		this.organization = org;
		this.groups = groups;
		this.registeredGroups = registeredGroups;
		this.user = user;
		this.userRating = userRating;
		this.members = members;
		this.quizzes = quizzes;
		this.moderator = moderator;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public List<Group> getRegisteredGroups() {
		return registeredGroups;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public int getUserRating() {
		return userRating;
	}
	
	public List<OrgMember> getMembers() {
		return members;
	}
	
	public int getMemberCount() {
		if(members != null) {
			return members.size();
		}
		return 0;
	}
	
	public boolean getUserIsInOrganization()
	{
		for (Organization org: this.registeredOrganizations)
		{
			if (org.getId() == this.organization.getId())
			{
				return true;
			}
		}
		return false;
	}
	
	public int getGroupCount() {
		int count = 0;
		if(registeredGroups != null) {
			count += registeredGroups.size();
		}
		if(groups != null) {
			count += groups.size();
		}
		return count;
	}
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}
	
	public int getQuizCount() {
		if(quizzes == null) {
			return 0;
		}
		return quizzes.size();
	}
	
	public boolean isModerator() {
		return moderator;
	}
}
