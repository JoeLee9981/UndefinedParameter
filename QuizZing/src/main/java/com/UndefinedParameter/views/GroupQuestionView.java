package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.User;

public class GroupQuestionView extends View {

	private List<Question> questions;
	private long groupId;
	
	public GroupQuestionView(User user, List<Question> list, long groupId) {
		super("group_questions.ftl");
		this.questions = list;
		this.groupId = groupId;
	}
	
	public List<Question> getQuestions() {
		return this.questions;
	}
	
	public long getGroupId() {
		return this.groupId;
	}

}
