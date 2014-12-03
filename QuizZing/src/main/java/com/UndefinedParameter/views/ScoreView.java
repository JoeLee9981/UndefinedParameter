package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreView extends View{

	private int score;
	
	public ScoreView(int score) {
		super("score.ftl");
		this.score = score;
	}

	@JsonProperty
	public int getScore() {
		return score;
	}

	@JsonProperty
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
