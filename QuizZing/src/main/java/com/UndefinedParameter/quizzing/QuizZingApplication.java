package com.UndefinedParameter.quizzing;

import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import com.UndefinedParameter.app.health.TemplateHealthCheck;
import com.UndefinedParameter.app.resources.GroupResource;
import com.UndefinedParameter.app.resources.GroupsResource;
import com.UndefinedParameter.app.resources.HomeResource;
import com.UndefinedParameter.app.resources.NewsArticleResource;
import com.UndefinedParameter.app.resources.OrganizationResource;
import com.UndefinedParameter.app.resources.OrgsResource;
import com.UndefinedParameter.app.resources.QuestionCreatorResource;
import com.UndefinedParameter.app.resources.QuizCreatorResource;
import com.UndefinedParameter.app.resources.QuizResource;
import com.UndefinedParameter.app.resources.QuizZingResource;



/**
 * Hello world!
 *
 */
public class QuizZingApplication extends Application<QuizZingConfiguration> {
    public static void main( String[] args ) throws Exception {
        new QuizZingApplication().run(args);
    }
    
    @Override
    public String getName() {
    	return "hello-world";
    }

	@Override
	public void initialize(Bootstrap<QuizZingConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
		bootstrap.addBundle((Bundle) new ViewBundle());
		
	}

	@Override
	public void run(QuizZingConfiguration configuration, Environment environment)
			throws Exception {
		
		environment.jersey().setUrlPattern("/service/*");
		
		final QuizZingResource resource = new QuizZingResource(configuration.getTemplate(), configuration.getDefaultName());
		
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
		
		/***** REGISTER VIEWS ******/
		environment.jersey().register(new HomeResource());
		environment.jersey().register(new NewsArticleResource());
		environment.jersey().register(new QuizResource());
		environment.jersey().register(new GroupResource());
		environment.jersey().register(new GroupsResource());
		environment.jersey().register(new OrganizationResource());
		environment.jersey().register(new OrgsResource());
		environment.jersey().register(new QuizCreatorResource());
		environment.jersey().register(new QuestionCreatorResource());
	}
}
