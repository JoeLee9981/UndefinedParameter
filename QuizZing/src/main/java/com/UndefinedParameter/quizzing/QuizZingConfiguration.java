package com.UndefinedParameter.quizzing;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizZingConfiguration extends Configuration {
	
	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	
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
	
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
}