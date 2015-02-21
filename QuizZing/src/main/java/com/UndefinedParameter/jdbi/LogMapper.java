package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Log;

public class LogMapper implements ResultSetMapper<Log>{

	public Log map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		Log log = new Log();
		
		log.setId(r.getInt("LogID"));
		log.setUserID(r.getInt("UserID"));
		log.setGroupID(r.getInt("GroupID"));
		log.setOrgID(r.getInt("OrgID"));
		log.setLog(r.getString("Log"));
		
		return log;
	}

}
