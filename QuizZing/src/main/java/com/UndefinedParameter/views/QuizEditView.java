package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Quiz;

/*
 * View responsible for editing a quiz (adding removing questions etc)
 * 
 */
public class QuizEditView extends View {

	private Quiz quiz;
	private Group group;
	
	public QuizEditView(Quiz quiz, Group group) {
		super("quiz_edit.ftl");
		this.quiz = quiz;
		this.group = group;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public Group getGroup() {
		return group;
	}
}
