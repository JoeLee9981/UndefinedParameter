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
		
		Organization[] orgs = new Organization[10];
		
		Organization org = new Organization(0, "University Of Utah");
		org.setDescription("University of Utah is located in Salt Lake City, UT. It is the largest"
				+ "University in Utah, and was one of the first five nodes on the internet. The University of Utah"
				+ "is known for alumni who have founded Pixar, Adobe and more.");
		org.setCity("Salt Lake City");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(5421);
		org.setQuizCount(800);
		org.setQuestionCount(5000);
		
		orgs[0] = org;
		
		org = new Organization(1, "Salt Lake Community College");
		org.setDescription("Salt Lake Community College is a large community colege. It's main campus is located"
				+ "in Taylorsville Utah, and has many satellite campuses located around the valley.");
		org.setCity("Taylorsville");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(4988);
		org.setQuizCount(741);
		org.setQuestionCount(3954);
		
		orgs[1] = org;
		
		org = new Organization(2, "Utah Valley Univesirty");
		org.setDescription("Utah Valley University Description and etc.");
		org.setCity("Provo");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(4555);
		org.setQuizCount(755);
		org.setQuestionCount(3029);
		
		orgs[2] = org;
		
		org = new Organization(3, "Brigham Young University");
		org.setDescription("Mormon College located in Provo");
		org.setCity("Provo");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(965);
		org.setQuizCount(502);
		org.setQuestionCount(2500);
		
		orgs[3] = org;
		
		org = new Organization(4, "Utah State University");
		org.setDescription("University located in the mountain town of Logan. Beauiful campus resting"
				+ "in the shadow of a mountain.");
		org.setCity("Logan");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(800);
		org.setQuizCount(200);
		org.setQuestionCount(2050);
		
		orgs[4] = org;
		
		org = new Organization(5, "Weber State University");
		org.setDescription("Weber state is another utah university");
		org.setCity("Ogden");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(725);
		org.setQuizCount(644);
		org.setQuestionCount(3500);
		
		orgs[5] = org;
		
		org = new Organization(6, "Snow College");
		org.setDescription("Just another college in Ephraim.");
		org.setCity("Ephraim");
		org.setState("Utah");
		org.setCountry("United States");
		org.setMemberCount(144);
		org.setQuizCount(300);
		org.setQuestionCount(1000);
		
		orgs[6] = org;
		
		org = new Organization(7, "Harvard");
		org.setDescription("Some people say it's a good school, but eh.");
		org.setCity("Somewhere");
		org.setState("OutThere");
		org.setCountry("United States");
		org.setMemberCount(126);
		org.setQuizCount(300);
		org.setQuestionCount(1000);
		
		orgs[7] = org;
		
		org = new Organization(8, "Chemistry 1210");
		org.setDescription("A general chemistry group, groups do not have to be tied to a college, although it is useful.");
		org.setCity("Anywhere");
		org.setState("YouWant");
		org.setCountry("United States");
		org.setMemberCount(126);
		org.setQuizCount(300);
		org.setQuestionCount(1000);
		
		orgs[8] = org;
		
		org = new Organization(9, "We Love Quizzes Group");
		org.setDescription("Organizations can be just groups of people who are enthusiastic for quizzes and just want to play the"
				+ "challenge portion of our website.");
		org.setCity("Everywhere");
		org.setState("All Over");
		org.setCountry("Planet Earth");
		org.setMemberCount(9);
		org.setQuizCount(18);
		org.setQuestionCount(200);
		
		orgs[9] = org;
		
		return orgs;
	}
	
	public static Organization findOrgById(int id) {
		
		//TODO: Make this fetch from the database
		
		return findOrgsByLocation("Salt Lake City")[id];
	}
}
