package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Organization;

public class OrganizationMapper implements ResultSetMapper<Organization>{

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
		
		return org;
	}

}
