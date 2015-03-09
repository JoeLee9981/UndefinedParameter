package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Quiz;

public class QuizEditQuestionsView extends View {

	private Quiz quiz;
	
	public QuizEditQuestionsView(Quiz quiz) {
		super("quiz_questions.ftl");
		this.quiz = quiz;
	}
	
	public Quiz getQuiz() {
		return this.quiz;
	}
}
