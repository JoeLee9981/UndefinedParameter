package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.UserGroup;

public class UserGroupMapper implements ResultSetMapper<UserGroup> {

	public UserGroup map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		UserGroup group = new UserGroup();
		
		//TODO: Fix this, read data from result and map into object
		
		return group;
	}

}
