package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import com.UndefinedParameter.app.core.Group;

public class QuizCreatorView extends View {

	public Group group;
	
	public QuizCreatorView(Group group) {
		super("quiz_create.ftl");
		this.group = group;
	}
	
	public Group getGroup() {
		return this.group;
	}
	
}
