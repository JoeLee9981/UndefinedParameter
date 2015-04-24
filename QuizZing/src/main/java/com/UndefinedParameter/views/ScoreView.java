package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class ScoreView extends View{

	private List<Quiz> quizIds;
	private float averageScore;
	private User user;
	private List<String> bestCategories;

	public ScoreView(String page, User user, List<Quiz> quizIds, float averageScore, List<String> bestCategories) {
		super(page);
		this.quizIds = quizIds;
		this.user = user;
		this.averageScore = averageScore;
		this.bestCategories = bestCategories;
	}

	public List<Quiz> getQuizIds() {
		return quizIds;
	}
	
	public User getUser() {
		return user;
	}
	
	public float getAverageScore() {
		return averageScore;
	}
	
	public List<String> getBestCategories() {
		if(bestCategories.size() > 4)
			return bestCategories.subList(0, 3);
		else
			return bestCategories;
	}
}
