package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Quiz;

public class QuizMapper implements ResultSetMapper<Quiz> {

	public Quiz map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		Quiz quiz = new Quiz();
		
		quiz.setQuizId(r.getLong("QuizID"));
		quiz.setName(r.getString("Name"));
		quiz.setCreatorId(r.getLong("CreatorID"));
		quiz.setDifficulty(r.getInt("Difficulty"));
		quiz.setRating((float)r.getInt("Rating") / (float)r.getInt("RatingCount"));
		quiz.setDescription(r.getString("Description"));
		quiz.setTime(r.getInt("Time"));
		quiz.setQuestionCount(r.getInt("QuestionCount"));
		
		return quiz;
	}
}
