package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Question;

public class QuestionEditView extends View {

	private Question question;
	private long groupId;
	
	public QuestionEditView(Question question, long groupId) {
		super("question_edit.ftl");
		this.groupId = groupId;
		this.question = question;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public long getGroupId() {
		return this.groupId;
	}

}
