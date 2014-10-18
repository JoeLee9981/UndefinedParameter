package com.UndefinedParameter.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.UndefinedParameter.app.health.TemplateHealthCheck;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(QuizZingConfiguration configuration, Environment environment)
			throws Exception {
		final QuizZingResource resource = new QuizZingResource(configuration.getTemplate(), configuration.getDefaultName());
		
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}
}
