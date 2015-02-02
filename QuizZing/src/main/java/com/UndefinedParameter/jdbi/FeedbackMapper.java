package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Feedback;

public class FeedbackMapper implements ResultSetMapper<Feedback> {

	public Feedback map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		Feedback feed = new Feedback();
		
		feed.setId(r.getLong("FeedbackID"));
		feed.setSuggestedFeature(r.getString("Feature"));
		feed.setImprovement(r.getString("Improvement"));
		feed.setMiscellaneous(r.getString("Miscellaneous"));
		
		
		return feed;
	}
	
}