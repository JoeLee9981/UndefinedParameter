package com.UndefinedParameter.quizzing;

import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import com.UndefinedParameter.app.health.TemplateHealthCheck;
import com.UndefinedParameter.app.resources.HomeResource;
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
	}
}
