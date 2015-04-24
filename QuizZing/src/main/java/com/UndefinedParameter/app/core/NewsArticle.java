package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Pojo object to represent news
 * 		This is mostly deprecated, but is being used
 * 		on the about page.
 *
 */
public class NewsArticle {

	/**** member variables ***/
	
	long id;
	String headline;
	String body;
	
	//default constructor
	public NewsArticle() {}
	
	// paramaterized constructor
	public NewsArticle(long id, String headline, String body) {
		this.id = id;
		this.headline = headline;
		this.body = body;
	}
	
	/******************** GETTERS AND SETTERS ******************************/

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public String getHeadline() {
		return headline;
	}

	@JsonProperty
	public String getBody() {
		return body;
	}

	@JsonProperty
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@JsonProperty
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
