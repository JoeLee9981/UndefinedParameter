package com.UndefinedParameter.views;

import java.util.ArrayList;
import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class QuizCreatorView extends View
{
	public List<Organization> joinedOrganizations;	
	public List<Group> joinedGroupsInOrganization;
	public List<String> categories;
	public Group group;
	private User user;
	
	public QuizCreatorView(User user, List<Organization> joinedOrganizations, List<Group> joinedGroupsInOrganization, Group group, List<String> categories)
	{
		super("quiz_create.ftl");
		this.user = user;
		this.joinedOrganizations = joinedOrganizations;
		this.joinedGroupsInOrganization = joinedGroupsInOrganization;
		this.categories = categories;
		this.group = group;
		
	}
	
	public User getUser()
	{
		return user;
	}
	
	public List<Organization> getJoinedOrganizations()
	{
		if(joinedOrganizations != null && joinedOrganizations.size() == 0)
			return null;
		return this.joinedOrganizations;
	}
	
	public Group getGroup()
	{
		return this.group;
	}
	
	public List<Group> getJoinedGroupsInOrganization()
	{
		if(joinedGroupsInOrganization != null && joinedGroupsInOrganization.size() == 0)
			return null;
		return this.joinedGroupsInOrganization;
	}
	
	public List<String> getCategories() {
		return this.categories;
	}

}
