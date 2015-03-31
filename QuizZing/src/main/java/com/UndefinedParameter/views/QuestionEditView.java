package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Question;

public class QuestionEditView extends View {

	private Question question;
	private List<String> allCategories;
	private long groupId;
	
	public QuestionEditView(Question question, long groupId, List<String> allCategories) {
		super("question_edit.ftl");
		this.groupId = groupId;
		this.question = question;
		this.allCategories = allCategories;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public long getGroupId() {
		return this.groupId;
	}

	public List<String> getAllCategories() {
		return this.allCategories;
	}
}
