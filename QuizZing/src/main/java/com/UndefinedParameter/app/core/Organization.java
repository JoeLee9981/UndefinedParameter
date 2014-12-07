package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This is the root of a organization and class structure
 * 		For example the University of utah (Organization) owns the
 * 		groups (classes) such as CS-4400
 */
public class Organization {
	
	/*
	 * Organization id
	 */
	private int id;
	
	/*
	 * Name of the organization
	 */
	private String name;
	
	/*
	 * A description of the organization
	 */
	private String description;
	
	/*
	 * The city where the organization is
	 */
	private String city;
	
	/*
	 * The state where the organization is
	 */
	private String state;
	
	/*
	 * The country where the organization is
	 */
	private String country;
	
	/*
	 * MORE IDENTIFIERS MAY BE NECESSARY IN THE FUTURE
	 */
	
	//TODO: These may need to be located somewhere else
	private int memberCount;
	private int quizCount;
	private int questionCount;
	
	//TODO: Add a date created
	

	//constructor
	public Organization(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Organization() 
	{
		this.id = -1;
	}
	
	/******************************** Getters and Setters ****************************/

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
	public String getDescription() {
		return description;
	}

	@JsonProperty
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public String getCity() {
		return city;
	}

	@JsonProperty
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty
	public String getState() {
		return state;
	}

	@JsonProperty
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty
	public String getCountry() {
		return country;
	}

	@JsonProperty
	public void setCountry(String country) {
		this.country = country;
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

	/****************************** Organization Helper Methods **********************************/

	/*
	 * returns an array of groups by category for this organization only
	 */
	public Group[] getGroupsByCategory(String category) {
		return OrganizationManager.findGroupsByCategory(id, category);
	}
	
	/*
	 * Returns an array of all groups belonging to this organization
	 */
	public Group[] getGroups() {
		return OrganizationManager.findGroupsById(id);
	}
}
