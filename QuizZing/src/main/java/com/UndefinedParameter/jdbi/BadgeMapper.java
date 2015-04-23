package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Badge;

public class BadgeMapper implements ResultSetMapper<Badge> {

	public Badge map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		Badge badge = new Badge();
		
		badge.setUserId(r.getLong("UserID"));
		badge.setGroupId(r.getLong("GroupID"));
		badge.setGroupName(r.getString("Name"));
		badge.setContribution(r.getInt("EarnedPoints"));

		return badge;
	}

}