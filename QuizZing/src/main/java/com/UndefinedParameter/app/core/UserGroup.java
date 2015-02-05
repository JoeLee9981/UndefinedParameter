package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserGroup {
	
	private int id;
	private int userid;
	private int groupid;
	
	
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

}
