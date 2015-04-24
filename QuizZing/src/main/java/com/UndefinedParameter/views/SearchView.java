package com.UndefinedParameter.views;

import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class SearchView extends View
{
	private User user;
	private String searchString;
	private List<Organization> orgResults;
	private List<Quiz> quizResults;
	private List<Group> groupResults;
	
	public SearchView(User user, String searchString, List<Organization> orgResults, List<Quiz> quizResults, List<Group> groupResults) {
		super("search_results.ftl");
		this.user = user;
		this.searchString = searchString;
		this.orgResults = orgResults;
		this.quizResults = quizResults;
		this.groupResults = groupResults;
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
	
	public List<Quiz> getQuizResults()
	{
		return quizResults;
	}
	
	public List<Group> getGroupResults()
	{
		return groupResults;
	}
	
}
