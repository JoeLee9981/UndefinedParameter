package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Quiz;

public class QuizListView extends View {

	private List<Quiz> quizzes;
	
	public QuizListView(String ftl, List<Quiz> quizzes) {
		super(ftl);
		this.quizzes = quizzes;

	}

	public List<Quiz> getQuizzes() {
		return this.quizzes;
	}
	
}
