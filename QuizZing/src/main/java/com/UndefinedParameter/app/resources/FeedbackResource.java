package com.UndefinedParameter.app.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Feedback;
import com.UndefinedParameter.jdbi.FeedbackDAO;
import com.UndefinedParameter.views.FeedbackResultView;
import com.UndefinedParameter.views.FeedbackView;

@Path("/feedback")
@Produces(MediaType.TEXT_HTML)
public class FeedbackResource {
	private FeedbackDAO feedbackDAO;
	
	public FeedbackResource(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}

	
	@GET
	public FeedbackView getFeedbackView() {
		return new FeedbackView();
	}
	
	@GET
	@Path("/view")
	public FeedbackResultView getFeedbackResultsView() {
		return new FeedbackResultView(feedbackDAO.retrieveFeedback());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addGroup(@Valid Feedback feedback) {
		
		feedbackDAO.addFeedback(feedback.getSuggestedFeature(), feedback.getImprovement(), feedback.getMiscellaneous());
	}
}

