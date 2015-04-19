package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupMember {
	
	private long userId;
	private long orgId;
	private String displayName;
	private int contribution;
	private DateTime joinDate;
	private int quizzes;
	private int questions;
	private boolean moderator;
	
	@JsonProperty
	public String getDisplayName() {
		return displayName;
	}
	
	@JsonProperty
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@JsonProperty
	public int getContribution() {
		return contribution;
	}
	
	@JsonProperty
	public void setContribution(int contribution) {
		this.contribution = contribution;
	}
	
	@JsonProperty
	public DateTime getJoinDate() {
		return joinDate;
	}
	
	@JsonProperty
	public void setJoinDate(DateTime joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getJoinDateString() {
		return joinDate.toString("MM/dd/yyyy");
	}
	
	@JsonProperty
	public int getQuizzes() {
		return quizzes;
	}
	
	@JsonProperty
	public void setQuizzes(int quizzes) {
		this.quizzes = quizzes;
	}
	
	@JsonProperty
	public int getQuestions() {
		return questions;
	}
	
	@JsonProperty
	public void setQuestions(int questions) {
		this.questions = questions;
	}

	@JsonProperty
	public long getUserId() {
		return userId;
	}

	@JsonProperty
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@JsonProperty
	public long getOrgId() {
		return orgId;
	}

	@JsonProperty
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	@JsonProperty
	public boolean isModerator() {
		return moderator;
	}

	@JsonProperty
	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}
}
