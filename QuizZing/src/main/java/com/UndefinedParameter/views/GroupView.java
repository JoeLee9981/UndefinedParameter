package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;

public class GroupView extends View {

	//the group itself
	private Group group;
	//the organization this group belongs to
	private Organization organization;
	//Manager object to find quizzes
	private QuizManager quizManager;
	
	public GroupView(Group group, Organization org, QuizManager quizManager) {
		super("group.ftl");
		this.group = group;
		this.organization = org;
		this.quizManager = quizManager;
	}
	
	public Group getGroup() {
		return group;
	}
	
	
	public Organization getOrganization() {
		return organization;
	}
	
	/*
	 * Find the parent organization
	 * 		This is being set automatically from the constructor
	 */
	private Organization findOrg() {
		return organization;
	}
	
	/*
	 * Get a list of quizzes in a group
	 */
	public List<Quiz> getQuizList() {
		List<Quiz> quizzes = quizManager.findQuizzesByGroup(group.getId());
		return quizzes;
	}
}
