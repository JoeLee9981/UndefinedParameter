package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Group;

public class GroupMapper implements ResultSetMapper<Group> {

	public Group map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		Group group = new Group();
		
		group.setId(r.getInt("GroupID"));
		group.setName(r.getString("Name"));
		group.setDescription(r.getString("Description"));
		group.setOrganizationId(r.getInt("OrgID"));
		group.setMemberCount(r.getInt("MemberCount"));
		group.setQuizCount(r.getInt("QuizCount"));
		group.setQuestionCount(r.getInt("QuestionCount"));
		group.setDateCreated(new DateTime(r.getDate("DateCreated")));
		
		return group;
	}

}
