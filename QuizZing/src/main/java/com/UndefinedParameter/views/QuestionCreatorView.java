package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionCreatorView extends View {
	
	private int quizId;
	
	public QuestionCreatorView(int quizId)
	{
		super("question_create.ftl");
		this.quizId = quizId;
	}

	@JsonProperty
	public int getQuizId() {
		return quizId;
	}

	@JsonProperty
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	
}
