package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.User;

public class HomeView extends View{

	private final NewsArticle[] newsList;
	private User user;
	
	public HomeView(String page, NewsArticle[] newsList) {
		super(page);
		this.newsList = newsList;
		this.user = null;
	}
	
	public HomeView(String page, NewsArticle[] newsList, User user) {
		super(page);
		this.newsList = newsList;
		this.user = user;
	}
	
	public NewsArticle[] getNews() {
		return newsList;
	}
	
	public User getUser() {
		return this.user;
	}
}