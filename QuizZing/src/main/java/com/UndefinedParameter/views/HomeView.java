package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.NewsArticle;

public class HomeView extends View{

	private final NewsArticle[] newsList;
	
	public HomeView(NewsArticle[] newsList) {
		super("home.ftl");
		this.newsList = newsList;
	}
	
	public NewsArticle getNews() {
		return newsList[0];
	}
}