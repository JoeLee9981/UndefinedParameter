package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Group {
	
	private int id;
	private String name;
	
	public Group(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	
	
}
