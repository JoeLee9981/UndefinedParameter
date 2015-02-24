package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

public class QuestionAddView extends View {
	
	private long quizId;
	private long groupId;
	private User user;
	
	//This is a list of all questions associated with this group
	private List<Question> questions;
	
	public QuestionAddView(User user, List<Question> questions, long quizId, long groupId) {
		super("question_add.ftl");
		this.quizId = quizId;
		this.groupId = groupId;
		this.questions = questions;
		this.user = user;
	}

	public List<Question> getQuestions() {
		if(questions != null && questions.size() > 0)
			return questions;
		else
			return null;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public User getUser() {
		return user;
	}
	
}
