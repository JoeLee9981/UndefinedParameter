package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class HomeView extends View{

	private final NewsArticle[] newsList;
	private User user;
	private ArrayList<Organization> registeredOrgs = null;
	private List<Quiz> topQuizzes;
	private List<Group> topGroups;
	private List<Quiz> recentQuizzes;
	private List<String> topCategories;
	
	public HomeView(String page, NewsArticle[] newsList, List<Quiz> topQuizzes, List<Quiz> recentQuizzes, List<Group> topGroups, List<String> topCategories) {
		super(page);
		this.newsList = newsList;
		this.user = null;
		this.recentQuizzes = recentQuizzes;
		this.topQuizzes = topQuizzes;
		this.topGroups = topGroups;
		this.topCategories = topCategories;
	}
	
	public HomeView(String page, NewsArticle[] newsList, User user, List<Organization> registeredOrgs, List<Quiz> topQuizzes, List<Quiz> recentQuizzes, List<Group> topGroups, List<String> topCategories) {
		super(page);
		this.newsList = newsList;
		this.user = user;
		this.registeredOrgs = (ArrayList<Organization>)registeredOrgs;
		this.recentQuizzes = recentQuizzes;
		this.topQuizzes = topQuizzes;
		this.topGroups = topGroups;
		this.topCategories = topCategories;
	}
	
	public NewsArticle[] getNews() {
		return newsList;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public ArrayList<Organization> getRegisteredOrganizations() {
		return registeredOrgs;
	}
	
	public List<Quiz> getTopQuizzes() {
		if(topQuizzes != null && topQuizzes.size() > 0) {
			return topQuizzes;
		}
		return null;
	}
	
	public List<Quiz> getRecentQuizzes() {
		if(recentQuizzes != null && recentQuizzes.size() > 0) {
			return recentQuizzes;
		}
		return null;
	}
	
	public List<Group> getTopGroups() {
		if(topGroups != null && topGroups.size() > 0) {
			return topGroups;
		}
		return null;
	}
	
	public List<String> getTopCategories() {
		if(topCategories != null && topCategories.size() > 0) {
			return topCategories;
		}
		return null;
	}
}