package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class QuizzesView extends View {

	private List<Quiz> topQuizzes;
	private List<Quiz> userQuizzes;
	private User user;
	
	public QuizzesView(User user, List<Quiz> topQuizzes, List<Quiz> userQuizzes) {
		super("quizzes.ftl");
		this.topQuizzes = topQuizzes;
		this.userQuizzes = userQuizzes;
		this.user = user;
	}

	public List<Quiz> getTopQuizzes() {
		return topQuizzes;
	}
	
	public List<Quiz> getUserQuizzes() {
		return userQuizzes;
	}
	
	public User getUser() {
		return user;
	}
}
