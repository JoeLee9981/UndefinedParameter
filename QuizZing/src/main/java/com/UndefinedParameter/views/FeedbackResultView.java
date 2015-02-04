package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Feedback;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackResultView extends View {
	
	private List<Feedback> feedbackList;
	
	public FeedbackResultView(List<Feedback> feedbackList) {
		super("feedbackresults.ftl");
		this.feedbackList = feedbackList;
	}

	@JsonProperty
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	@JsonProperty
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	
}
