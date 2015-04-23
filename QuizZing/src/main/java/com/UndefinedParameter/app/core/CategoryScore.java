package com.UndefinedParameter.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryScore {
	private long categoryId;
	private String category;
	private double score;
	
	@JsonProperty
	public long getCategoryId() {
		return categoryId;
	}
	
	@JsonProperty
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	@JsonProperty
	public String getCategory() {
		return category;
	}
	
	@JsonProperty
	public void setCategory(String category) {
		this.category = category;
	}
	
	@JsonProperty
	public double getScore() {
		return score;
	}
	
	@JsonProperty
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
