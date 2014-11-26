package com.UndefinedParameter.app.core;

public class OrganizationManager {
	
	/*
	 * Find groups within an organization by id
	 * 		Category is the category the groups belong to
	 * 		for example CS or Computer Science. This would return
	 * 		all classes related to the CS category for the organization
	 */
	public static Group[] findGroupsByCategory(int orgId, int categoryId) {
		//TODO: Implement - note may want to use helper methods to group id's
				// then retrieve the groups
		return null;
	}
	
	public static Group[] findGroupsByCategory(int orgId, String category) {
		int catId = 0;//TODO: Find the category id from database
		return findGroupsByCategory(orgId, catId);
	}
	
	/*
	 * Finds all of the groups by an organizations id
	 */
	public static Group[] findGroupsById(int orgId) {
		//TOOD: Implement
		return null;
	}
	
	public static Organization[] findOrgsByLocation(String city) {
		
		//TODO: Get these from the database instead of hard coding
		
		Organization[] orgs = new Organization[5];
		
		Organization org = new Organization(0, "University Of Utah");
		org.setDescription("University of Utah is located in Salt Lake City, UT. It is the largest"
				+ "University in Utah, and was one of the first five nodes on the internet. The University of Utah"
				+ "is known for alumni who have founded Pixar, Adobe and more.");
		org.setCity("Salt Lake City");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[0] = org;
		
		org = new Organization(1, "Salt Lake Community College");
		org.setDescription("Salt Lake Community College is a large community colege. It's main campus is located"
				+ "in Taylorsville Utah, and has many satellite campuses located around the valley.");
		org.setCity("Taylorsville");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[1] = org;
		
		org = new Organization(2, "Utah Valley Univesirty");
		org.setDescription("Utah Valley University Description and etc.");
		org.setCity("Provo");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[2] = org;
		
		org = new Organization(3, "Brigham Young University");
		org.setDescription("Mormon College located in Provo");
		org.setCity("Provo");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[3] = org;
		
		org = new Organization(4, "Utah State University");
		org.setDescription("University located in the mountain town of Logan. Beauiful campus resting"
				+ "in the shadow of a mountain.");
		org.setCity("Logan");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[4] = org;
		
		return orgs;
	}
	
	public static Organization findOrgById(int id) {
		
		//TODO: Make this fetch from the database
		
		Organization[] orgs = new Organization[5];
		
		Organization org = new Organization(0, "University Of Utah");
		org.setDescription("University of Utah is located in Salt Lake City, UT. It is the largest"
				+ "University in Utah, and was one of the first five nodes on the internet. The University of Utah"
				+ "is known for alumni who have founded Pixar, Adobe and more.");
		org.setCity("Salt Lake City");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[0] = org;
		
		org = new Organization(1, "Salt Lake Community College");
		org.setDescription("Salt Lake Community College is a large community colege. It's main campus is located"
				+ "in Taylorsville Utah, and has many satellite campuses located around the valley.");
		org.setCity("Taylorsville");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[1] = org;
		
		org = new Organization(2, "Utah Valley Univesirty");
		org.setDescription("Utah Valley University Description and etc.");
		org.setCity("Provo");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[2] = org;
		
		org = new Organization(3, "Brigham Young University");
		org.setDescription("Mormon College located in Provo");
		org.setCity("Provo");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[3] = org;
		
		org = new Organization(4, "Utah State University");
		org.setDescription("University located in the mountain town of Logan. Beauiful campus resting"
				+ "in the shadow of a mountain.");
		org.setCity("Logan");
		org.setState("Utah");
		org.setCountry("United States");
		
		orgs[4] = org;
		
		return orgs[id];
	}
}
