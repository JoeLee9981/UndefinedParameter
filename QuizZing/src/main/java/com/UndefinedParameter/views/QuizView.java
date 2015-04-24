package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Quiz;
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
	
	/**
	 * Constructor for the QuizView. Takes all arguments used inside the .ftl files.
	 * 
	 * @param user
	 * @param quiz
	 * @param groupId
	 * @param loggedIn
	 * @param editable
	 * @param userRating
	 * @param userDifficulty
	 * @param userBestScore
	 */
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
	
	/**
	 * Returns quiz object to parse and display on the .ftl web page.
	 * 
	 * @return quiz to display
	 */
	public Quiz getQuiz() {
		return quiz;
	}
	
	/**
	 * Returns boolean to determine whether the current logged in user has editing rights.
	 * 
	 * @return editable boolean
	 */
	public boolean isEditable() {
		return editable;
	}
	
	/**
	 * Returns boolean of whether or not the quiz contains questions.
	 * 
	 * @return question count boolean
	 */
	public boolean isEmpty() {
		return quiz.getQuestionCount() == 0;
	}
	
	/**
	 * Returns id of group the quiz belongs to.
	 * 
	 * @return group id long
	 */
	public long getGroupId() {
		return groupId;
	}
	
	/**
	 * Returns the rating for this quiz.
	 * 
	 * @return rating integer
	 */
	public int getUserRating() {
		return this.userRating;
	}
	
	/**
	 * Returns the difficulty rating for this quiz.
	 * 
	 * @return difficulty integer
	 */
	public int getUserDifficulty() {
		return this.userDifficulty;
	}
	
	/**
	 * Returns boolean on whether the current user is logged in or not.
	 * 
	 * @return logged in boolean
	 */
	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	/**
	 * Returns current logged-in user object.
	 * 
	 * @return current User
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Returns user's best score so far for this quiz. Is N/A if they're not logged in or a previous score doesn't exist.
	 * 
	 * @return previous best score float
	 */
	public float getUserBestScore() {
		return userBestScore;
	}
}
