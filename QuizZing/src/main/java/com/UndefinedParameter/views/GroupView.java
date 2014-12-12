package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.OrganizationManager;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;

public class GroupView extends View {

	//the group itself
	private Group group;
	//the organization this group belongs to
	private Organization organization;
	
	public GroupView(Group group) {
		super("group.ftl");
		this.group = group;
		this.organization = findOrg(group.getOrganizationId());
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
	private Organization findOrg(int orgId) {
		return OrganizationManager.findOrgById(orgId);
	}
	
	/*
	 * Get a list of quizzes in a group
	 */
	public ArrayList<Quiz> getQuizzes() {
		ArrayList<Quiz> quizzes = QuizManager.findQuizzesByGroup(group.getId());
		
		return quizzes;
	}
}
