package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.Question;
import com.UndefinedParameter.app.core.Question.QuestionType;

public class QuestionMapper implements ResultSetMapper<Question>{

	public Question map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		Question que = new Question();
		
		que.setQuestionId(r.getLong("QuestionID"));
		que.setCreatorId(r.getLong("CreatorID"));
		que.setGroupId(r.getLong("GroupID"));
		que.setQuestionDifficulty(r.getFloat("QuestionDifficulty"));
		que.setRating(r.getFloat("Rating"));
		que.setQuestionText(r.getString("QuestionText"));
		que.setQuestionType(QuestionType.valueOf(r.getString("QuestionType")));
		que.setCorrectAnswer(r.getString("CorrectAnswer"));
		que.setExplanation(r.getString("Explanation"));
		que.setReference(r.getString("Reference"));
		
		ArrayList<String> wronganswers = new ArrayList<String>();
		
		String wrong1 = r.getString("WrongAnswer1");
		String wrong2 = r.getString("WrongAnswer2");
		String wrong3 = r.getString("WrongAnswer3");
		String wrong4 = r.getString("WrongAnswer4");
		//only add the wrong answers if not null
		if(!"".equals(wrong1))
			wronganswers.add(wrong1);
		if(!"".equals(wrong2))
			wronganswers.add(wrong2);
		if(!"".equals(wrong3))
			wronganswers.add(wrong3);
		if(!"".equals(wrong4))
			wronganswers.add(wrong4);
		
		que.setWrongAnswers(wronganswers);
		
		return que;
	}

}
