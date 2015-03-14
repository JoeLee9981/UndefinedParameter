package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.OrgMember;


public class OrgMemberMapper implements ResultSetMapper<OrgMember> {
	
	public OrgMember map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		OrgMember member = new OrgMember();
		
		member.setUserId(r.getLong("UserID"));
		member.setOrgId(r.getLong("UserID"));
		member.setDisplayName(r.getString("FirstName") + " " + r.getString("LastName").substring(0, 1));
		
		member.setContribution(r.getInt("Rating"));
		member.setJoinDate(new DateTime(r.getDate("JoinDate")));
		member.setQuizzes(10);
		member.setQuestions(100);

		return member;
	}
}
