package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Question;

public class QuestionEditView extends View {

	private Question question;
	private List<String> allCategories;
	private long groupId;
	private long quizId;
	private Boolean goBackToQuizEdit = false;
	
	public QuestionEditView(Question question, long groupId, List<String> allCategories) {
		super("question_edit.ftl");
		this.groupId = groupId;
		this.question = question;
		this.allCategories = allCategories;
	}
	
	public QuestionEditView(Question question, long groupId, List<String> allCategories, long quizId) {
		super("question_edit_full.ftl");
		this.groupId = groupId;
		this.question = question;
		this.allCategories = allCategories;
		this.quizId = quizId;
		this.goBackToQuizEdit = true;
	}
	
	public long getQuizId()
	{
		return this.quizId;
	}
	
	public Boolean getGoBackToQuizEdit()
	{
		return this.goBackToQuizEdit;
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
