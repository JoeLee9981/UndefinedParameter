package com.UndefinedParameter.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.views.OrganizationView;

@Path("orgs/{id}")
@Produces(MediaType.TEXT_HTML)
public class OrganizationResource {

	@GET
	public OrganizationView getOrganizationView(@PathParam("id") int id) {
		return new OrganizationView(new Organization(id, "My organization"));
	}
}
