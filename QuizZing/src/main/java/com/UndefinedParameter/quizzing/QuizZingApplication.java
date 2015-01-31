package com.UndefinedParameter.quizzing;

import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.health.TemplateHealthCheck;
import com.UndefinedParameter.app.resources.FeedbackResource;
import com.UndefinedParameter.app.resources.GroupResource;
import com.UndefinedParameter.app.resources.HomeResource;
import com.UndefinedParameter.app.resources.NewsArticleResource;
import com.UndefinedParameter.app.resources.OrganizationResource;
import com.UndefinedParameter.app.resources.QuestionCreatorResource;
import com.UndefinedParameter.app.resources.QuizResource;
import com.UndefinedParameter.app.resources.UserProfileResource;
import com.UndefinedParameter.jdbi.GroupDAO;
import com.UndefinedParameter.jdbi.NewsArticleDAO;
import com.UndefinedParameter.jdbi.OrganizationDAO;
import com.UndefinedParameter.jdbi.QuestionDAO;
import com.UndefinedParameter.jdbi.QuizDAO;
import com.UndefinedParameter.jdbi.UserDAO;



/*
 * QuizZing Application is the main entry point of the server
 * 	It contains the main method to run the server and registers
 * 	the views with Jersey
 *
 */
public class QuizZingApplication extends Application<QuizZingConfiguration> {
    final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	
    private static BasicAuthenticator authenticator = null;
    
	public static void main( String[] args ) throws Exception {
		
		try {
			new QuizZingApplication().run(args);
		}
		catch(Exception e) {
			logger.error("QuizZing Application - Unable to start Server: " + e.getStackTrace());
		}
    }
    
    @Override
    public String getName() {
    	return "hello-world";
    }

	@Override
	public void initialize(Bootstrap<QuizZingConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/"));
		bootstrap.addBundle((Bundle) new ViewBundle());
		
	}

	@Override
	public void run(QuizZingConfiguration configuration, Environment environment)
			throws Exception {
		
		
		logger.info("QuizZingApplication - Running Server");
		
		/****** Register the database objects *********/
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
		final NewsArticleDAO newsDAO = jdbi.onDemand(NewsArticleDAO.class);
		final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
		final OrganizationDAO orgDAO = jdbi.onDemand(OrganizationDAO.class);
		final GroupDAO groupDAO = jdbi.onDemand(GroupDAO.class);
		final QuizDAO quizDAO = jdbi.onDemand(QuizDAO.class);
		final QuestionDAO questionDAO = jdbi.onDemand(QuestionDAO.class);
		
		logger.info("Database objects registered successfully");
		
		/****** Register Health Checks ***********/
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		
		logger.info("Health Check Completed");
		
		/****** Registering Authentication ******/
		authenticator = new BasicAuthenticator(userDAO);
	    environment.jersey().register(new BasicAuthProvider<User>(
	    		authenticator, 
	    		"Authentication Required"));
	    
	    logger.info("Authenticator initiated successfully");
		
		/***** REGISTER VIEWS ******/
		environment.jersey().register(new HomeResource(newsDAO, userDAO));
		environment.jersey().register(new NewsArticleResource(newsDAO));
		environment.jersey().register(new QuizResource(quizDAO, questionDAO));
		environment.jersey().register(new GroupResource(orgDAO, groupDAO, quizDAO, questionDAO));
		environment.jersey().register(new OrganizationResource(orgDAO, groupDAO));
		environment.jersey().register(new QuestionCreatorResource(quizDAO, questionDAO));
		environment.jersey().register(new FeedbackResource());
		environment.jersey().register(new UserProfileResource(userDAO));

		logger.info("All Views Registered");
	}
}
