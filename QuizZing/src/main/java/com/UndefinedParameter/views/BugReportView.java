package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Bug;
import com.UndefinedParameter.app.core.User;

public class BugReportView extends View {

	private List<Bug> bugs;
	private User user;
	
	public BugReportView(User user, List<Bug> bugs) {
		super("bugreport.ftl");
		this.bugs = bugs;
	}
	
	public List<Bug> getBugs() {
		return bugs;
	}
	
	public User getUser() {
		return user;
	}
}
