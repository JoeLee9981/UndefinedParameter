package com.UndefinedParameter.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.NewsManager;
import com.UndefinedParameter.jdbi.NewsArticleDAO;
import com.UndefinedParameter.views.NewsArticleView;


@Path("/news")
@Produces(MediaType.TEXT_HTML)
public class NewsArticleResource {
	
	private NewsArticleDAO newsDAO;
	private NewsManager newsManager;
	
	public NewsArticleResource(NewsArticleDAO newsDAO) {
		this.newsDAO = newsDAO;
		this.newsManager = new NewsManager(newsDAO);
	}
	
	@GET
	public NewsArticleView getNewsArticleView(@QueryParam("id") int id) {
		NewsArticle news = newsManager.getNewsById(id);
		return new NewsArticleView(news);
	}
}
