package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Log {
	
	private long id;
	private long orgid;
	private long groupid;
	private long userid;
	private String log;
	
	public Log(long id) {
		this.id = id;
	}
	
	public Log() {
		
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
	public long getOrgID() {
		return orgid;
	}

	@JsonProperty
	public void setOrgID(long orgid) {
		this.orgid = orgid;
	}

	@JsonProperty
	public long getGroupID() {
		return groupid;
	}

	@JsonProperty
	public void setGroupID(long groupid) {
		this.groupid = groupid;
	}
	
	@JsonProperty
	public String getLog() {
		return log;
	}

	@JsonProperty
	public void setLog(String log) {
		this.log = log;
	}
}
