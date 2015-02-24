package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreView extends View{

	private int score;
	private User user;
	
	public ScoreView(User user, int score) {
		super("score.ftl");
		this.score = score;
		this.user = user;
	}

	@JsonProperty
	public int getScore() {
		return score;
	}

	@JsonProperty
	public void setScore(int score) {
		this.score = score;
	}
	
	public User getUser() {
		return user;
	}
}
