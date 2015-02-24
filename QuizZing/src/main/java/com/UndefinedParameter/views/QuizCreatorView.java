package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.User;

public class QuizCreatorView extends View {

	public Group group;
	private User user;
	
	public QuizCreatorView(User user, Group group) {
		super("quiz_create.ftl");
		this.group = group;
		this.user = user;
	}
	
	public Group getGroup() {
		return this.group;
	}
	
	public User getUser() {
		return user;
	}
	
}
