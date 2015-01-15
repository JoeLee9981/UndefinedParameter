package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.NewsArticleDAO;
import com.UndefinedParameter.views.HomeView;
import com.UndefinedParameter.views.LoginView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
	
	public HomeResource() {
	}
	
	/*
	 * Creates a Home View, if authenticated returns a the view that's logged in
	 * 	or else returns a login page (TODO: Change this to
	 */
	@GET
	public Response getHomeView(@Auth(required = false) User user) {
		
		if(user == null) {
			ArrayList<NewsArticle> news = (ArrayList<NewsArticle>)NewsArticleDAO.selectAllNews();
			return Response.ok(new HomeView("home.ftl", news.toArray(new NewsArticle[news.size()]))).build();
		}
		else {
			//TODO: Create a customized view for the user and return it, instead of the standard
			ArrayList<NewsArticle> news = (ArrayList<NewsArticle>)NewsArticleDAO.selectAllNews();
			return Response.ok(new HomeView("user_home.ftl", news.toArray(new NewsArticle[news.size()]), user)).build();
		}
	}
	
	/*
	 * Login is used to establish authentication with the web browser
	 * 	The header requires the user name and password, and a payload
	 *  containing the username and password. This will allow cached
	 *  authentication into the web browser itself
	 */
	@POST
	@Path("/login")
	public Response login(@Auth User user) {
		return Response.ok().build();
	}
	
	@GET
	@Path("/login")
	public Response getLoginView() {
		return Response.ok(new LoginView()).build();
	}
	
	@POST
	@Path("/logout")
	public Response logout() {
		return Response.status(401).build();
	}
}
