package com.UndefinedParameter.views;

import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class SearchView extends View
{
	private User user;
	private String searchString;
	private List<Organization> orgResults;
	
	public SearchView(User user, String searchString, List<Organization> orgResults) {
		super("search_results.ftl");
		this.user = user;
		this.searchString = searchString;
		this.orgResults = orgResults;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public String getSearchString()
	{
		return searchString;
	}
	
	public List<Organization> getOrgResults()
	{
		return orgResults;
	}
	
}
