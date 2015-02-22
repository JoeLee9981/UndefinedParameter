package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizRating {
	
	private long quizRatingId;
	private long quizId;
	private long userId;
	private int rating;
	
	public QuizRating() {}

	public QuizRating(long quizRatingId, long quizId, long userId, int rating) {
		super();
		this.quizRatingId = quizRatingId;
		this.quizId = quizId;
		this.userId = userId;
		this.rating = rating;
	}

	@JsonProperty
	public long getQuizRatingId() {
		return quizRatingId;
	}

	@JsonProperty
	public void setQuizRatingId(long quizRatingId) {
		this.quizRatingId = quizRatingId;
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
	public long getUserId() {
		return userId;
	}

	@JsonProperty
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@JsonProperty
	public int getRating() {
		return rating;
	}

	@JsonProperty
	public void setRating(int rating) {
		this.rating = rating;
	}
}
