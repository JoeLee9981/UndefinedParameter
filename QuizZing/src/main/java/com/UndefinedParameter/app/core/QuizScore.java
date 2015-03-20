package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizScore {
	private long id;
	private long userId;
	private long quizId;
	private DateTime datetime;
	private float score;
	
	//constructor
	public QuizScore(int id) {
		this.id = id;
	}

	public QuizScore() {
	}
	
	/***************** GETTERS AND SETTERS ***********************/
	
	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public long getUserId() {
		return userId;
	}

	@JsonProperty
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@JsonProperty
	public long getQuizId() {
		return quizId;
	}

	@JsonProperty
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	
	@JsonProperty
	public DateTime getDateTime() {
		return datetime;
	}

	@JsonProperty
	public void setDateTime(DateTime datetime) {
		this.datetime = datetime;
	}
	
	@JsonProperty
	public float getScore() {
		return score;
	}

	@JsonProperty
	public void setScore(float score) {
		this.score = score;
	}
	
}