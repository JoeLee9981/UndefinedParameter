package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;

import com.UndefinedParameter.app.core.Feedback;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackResultView extends View {
	
	private ArrayList<Feedback> feedbackList;
	
	public FeedbackResultView(ArrayList<Feedback> feedbackList) {
		super("feedbackresults.ftl");
		this.feedbackList = feedbackList;
	}

	@JsonProperty
	public ArrayList<Feedback> getFeedbackList() {
		return feedbackList;
	}

	@JsonProperty
	public void setFeedbackList(ArrayList<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	
}
