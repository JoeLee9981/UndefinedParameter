package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Bug;

public class BugReportView extends View {

	private List<Bug> bugs;
	
	public BugReportView(List<Bug> bugs) {
		super("bugreport.ftl");
		this.bugs = bugs;
	}
	
	public List<Bug> getBugs() {
		return bugs;
	}
}
