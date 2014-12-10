package com.UndefinedParameter.quizzing;

import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.UndefinedParameter.app.health.TemplateHealthCheck;
import com.UndefinedParameter.app.resources.FeedbackResource;
import com.UndefinedParameter.app.resources.GroupResource;
import com.UndefinedParameter.app.resources.HomeResource;
import com.UndefinedParameter.app.resources.NewsArticleResource;
import com.UndefinedParameter.app.resources.OrganizationResource;
import com.UndefinedParameter.app.resources.QuestionCreatorResource;
import com.UndefinedParameter.app.resources.QuizResource;



/*
 * QuizZing Application is the main entry point of the server
 * 	It contains the main method to run the server and registers
 * 	the views with Jersey
 *
 */
public class QuizZingApplication extends Application<QuizZingConfiguration> {
    final static Logger logger = LoggerFactory.getLogger(QuizZingApplication.class);
	
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

			
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		
		/***** REGISTER VIEWS ******/
		environment.jersey().register(new HomeResource());
		environment.jersey().register(new NewsArticleResource());
		environment.jersey().register(new QuizResource());
		environment.jersey().register(new GroupResource());
		environment.jersey().register(new OrganizationResource());
		environment.jersey().register(new QuestionCreatorResource());
		environment.jersey().register(new FeedbackResource());

	}
}
