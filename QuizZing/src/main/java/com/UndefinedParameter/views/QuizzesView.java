package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Quiz;

public class QuizzesView extends View {

	private List<Quiz> topQuizzes;
	private List<Quiz> userQuizzes;
	
	public QuizzesView(List<Quiz> topQuizzes, List<Quiz> userQuizzes) {
		super("quizzes.ftl");
		this.topQuizzes = topQuizzes;
		this.userQuizzes = userQuizzes;
	}

	public List<Quiz> getTopQuizzes() {
		return topQuizzes;
	}
	
	public List<Quiz> getUserQuizzes() {
		return userQuizzes;
	}
	
}
