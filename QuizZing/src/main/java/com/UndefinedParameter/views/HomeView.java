package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.NewsArticle;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class HomeView extends View{

	private User user;
	private List<Quiz> quizzes;
	
	public HomeView(String page, List<Quiz> quizzes) {
		super(page);
		this.quizzes = quizzes;
		this.user = null;

	}
	
	public HomeView(String page, User user, List<Quiz> quizzes) {
		super(page);
		this.user = user;
		this.quizzes = quizzes;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public List<Quiz> getQuizzes() {
		return this.quizzes;
	}
}