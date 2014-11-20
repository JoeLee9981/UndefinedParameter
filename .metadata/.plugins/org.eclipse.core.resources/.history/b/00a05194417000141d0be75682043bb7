package com.UndefinedParameter.app.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jdbi.NewsArticleDAO;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.views.NewsArticleView;


@Path("/news/{id}")
@Produces(MediaType.TEXT_HTML)
public class NewsArticleResource {
	public NewsArticleResource() {
	}
	
	@GET
	public NewsArticleView getNewsArticleView(@PathParam("id") String id) {
		ArrayList<NewsArticle> news = (ArrayList<NewsArticle>)NewsArticleDAO.selectAllNews();
		return new NewsArticleView(news.get(Integer.parseInt(id)));
	}
}
