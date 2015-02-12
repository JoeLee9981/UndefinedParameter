package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.History;

public class HistoryMapper implements ResultSetMapper<History>{

	public History map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		History hist = new History();
		
		hist.setId(r.getInt("HistoryID"));
		hist.setUserID(r.getInt("UserID"));
		hist.setQuizID(r.getInt("QuizID"));
		hist.setDateTime(r.getDate("DateTime"));
		hist.setScore(r.getFloat("Score"));
		
		return hist;
	}

}
