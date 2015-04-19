package com.UndefinedParameter.views;

import java.util.List;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Question;
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
	private List<Question> unaddedGroupQuestions;
	
	public QuizEditView(User user, Quiz quiz, Group group, List<Question> unaddedGroupQuestions) {
		super("quiz_edit.ftl");
		this.quiz = quiz;
		this.group = group;
		this.user = user;
		this.unaddedGroupQuestions = unaddedGroupQuestions;
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
	
	public List<Question> getUnaddedGroupQuestions()
	{
		return unaddedGroupQuestions;
	}
}
