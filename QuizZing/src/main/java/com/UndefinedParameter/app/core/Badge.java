package com.UndefinedParameter.app.core;

public class Badge {

	private long userId;
	private long groupId;
	private String groupName;
	private long organizationId;
	private String organizationName;
	private int contribution;
	
	public Badge() {}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getContribution() {
		return contribution;
	}

	public void setContribution(int contribution) {
		this.contribution = contribution;
	};
	
}
