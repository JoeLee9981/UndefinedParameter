package com.UndefinedParameter.views;

import io.dropwizard.views.View;
import com.UndefinedParameter.app.core.Quiz;

public class QuizView extends View {

	private Quiz quiz;
	private long groupId;
	private boolean editable;
	private int userRating;
	
	public QuizView(Quiz quiz, long groupId, boolean editable, int userRating) {
		super("quiz.ftl");
		this.quiz = quiz;
		this.editable = editable;
		this.groupId = groupId;
		this.userRating = userRating;
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
	
}
