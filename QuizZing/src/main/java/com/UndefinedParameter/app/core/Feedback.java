package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {
	
	private String suggestedFeature;
	private String improvement;
	private String miscellaneous;
	
	@JsonProperty
	public String getSuggestedFeature() {
		return suggestedFeature;
	}
	
	@JsonProperty
	public void setSuggestedFeature(String suggestedFeature) {
		this.suggestedFeature = suggestedFeature;
	}
	
	@JsonProperty
	public String getImprovement() {
		return improvement;
	}
	
	@JsonProperty
	public void setImprovement(String improvement) {
		this.improvement = improvement;
	}
	
	@JsonProperty
	public String getMiscellaneous() {
		return miscellaneous;
	}
	
	@JsonProperty
	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	
	
}
