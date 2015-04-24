package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a pojo class used for adding a group
 *		Only serves for functionality for adding a class to the database
 */
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
