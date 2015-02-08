package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bug {
	
	private long bugReportId;
	private long reporterId;
	private String description;
	private String stepsToReproduce;
	private int urgency;
	
	@JsonProperty
	public long getBugReportId() {
		return bugReportId;
	}
	
	@JsonProperty
	public void setBugReportId(long bugReportId) {
		this.bugReportId = bugReportId;
	}
	
	@JsonProperty
	public long getReporterId() {
		return reporterId;
	}
	
	@JsonProperty
	public void setReporterId(long reporterId) {
		this.reporterId = reporterId;
	}
	
	@JsonProperty
	public String getDescription() {
		return description;
	}
	
	@JsonProperty
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public String getStepsToReproduce() {
		return stepsToReproduce;
	}
	
	@JsonProperty
	public void setStepsToReproduce(String stepsToReproduce) {
		this.stepsToReproduce = stepsToReproduce;
	}
	
	@JsonProperty
	public int getUrgency() {
		return urgency;
	}
	
	@JsonProperty
	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}
	
}
