package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Quiz;

public class QuestionAddView extends View {
	
	private Quiz quiz;
	private long groupId;
	
	public QuestionAddView(Quiz quiz, long groupId) {
		super("question_add.ftl");
		this.quiz = quiz;
		this.groupId = groupId;
		
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public long getQuizId() {
		return quiz.getQuizId();
	}
	
	public long getGroupId() {
		return groupId;
	}
}
