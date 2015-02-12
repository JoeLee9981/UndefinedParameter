package com.UndefinedParameter.app.core;

import java.sql.Date;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class History {
	
	private long id;
	private long userid;
	private long quizid;
	private Date time;
	private float score;
	
	public History(long id) {
		this.id = id;
	}
	
	public History() {
		
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
	public long getUserID() {
		return userid;
	}

	@JsonProperty
	public void setUserID(long userid) {
		this.userid = userid;
	}
	
	@JsonProperty
	public long getQuizID() {
		return quizid;
	}

	@JsonProperty
	public void setQuizID(long quizid) {
		this.quizid = quizid;
	}

	@JsonProperty
	public Date getDateTime() {
		return time;
	}

	@JsonProperty
	public void setDateTime(Date date) {
		this.time = date;
	}

	@JsonProperty
	public float getScore() {
		return score;
	}

	@JsonProperty
	public void setScore(float newscore) {
		this.score = newscore;
	}	
}
