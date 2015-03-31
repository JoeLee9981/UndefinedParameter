package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionCreatorView extends View {
	
	private long quizId;
	private long groupId;
	private User user;
	
	public QuestionCreatorView(User user, long quizId, long groupId)
	{
		super("question_create.ftl");
		this.quizId = quizId;
		this.groupId = groupId;
		this.user = user;
	}
	
	public QuestionCreatorView(String ftl, User user, long quizId, long groupId) {
		super(ftl);
		this.quizId = quizId;
		this.groupId = groupId;
		this.user = user;
	}

	@JsonProperty
	public long getQuizId() {
		return quizId;
	}

	@JsonProperty
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	@JsonProperty
	public long getGroupId() {
		return groupId;
	}

	@JsonProperty
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public User getUser() {
		return user;
	}
}
