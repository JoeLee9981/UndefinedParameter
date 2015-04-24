package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class for association between a user and a group
 * @author god_laptop
 *
 */
public class UserGroup {
	
	/*** Member Variables ***/
	
	private int id;
	private int userid;
	private int groupid;
	private long rating;
	private long earnedpoints;
	private int modstatus;
	private DateTime joindate;
	
	public UserGroup(int id) {
		this.id = id;
	}
	
	public UserGroup() {
		
	}
	
	/************************* GETTERS AND SETTERS ******************************************/

	@JsonProperty
	public int getId() {
		return id;
	}

	@JsonProperty
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public int getUserId() {
		return userid;
	}
	
	@JsonProperty
	public void setUserId(int id) {
		this.userid = id;
	}

	@JsonProperty
	public void setGroupId(int groupid) {
		this.groupid = groupid;
	}

	@JsonProperty
	public int getGroupId() {
		return groupid;
	}
	
	@JsonProperty
	public void setRating(long rating) {
		this.rating = rating;
	}

	@JsonProperty
	public long getRating() {
		return rating;
	}

	@JsonProperty
	public void setEarnedPoints(long points) {
		this.earnedpoints = points;
	}

	@JsonProperty
	public long getEarnedPoints() {
		return earnedpoints;
	}
	
	@JsonProperty
	public int getModStatus() {
		return modstatus;
	}

	@JsonProperty
	public void setModStatus(int status) {
		this.modstatus = status;
	}

	@JsonProperty
	public void setJoinDate(DateTime dateTime)
	{
		this.joindate = dateTime;
	}
	
	@JsonProperty
	public DateTime getJoinDate()
	{
		return joindate;
	}
}
