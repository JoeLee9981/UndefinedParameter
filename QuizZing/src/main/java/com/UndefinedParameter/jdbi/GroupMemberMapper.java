package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.OrgMember;


public class GroupMemberMapper implements ResultSetMapper<OrgMember> {
	
	public OrgMember map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		OrgMember member = new OrgMember();
		
		member.setUserId(r.getLong("UserID"));
		member.setOrgId(r.getLong("GroupID"));
		member.setDisplayName(r.getString("FirstName") + " " + r.getString("LastName").substring(0, 1));
		
		member.setModerator(r.getBoolean("ModStatus"));
		member.setContribution(r.getInt("EarnedPoints"));
		member.setJoinDate(new DateTime(r.getDate("JoinDate")));
		member.setQuizzes(10);
		member.setQuestions(100);

		return member;
	}
}