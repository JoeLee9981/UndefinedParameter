package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class ScoreView extends View{

	private List<Quiz> quizIds;
	private User user;
	
	public ScoreView(String page, User user, List<Quiz> quizIds) {
		super(page);
		this.quizIds = quizIds;
		this.user = user;
	}

	public List<Quiz> getQuizIds() {
		return quizIds;
	}
	
	public User getUser() {
		return user;
	}
}
