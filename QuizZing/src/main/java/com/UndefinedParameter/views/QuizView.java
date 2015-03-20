package com.UndefinedParameter.views;

import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizScore;
import com.UndefinedParameter.app.core.User;

public class QuizView extends View {

	private Quiz quiz;
	private long groupId;
	private boolean loggedIn;
	private boolean editable;
	private int userRating;
	private int userDifficulty;
	private User user;
	private float userBestScore;
	
	public QuizView(User user, Quiz quiz, long groupId, boolean loggedIn, boolean editable, int userRating, int userDifficulty, float userBestScore) {
		super("quiz.ftl");
		this.quiz = quiz;
		this.loggedIn = loggedIn;
		this.editable = editable;
		this.groupId = groupId;
		this.userRating = userRating;
		this.userDifficulty = userDifficulty;
		this.user = user;
		this.userBestScore = userBestScore;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public boolean isEditable() {
		return editable;
	}
	
	public boolean isEmpty() {
		return quiz.getQuestionCount() == 0;
	}
	
	public long getGroupId() {
		return groupId;
	}
	
	//This is used for displaying the users own rating over the averaged rating
	public int getUserRating() {
		return this.userRating;
	}
	
	//this is used for displaying the users own difficulty rating over the averaged rating
	public int getUserDifficulty() {
		return this.userDifficulty;
	}
	
	//is the user logged in or not
	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	public User getUser() {
		return user;
	}
	
	public float getUserBestScore() {
		return userBestScore;
	}
}
