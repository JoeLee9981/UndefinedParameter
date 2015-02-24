package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.User;

public class AboutView extends View {

	List<NewsArticle> news;
	private User user;
	
	public AboutView(User user, List<NewsArticle> news) {
		super("about.ftl");
		this.news = news;
		this.user = user;
	}
	
	public List<NewsArticle> getNews() {
		return news;
	}
	
	public User getUser() {
		return user;
	}

}
