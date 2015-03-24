package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreView extends View{

	private int scores;
	private User user;
	
	public ScoreView(String page, User user, int scores) {
		super(page);
		this.scores = scores;
		this.user = user;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}
	
	public User getUser() {
		return user;
	}
}
