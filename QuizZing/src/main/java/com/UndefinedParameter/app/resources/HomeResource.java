package com.UndefinedParameter.app.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jdbi.NewsArticleDAO;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.views.HomeView;

@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
	
	public HomeResource() {
	}
	
	@GET
	public HomeView getHomeView() {
		ArrayList<NewsArticle> news = (ArrayList<NewsArticle>)NewsArticleDAO.selectAllNews();
		return new HomeView(news.toArray(new NewsArticle[news.size()]));
	}
	
}