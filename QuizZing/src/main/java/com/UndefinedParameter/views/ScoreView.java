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
	private String favoriteCategory;
	
	public ScoreView(String page, User user, List<Quiz> quizIds, float averageScore, List<String> bestCategories, String favoriteCategory) {
		super(page);
		this.quizIds = quizIds;
		this.user = user;
		this.averageScore = averageScore;
		this.bestCategories = bestCategories;
		this.favoriteCategory = favoriteCategory;
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
		return bestCategories;
	}
	
	public String getFavoriteCategory() {
		return favoriteCategory;
	}
}
