package com.UndefinedParameter.app.core;

public class NewsArticle {

	long id;
	String headline;
	String body;
	
	public NewsArticle() {}
	
	public NewsArticle(long id, String headline, String body) {
		this.id = id;
		this.headline = headline;
		this.body = body;
	}

	public long getId() {
		return id;
	}

	public String getHeadline() {
		return headline;
	}

	public String getBody() {
		return body;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
