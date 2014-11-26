package com.UndefinedParameter.views;

import io.dropwizard.views.View;
import com.UndefinedParameter.app.core.Quiz;

public class QuizView extends View {

	private Quiz quiz;
	
	public QuizView(Quiz quiz) {
		super("quiz.ftl");
		this.quiz = quiz;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
}