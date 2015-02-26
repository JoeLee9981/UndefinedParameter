package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Organization;

public class OrganizationMapper implements ResultSetMapper<Organization>{

	private OrganizationDAO orgDAO;
	
	public Organization map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		Organization org = new Organization();
		
		org.setId(r.getInt("OrgID"));
		org.setName(r.getString("Name"));
		org.setDescription(r.getString("Description"));
		org.setCity(r.getString("City"));
		org.setState(r.getString("State"));
		org.setCountry(r.getString("Country"));
		org.setMemberCount(r.getInt("MemberCount"));
		org.setQuizCount(r.getInt("QuizCount"));
		org.setQuestionCount(r.getInt("QuestionCount"));
		org.setDateCreated(new DateTime(r.getDate("DateCreated")));
		org.setRating(Math.round((float)r.getInt("Rating") / (float)r.getInt("RatingCount")));
		
		return org;
	}

	
	public boolean rateOrganization(long userId, long orgId, int rating) {
		
		if(userId < 1 || orgId < 1 || rating < 1 || rating > 5) {
			//this is invalid
			return false;
		}
		try {
			int existingRating = orgDAO.getOrganizationRating(orgId);
			int existingRatingAmount = orgDAO.getOrganizationRatingCount(orgId);
			int existingUserRating = orgDAO.getUserOrganizationRating(orgId, userId);
			
			if(existingUserRating == 0)
			{
				existingRatingAmount = existingRatingAmount + 1;
				existingRating = (existingRating + rating) / existingRatingAmount;
				
				orgDAO.updateUserOrgRating(orgId, userId, rating);
				
				orgDAO.updateOrganizationRating(orgId, existingRating);
				orgDAO.updateOrganizationRatingCount(orgId, existingRatingAmount);
				
				//orgDAO.updateQuizRating(userId, orgId, rating);
				//orgDAO.updateQuizQualityRating(rating - existingRating, orgId);
			}
			else
			{
				int newrating = rating - existingRating;
				existingRating = (existingRating + newrating) / existingRatingAmount;
				
				orgDAO.updateOrganizationRating(orgId, existingRating);
				
				orgDAO.updateUserOrgRating(orgId, userId, rating);
			}
			return true;
		}
		catch(Exception e) {
			//database insert fails
			return false;
		}
	}
}
