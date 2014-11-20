package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.NewsArticle;

public class NewsArticleView extends View {
	
private final NewsArticle article;
	
	public NewsArticleView(NewsArticle article) {
		super("home.ftl");
		this.article = article;
	}
	
	public NewsArticle getNews() {
		return article;
	}
}
