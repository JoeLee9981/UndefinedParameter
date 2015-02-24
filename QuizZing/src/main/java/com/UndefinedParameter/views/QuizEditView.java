package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

/*
 * View responsible for editing a quiz (adding removing questions etc)
 * 
 */
public class QuizEditView extends View {

	private Quiz quiz;
	private Group group;
	private User user;
	
	public QuizEditView(User user, Quiz quiz, Group group) {
		super("quiz_edit.ftl");
		this.quiz = quiz;
		this.group = group;
		this.user = user;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public User getUser() {
		return user;
	}
}
