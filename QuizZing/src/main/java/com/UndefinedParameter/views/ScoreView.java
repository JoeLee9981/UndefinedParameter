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

	/**
	 * View that contains all the objects that the score-related .ftl pages will access.
	 * 
	 * @param page
	 * @param user
	 * @param quizIds
	 * @param averageScore
	 * @param bestCategories
	 */
	public ScoreView(String page, User user, List<Quiz> quizIds, float averageScore, List<String> bestCategories) {
		super(page);
		this.quizIds = quizIds;
		this.user = user;
		this.averageScore = averageScore;
		this.bestCategories = bestCategories;
	}

	/**
	 * Returns a list of quizzes for all quizzes that a user has taken.
	 * 
	 * @return quiz list
	 */
	public List<Quiz> getQuizIds() {
		return quizIds;
	}
	
	/**
	 * Returns current logged-in user.
	 * 
	 * @return current user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Returns overall average score for this user across all quizzes.
	 * 
	 * @return average score float
	 */
	public float getAverageScore() {
		return averageScore;
	}
	
	/**
	 * Returns a list of category names from the quiz with the best score.
	 * 
	 * @return category name list
	 */
	public List<String> getBestCategories() {
		if(bestCategories.size() > 4)
			return bestCategories.subList(0, 3);
		else
			return bestCategories;
	}
}
