package com.UndefinedParameter.views;

import java.util.ArrayList;
import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

public class HomeView extends View{

	private final NewsArticle[] newsList;
	private User user;
	private ArrayList<Organization> registeredOrgs = null;
	
	public HomeView(String page, NewsArticle[] newsList) {
		super(page);
		this.newsList = newsList;
		this.user = null;
	}
	
	public HomeView(String page, NewsArticle[] newsList, User user, List<Organization> registeredOrgs) {
		super(page);
		this.newsList = newsList;
		this.user = user;
		this.registeredOrgs = (ArrayList<Organization>)registeredOrgs;
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
}