package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Bug;

public class BugMapper implements ResultSetMapper<Bug> {

	public Bug map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		Bug bug = new Bug();
		
		bug.setBugReportId(r.getLong("BugReportID"));
		bug.setReporterId(r.getLong("ReporterID"));
		bug.setDescription(r.getString("Description"));
		bug.setStepsToReproduce(r.getString("StepsToReproduce"));
		bug.setUrgency(r.getInt("Urgency"));
		
		return bug;
	}
	
}