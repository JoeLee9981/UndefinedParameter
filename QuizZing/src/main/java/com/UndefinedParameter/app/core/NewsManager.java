package com.UndefinedParameter.app.core;

import java.util.List;

import com.UndefinedParameter.jdbi.NewsArticleDAO;

/**
 * This is mostly deprecated, however, the about page is still
 * 		using queries provided here.
 * 
 * This is the manager for news
 *
 */
public class NewsManager {

	/*** Database object *****/
	protected NewsArticleDAO newsDAO;
	
	//construtor
	public NewsManager(NewsArticleDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	/*
	 * This returns selected news articles based on recent
	 * 	News to the system. It is intended for use on the home
	 * 	page before a user is logged in
	 */
	public List<NewsArticle> getRecentNews() {
		
		return newsDAO.findAllNews();
	}
	
	/**
	 * DEPRECATED NOT BEING USED
	 * @param userId
	 * @return
	 */
	public List<NewsArticle> getRecentNewsByUser(long userId) {
		
		return newsDAO.findAllNews();
	}
	
	/**
	 * DEPRECATED NOT BEING USED
	 * @param newsId
	 * @return
	 */
	public NewsArticle getNewsById(int newsId) {
		return newsDAO.findNewsById(newsId);
	}
}
