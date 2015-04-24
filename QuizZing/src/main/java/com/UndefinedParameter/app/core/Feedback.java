package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Pojo class used to wrap user feedback for transmission from
 * 	User input into the database.
 *
 */
public class Feedback {
	
	/**** member variables ****/
	
	private long FeedbackID;
	private String suggestedFeature;
	private String improvement;
	private String miscellaneous;
	
	/**** Getters and Setters ****/
	
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

	@JsonProperty
	public void setId(long long1) {
		this.FeedbackID = long1;
		
	}
	
	@JsonProperty
	public long getID()
	{
		return FeedbackID;
	}
	
}
