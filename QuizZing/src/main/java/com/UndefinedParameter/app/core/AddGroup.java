package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class AddGroup {
	
	private int organizationId;
	private String name;


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
	
	
	
}
