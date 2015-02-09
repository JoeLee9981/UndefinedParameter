package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.NewsArticle;

public class AboutView extends View {

	List<NewsArticle> news;
	
	public AboutView(List<NewsArticle> news) {
		super("about.ftl");
		this.news = news;
	}
	
	public List<NewsArticle> getNews() {
		return news;
	}

}
