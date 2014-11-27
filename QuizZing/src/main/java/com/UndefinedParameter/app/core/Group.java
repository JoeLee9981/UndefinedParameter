package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 	Group represents individual groups within a single organization.
 * 		This would be for example a class, such as CS-4400 (group) belongs
 * 		to the Univesity of Utah (organization).
 */
public class Group {
	
	/*
	 * This is the id of the group
	 */
	private int id;
	
	/*
	 * This is the organization that the group belongs to
	 */
	private int organizationId;
	
	/*
	 * This is the name of the group
	 */
	private String name;
	
	
	/*
	 * TODO: Add long name and description fields
	 * 	May also want to consider a category field
	 * 		For exampe. name would be CS-4962, long Name
	 * 		would be Mobile Apps - Android/iOS, category would
	 * 		be Computer Science, description would be a description
	 * 		of the class.
	 */
	
	
	/*
	 * constructor - For now organizationId is not required to create
	 * 		The group object, this allows more flexibility, may be
	 * 		required in the future
	 */
	public Group(int groupId, int orgId, String name) {
		this.id = groupId;
		this.organizationId = orgId;
		this.name = name;
	}
	
	public Group() {
		this.id = -1;
	}
	
	
	/************************** Standard Getters and Setters *************************/
	
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
	public int getOrganizationId() {
		return organizationId;
	}

	@JsonProperty
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	
}
