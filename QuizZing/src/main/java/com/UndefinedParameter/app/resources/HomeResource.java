package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.NewsManager;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.QuizManager;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserManager;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.NewsArticleDAO;
import com.UndefinedParameter.jdbi.OrgMemberDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.QuizScoreDAO;
import com.UndefinedParameter.jdbi.UserDAO;
import com.UndefinedParameter.views.AboutView;
import com.UndefinedParameter.views.ForgotView;
import com.UndefinedParameter.views.HomeView;
import com.UndefinedParameter.views.LoginView;
import com.UndefinedParameter.views.RegisterView;
import com.UndefinedParameter.views.TutorialView;

/**
 * This is responsible for any paths used against the root
 * 	Mostly used for home and user home views
 * @author god_laptop
 *
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
	
	//Managers
	private NewsManager newsManager;
	private UserManager userManager;
	private OrganizationManager orgManager;
	private QuizManager quizManager;
	
	/**
	 * Constructor
	 * @param newsDAO
	 * @param userDAO
	 * @param orgDAO
	 * @param groupDAO
	 * @param quizDAO
	 * @param questionDAO
	 * @param quizScoreDAO
	 * @param orgMemberDAO
	 */
	public HomeResource(NewsArticleDAO newsDAO, UserDAO userDAO, OrganizationDAO orgDAO, GroupDAO groupDAO, QuizDAO quizDAO, QuestionDAO questionDAO, QuizScoreDAO quizScoreDAO, OrgMemberDAO orgMemberDAO) {
		this.newsManager = new NewsManager(newsDAO);
		this.userManager = new UserManager(userDAO);
		this.orgManager = new OrganizationManager(orgDAO, groupDAO, orgMemberDAO);
		this.quizManager = new QuizManager(quizDAO, questionDAO, quizScoreDAO);
	}
	
	/*
	 * Creates a Home View, if authenticated returns a the view that's logged in
	 * 	or else returns a login page (TODO: Change this to
	 */
	@GET
	public Response getHomeView(@Auth(required = false) User user) {
		
		if(user == null) {
			ArrayList<NewsArticle> news = (ArrayList<NewsArticle>)newsManager.getRecentNews();
			return Response.ok(new HomeView("home.ftl", quizManager.findTopQuizzes())).build();
		}
		else {
			//TODO: Create a customized view for the user and return it, instead of the standard
			ArrayList<NewsArticle> news = (ArrayList<NewsArticle>)newsManager.getRecentNewsByUser(user.getId());
			return Response.ok(new HomeView("user_home.ftl", user, quizManager.findQuizzesByCreatorId(user.getId()))).build();
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
	public Response login(@Auth User user, @QueryParam("path") String path) {
		return Response.ok().build();
	}
	
	/**
	 * gets the login view that a user can use to login with
	 * @param user
	 * @return
	 */
	@GET
	@Path("/login")
	public Response getLoginView(@Auth(required=false) User user) {
		return Response.ok(new LoginView(user)).build();
	}
	
	/**
	 * Performs a logout by return a 401 against an auth request causing
	 * 	the browser to remove the cached username and password
	 * @return
	 */
	@POST
	@Path("/logout")
	public Response logout() {
		return Response.status(401).build();
	}
	
	/**
	 * Returns a view that the user can use to register
	 * @param user
	 * @return
	 */
	@GET
	@Path("/register")
	public Response getRegisterView(@Auth(required = false) User user) 
	{
		// If the user is already logged in, redirect them to their profile page
		if (user == null)
		{
			return Response.ok(new RegisterView(null)).build();
		}
		else
		{
			// TODO This is not currently set to go to their user page.  I think it should go to /profile -Braeden
			return Response.ok(new RegisterView(null)).build();
		}
	}
	
	/**
	 * Posts a new user and registers it into the database
	 * @param user
	 * @return
	 */
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(@Valid User user) {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			if(userManager.registerNewUser(user)) {
				response.put("response", "success");
				return Response.ok(response).build();
			}
			else {
				response.put("response", "error");
				response.put("message", "Your email address has already been registered.");
				return Response.ok(response).build();
			}
			
		}
		catch(Exception e) {
			//TODO: return error message
			return Response.status(500).build();
		}
		
	}
	
	/**
	 * Creates a view for forgot password link to allow a user to reset their password
	 * @return
	 */
	@GET
	@Path("/forgot")
	public Response getForgotView() {
		return Response.ok(new ForgotView("forgot.ftl")).build();
	}
	
	/**
	 * Creates the password recovery screen where the user enters the validation code
	 * @param email
	 * @return
	 */
	@GET
	@Path("/recover")
	public Response getRecoverView(@QueryParam("email") String email) {
		// TODO: Send email to usermanager for validation and recovery code generation.
		try {
			User recoveredUser = userManager.recoverUser(email);	
			
			if(recoveredUser != null)
				return Response.ok(new ForgotView("recover.ftl", recoveredUser)).build();
			else
				return Response.ok(new ForgotView("dne.ftl", email)).build();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	
	/**
	 * Posts the validation code for recovering the password
	 * @param user
	 * @return
	 */
	@POST
	@Path("/recover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recover(@Valid User user) {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			if(userManager.updateUser(user)) {
				response.put("response", "success");
				return Response.ok(response).build();
			}
			else {
				response.put("response", "error");
				response.put("message", "Your password changes did not get passed to the database.");
				return Response.ok(response).build();
			}
			
		}
		catch(Exception e) {
			//TODO: return error message
			return Response.status(500).build();
		}
		
	}
	/**
	 * Creats the view for the about page
	 * @param user
	 * @return
	 */
	@GET
	@Path("/about")
	public Response getAboutPage(@Auth(required=false) User user) {
		return Response.ok(new AboutView(user, newsManager.getRecentNews())).build();
	}
	
	/**
	 * Tutorial page
	 * @param user
	 * @return
	 */
	@GET
	@Path("/tutorial")
	public Response getTutorialView(@Auth(required = false) User user) {
		return Response.ok(new TutorialView(user)).build();
	}
}
