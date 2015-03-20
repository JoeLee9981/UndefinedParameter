package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.QuizScore;

public class QuizScoreMapper implements ResultSetMapper<QuizScore> {
	
	public QuizScore map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		QuizScore quizscore = new QuizScore();
		
		
		quizscore.setId(r.getInt("QuizScoreID"));
		quizscore.setUserId(r.getLong("UserID"));
		quizscore.setQuizId(r.getLong("QuizID"));
		
		//quizscore.setDateTime()
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dt = formatter.parseDateTime(r.getString("DateTime"));
		quizscore.setDateTime(dt);
		quizscore.setScore(r.getFloat("Score"));
		
		return quizscore;
	}

}
