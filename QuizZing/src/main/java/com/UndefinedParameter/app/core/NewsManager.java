package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.NewsArticleDAO;

public class NewsManager {

	protected NewsArticleDAO newsDAO;
	
	public NewsManager(NewsArticleDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	/*
	 * This returns selected news articles based on recent
	 * 	News to the system. It is intended for use on the home
	 * 	page before a user is logged in
	 */
	public List<NewsArticle> getRecentNews() {
		//TODO: Format and choose the news to return
		return newsDAO.findAllNews();
	}
	
	
	public List<NewsArticle> getRecentNewsByUser(long userId) {
		//TODO: Select news by user id, format and return
		return newsDAO.findAllNews();
	}
	
	public NewsArticle getNewsById(int newsId) {
		return newsDAO.findNewsById(newsId);
	}
}
