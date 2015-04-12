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
		que.setOrdered(r.getBoolean("Ordered"));
		que.setRating((float)r.getInt("Rating") /(float)r.getInt("RatingCount"));
		que.setQuestionText(r.getString("QuestionText"));
		String queType = r.getString("QuestionType");
		que.setQuestionType(QuestionType.valueOf(queType));
		que.setCorrectAnswer(r.getString("CorrectAnswer"));
		que.setExplanation(r.getString("Explanation"));
		que.setReference(r.getString("Reference"));
		que.setCorrectPosition(r.getInt("CorrectPosition"));
		que.setDifficulty((float)r.getInt("QuestionDifficulty") /(float)r.getInt("DifficultyCount"));
		que.setFlagged(r.getBoolean("Flagged"));
		que.setFlaggedReason(r.getString("FlaggedReason"));
		
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
		
		que.setAnswers();
		
		return que;
	}

}
