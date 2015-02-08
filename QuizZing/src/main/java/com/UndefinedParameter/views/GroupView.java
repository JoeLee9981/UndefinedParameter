package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;

public class GroupView extends View {

	//the group itself
	private Group group;
	//the organization this group belongs to
	private Organization organization;
	//list of group quizzes
	private List<Quiz> quizzes;
	//list of a users quizzes, may be null
	private List<Quiz> userQuizzes;
	//is the user logged in
	private boolean loggedIn;
	
	public GroupView(Group group, Organization org, List<Quiz> quizzes, List<Quiz> userQuizzes, boolean loggedIn) {
		super("group.ftl");
		this.group = group;
		this.organization = org;
		this.quizzes = quizzes;
		this.userQuizzes = userQuizzes;
		this.loggedIn = loggedIn;
	}
	
	public Group getGroup() {
		return group;
	}
	
	
	public Organization getOrganization() {
		return organization;
	}
	
	/*
	 * Get a list of quizzes in a group
	 */
	public List<Quiz> getQuizzes() {
		return quizzes;
	}
	
	/*
	 * Get a list of a users quizzes in the group
	 * 	May be null
	 */
	public List<Quiz> getUserQuizzes() {
		return userQuizzes;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
}
