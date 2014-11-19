package com.UndefinedParameter.quizzing;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizZingConfiguration extends Configuration {
	@NotEmpty
	private String template;
	
	@NotEmpty
	private String defaultName = "UndefinedParameter";
	
	@JsonProperty
	public String getTemplate() {
		return template;
	}
	
	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}
	
	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}
	
	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
	}
	
}