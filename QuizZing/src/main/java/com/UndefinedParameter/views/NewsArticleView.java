package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.User;

public class NewsArticleView extends View {
	
private final NewsArticle article;
	
	private User user;

	public NewsArticleView(User user, NewsArticle article) {
		super("news.ftl");
		this.article = article;
		this.user = user;
	}
	
	public NewsArticle getNews() {
		return article;
	}
	
	public User getUser() {
		return user;
	}
}
