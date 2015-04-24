package com.UndefinedParameter.app.resources;

import io.dropwizard.auth.Auth;

import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.UndefinedParameter.app.core.Bug;
import com.UndefinedParameter.app.core.Feedback;
import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.jdbi.BugDAO;
import com.UndefinedParameter.jdbi.FeedbackDAO;
import com.UndefinedParameter.views.BugReportView;
import com.UndefinedParameter.views.BugView;
import com.UndefinedParameter.views.FeedbackResultView;
import com.UndefinedParameter.views.FeedbackView;
import com.UndefinedParameter.views.LoginView;


/**
 * This is the resource used to handle the path and sub
 * 	paths for all /feedback requests
 *
 */
@Path("/feedback")
@Produces(MediaType.TEXT_HTML)
public class FeedbackResource {
	//dao objects
	private FeedbackDAO feedbackDAO;
	private BugDAO bugDAO;
	
	/**
	 * Construtor
	 * @param feedbackDAO
	 * @param bugDAO
	 */
	public FeedbackResource(FeedbackDAO feedbackDAO, BugDAO bugDAO) {
		this.feedbackDAO = feedbackDAO;
		this.bugDAO = bugDAO;
	}

	/**
	 * Creates a view for feedback
	 * @param user
	 * @return
	 */
	@GET
	public FeedbackView getFeedbackView(@Auth(required=false) User user) {
		return new FeedbackView(user);
	}
	
	/**
	 * Creates a view to allow viewing the feedback
	 * @param user
	 * @return
	 */
	@GET
	@Path("/view")
	public FeedbackResultView getFeedbackResultsView(@Auth(required=false) User user) {
		return new FeedbackResultView(user, feedbackDAO.retrieveFeedback());
	}
	
	/**
	 * Posts feedback from the user into the database
	 * @param feedback
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addGroup(@Valid Feedback feedback) {
		
		feedbackDAO.addFeedback(feedback.getSuggestedFeature(), feedback.getImprovement(), feedback.getMiscellaneous());
	}
	
	/**
	 * Creates a view for a bug report
	 * @param user
	 * @return
	 */
	@GET
	@Path("/bug")
	public Response getBugView(@Auth(required=false) User user) {

		return Response.ok(new BugView(user)).build();
	}
	
	/**
	 * Posts a bug report into the database
	 * @param user
	 * @param bug
	 * @return
	 */
	@POST
	@Path("/bug")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBug(@Auth(required = false) User user, @Valid Bug bug) {
		long bugId;
		HashMap<String, String> response = new HashMap<String, String>();
		
		if(user != null)
			bugId = bugDAO.addBug(user.getId(), bug.getUrgency(), bug.getDescription(), bug.getStepsToReproduce());
		else
			bugId = bugDAO.addBug(0, bug.getUrgency(), bug.getDescription(), bug.getStepsToReproduce());
		
		if(bugId > 0) {
			response.put("response", "success");
			response.put("redirect", "/");
		}
		else {
			response.put("response", "fail");
			response.put("message", "Unable to submit the bug report, please try again.");
		}
		
		return Response.ok(response).build();
	}
	
	/**
	 * Returns a view to view all the reported bugs
	 * @param user
	 * @return
	 */
	@GET
	@Path("/bug/view")
	public Response viewBugs(@Auth(required = false) User user) {
		
		if(user != null) {
			return Response.ok(new BugReportView(user, bugDAO.retrieveBugs())).build();
		}
		else {
			return Response.ok(new LoginView(user)).build();
		}
	}
}

