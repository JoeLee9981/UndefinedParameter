package com.UndefinedParameter.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.jdbi.NewsArticleDAO;
import com.UndefinedParameter.views.NewsArticleView;


@Path("/news/{id}")
@Produces(MediaType.TEXT_HTML)
public class NewsArticleResource {
	public NewsArticleResource() {
	}
	
	@GET
	public NewsArticleView getNewsArticleView(@PathParam("id") int id) {
		NewsArticle news = NewsArticleDAO.getNewsById(id);
		return new NewsArticleView(news);
	}
}