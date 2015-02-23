package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.OrganizationType;

public class OrganizationTypeMapper implements ResultSetMapper<OrganizationType>{

	public OrganizationType map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		OrganizationType orgType = new OrganizationType();
		
		orgType.setTypeName(r.getString("Type"));

		return orgType;
	}

}
