package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.Feedback;
import com.UndefinedParameter.app.core.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackResultView extends View {
	
	private List<Feedback> feedbackList;
	private User user;
	
	public FeedbackResultView(User user, List<Feedback> feedbackList) {
		super("feedbackresults.ftl");
		this.feedbackList = feedbackList;
		this.user = user;
	}

	@JsonProperty
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	@JsonProperty
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	public User getUser() {
		return user;
	}
}
