package com.UndefinedParameter.app.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * This does a quick health test of the server
 * 		This is template code used by dropwizard
 *
 */
public class TemplateHealthCheck extends HealthCheck {
	private final String template;
	
	public TemplateHealthCheck(String template) {
		this.template = template;
	}
	
	@Override
	protected Result check() throws Exception {
		final String saying = String.format(template, "TEST");
		if(!saying.contains("TEST")) {
			return Result.unhealthy("template doesn't include a name");
		}
		
		return Result.healthy();
	}
}
