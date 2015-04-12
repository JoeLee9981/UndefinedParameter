package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.User;

public class GroupQuestionView extends View {

	private List<Question> questions;
	private List<Question> userQuestions;
	private User user;
	private long groupId;
	private String message;
	private boolean moderator;
	
	public GroupQuestionView(User user, List<Question> questions, long groupId, String message, boolean moderator) {
		super("group_questions.ftl");
		this.user = user;
		this.questions = new ArrayList<Question>();
		this.userQuestions = null;
		this.groupId = groupId;
		setQuestions(questions);
		this.message = message;
		this.moderator = moderator;
	}
	
	private void setQuestions(List<Question> questions) {
		if(user == null) {
			this.questions = questions;
			return;
		}
		userQuestions = new ArrayList<Question>();
		for(int i = 0; i < questions.size(); i++) {
			if(questions.get(i).getCreatorId() == user.getId()) {
				userQuestions.add(questions.get(i));
			}
			else {
				this.questions.add(questions.get(i));
			}
		}
	}
	
	public List<Question> getQuestions() {
		if(this.questions.size() == 0) {
			return null;
		}
		return this.questions;
	}
	
	public List<Question> getUserQuestions() {
		if(this.userQuestions.size() == 0) {
			return null;
		}
		return this.userQuestions;
	}
	
	public long getGroupId() {
		return this.groupId;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public String getMessage() {
		return this.message;
	}

	public boolean isModerator() {
		return moderator;
	}
}
