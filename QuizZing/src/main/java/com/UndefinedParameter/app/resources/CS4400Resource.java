package com.UndefinedParameter.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.views.CS4400View;

@Path("/group/CS4400")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class CS4400Resource {
	
	@GET
	public CS4400View getGroupsView() {
		return new CS4400View();
	}
}
