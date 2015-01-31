package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Question.QuestionType;

public class QuestionMapper implements ResultSetMapper<Question>{

	public Question map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		Question que = new Question();
		
		que.setQuestionId(r.getInt("QuestionID"));
		que.setCreatorId(r.getInt("CreatorID"));
		que.setQuestionDifficulty(r.getFloat("QuestionDifficulty"));
		que.setRating(r.getFloat("Rating"));
		que.setQuestionText(r.getString("QuestionText"));
		que.setQuestionType(QuestionType.valueOf(r.getString("QuestionType")));
		que.setCorrectAnswer(r.getString("CorrectAnswer"));
		
		ArrayList<String> wronganswers = null;
		
		wronganswers.add(r.getString("WrongAnswer1"));
		wronganswers.add(r.getString("WrongAnswer2"));
		wronganswers.add(r.getString("WrongAnswer3"));
		wronganswers.add(r.getString("WrongAnswer4"));
		que.setWrongAnswers(wronganswers);
		
		/*
		org.setId(r.getInt("OrgID"));
		org.setName(r.getString("Name"));
		org.setDescription(r.getString("Description"));
		org.setCity(r.getString("City"));
		org.setState(r.getString("State"));
		org.setCountry(r.getString("Country"));
		org.setMemberCount(r.getInt("MemberCount"));
		org.setQuizCount(r.getInt("QuizCount"));
		org.setQuestionCount(r.getInt("QuestionCount"));
		org.setDateCreated(new DateTime(r.getDate("DateCreated")));
		
		return org;
		*/
		return que;
	}

}
