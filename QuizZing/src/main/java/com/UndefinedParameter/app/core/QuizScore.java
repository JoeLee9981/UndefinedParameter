package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

public class QuizScore {
	private long id;
	private long userid;
	private long quizid;
	private DateTime datetime;
	private float score;
	
	//constructor
	public QuizScore(int id) {
		this.id = id;
	}

	public QuizScore() {
	}
	
	/***************** GETTERS AND SETTERS ***********************/
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUserID() {
		return userid;
	}

	public void setUserID(long userid) {
		this.userid = userid;
	}

	public long getQuizID() {
		return userid;
	}

	public void setQuizID(long quizid) {
		this.quizid = quizid;
	}
	
	public DateTime getDateTime() {
		return datetime;
	}

	public void setDateTime(DateTime datetime) {
		this.datetime = datetime;
	}
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
}