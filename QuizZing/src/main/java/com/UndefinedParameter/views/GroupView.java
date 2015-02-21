package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.User;

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
	private User user;
	// List of the groups this user is registered for
	private List<Group> registeredGroups;
	
	//count of questions
	private int questionCount;
	
	public GroupView(Group group, Organization org, List<Quiz> quizzes, List<Quiz> userQuizzes, boolean loggedIn, User user, List<Group> registeredGroups, int questionCount) {
		super("group.ftl");
		this.group = group;
		this.organization = org;
		this.quizzes = quizzes;
		this.userQuizzes = userQuizzes;
		this.loggedIn = loggedIn;
		this.user = user;
		this.registeredGroups = registeredGroups;
		this.questionCount = questionCount;
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
	
	public int getQuizCount()
	{
		return quizzes.size();
	}
	
	public int getQuestionCount()
	{
		return this.questionCount;
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
	
	public User getUser() {
		return this.user;
	}	
	
	public boolean getUserIsGroupMember()
	{
		// This is currently a very inefficient way of finding if the user is in this group
		if (registeredGroups != null)
		{
			for (int i = 0; i < registeredGroups.size(); i++)
			{
				if (registeredGroups.get(i).getId() == this.group.getId())
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
