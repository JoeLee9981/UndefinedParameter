package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 	Group represents individual groups within a single organization.
 * 		This would be for example a class, such as CS-4400 (group) belongs
 * 		to the Univesity of Utah (organization).
 */
public class Group {
	
	/*
	 * This is the id of the group
	 */
	private int id;
	
	/*
	 * This is the organization that the group belongs to
	 */
	private int organizationId;
	
	/*
	 * This is the name of the group
	 */
	private String name;
	
	/*
	 * Description of the group
	 */
	private String description;
	
	/*
	 * Number of members in the group
	 */
	private int memberCount;
	
	/*
	 * Number of quizzes in the group
	 */
	private int quizCount;
	
	/*
	 * Number of questions in the group
	 */
	private int questionCount;
	
	/*
	 * Number of flagged questions in a group
	 */
	private int flagCount;
	
	private DateTime dateCreated;
	
	
	/*
	 * constructor - For now organizationId is not required to create
	 * 		The group object, this allows more flexibility, may be
	 * 		required in the future
	 */
	public Group(int groupId, int orgId, String name) {
		this.id = groupId;
		this.organizationId = orgId;
		this.name = name;
	}
	
	public Group() {
		this.id = -1;
	}
	
	
	/************************** Standard Getters and Setters *************************/
	
	@JsonProperty
	public int getId() {
		return id;
	}

	@JsonProperty
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public int getOrganizationId() {
		return organizationId;
	}

	@JsonProperty
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	@JsonProperty
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public int getMemberCount() {
		return memberCount;
	}

	@JsonProperty
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	@JsonProperty
	public int getQuizCount() {
		return quizCount;
	}

	@JsonProperty
	public void setQuizCount(int quizCount) {
		this.quizCount = quizCount;
	}

	@JsonProperty
	public int getQuestionCount() {
		return questionCount;
	}

	@JsonProperty
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	@JsonProperty
	public DateTime getDateCreated() {
		return dateCreated;
	}

	@JsonProperty
	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@JsonProperty
	public int getFlagCount() {
		return flagCount;
	}

	@JsonProperty
	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}

	/**
	 * Returns a formatted date string in the format "MM/dd/yyyy"
	 * @return the date formatted
	 */
	public String getDateAsString() {
		return dateCreated.toString("MM/dd/yyyy");
	}
	
}
