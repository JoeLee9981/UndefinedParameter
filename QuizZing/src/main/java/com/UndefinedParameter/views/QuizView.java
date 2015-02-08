package com.UndefinedParameter.views;

import io.dropwizard.views.View;
import com.UndefinedParameter.app.core.Quiz;

public class QuizView extends View {

	private Quiz quiz;
	private boolean editable;
	
	public QuizView(Quiz quiz, boolean editable) {
		super("quiz.ftl");
		this.quiz = quiz;
		this.editable = editable;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public boolean isEditable() {
		return editable;
	}
}
