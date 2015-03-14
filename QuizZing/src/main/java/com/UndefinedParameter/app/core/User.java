package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private long id;
	private String userName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String country;
	private String city;
	private String state;
	private String email;
	private String password;
	private String secretQuestion;
	private String secretAnswer;
	private String activeCode;
	private DateTime lastAccessed;
	private int active;
	private int seeagain;
	
	public User(long id) {
		this.id = id;
	}
	
	public User() {
		
	}
	
	/************************* GETTERS AND SETTERS ******************************************/

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty
	public String getUserName() {
		return userName;
	}

	@JsonProperty
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty
	public String getLastName() {
		return lastName;
	}

	@JsonProperty
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getEmail() {
		return email;
	}

	@JsonProperty
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty
	public String getSecretQuestion() {
		return secretQuestion;
	}

	@JsonProperty
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	@JsonProperty
	public String getSecretAnswer() {
		return secretAnswer;
	}

	@JsonProperty
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	
	@JsonProperty
	public DateTime getLastAccessed() {
		if(lastAccessed == null)
			return DateTime.now();
		else
			return lastAccessed;
	}
	
	@JsonProperty
	public void setLastAccessed(DateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	@JsonProperty
	public int getActive() {
		return active;
	}
	
	@JsonProperty
	public void setActive(int active) {
		this.active = active;
	}

	@JsonProperty
	public int getSeeAgain() {
		return seeagain;
	}
	
	@JsonProperty
	public void setSeeagain(int seeagain) {
		this.seeagain = seeagain;
	}

	@JsonProperty
	public String getActiveCode() {
		return activeCode;
	}
	
	@JsonProperty
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	
	public String getDisplayName() {
		return firstName + " " + lastName.subSequence(0, 1);
	}
}
