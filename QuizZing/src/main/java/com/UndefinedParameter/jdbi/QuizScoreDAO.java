package com.UndefinedParameter.jdbi;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.joda.time.DateTime;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizScore;

@RegisterMapper(QuizScoreMapper.class)
public interface QuizScoreDAO {

	@SqlQuery("SELECT * FROM QuizScore WHERE UserID = :userId")
	public List<QuizScore> findScoresByUserId(@Bind("userId") long userId);
	
	@SqlQuery("SELECT * FROM QuizScore WHERE QuizID = :quizId")
	public List<QuizScore> findScoresByQuizID(@Bind("quizId") long quizId);
	
	@SqlQuery("SELECT * FROM QuizScore WHERE QuizID = :quizId AND UserID = :userId")
	public List<QuizScore> findScoresByQuizAndUser(@Bind("quizId") long quizId, @Bind("userId") long userId);
	
	/*
	 * Retrieve participated quizzes by user ID
	 */
	@SqlQuery("SELECT DISTINCT Quiz.* FROM Quiz, QuizScore "
		+ "WHERE QuizScore.UserID = :userId "
		+ "AND QuizScore.QuizID = Quiz.QuizID")
	@RegisterMapper(QuizMapper.class)
	public List<Quiz> retrieveQuizzesParticipated(@Bind("userId") long userId);
	
		
	@SqlUpdate("INSERT INTO QuizScore "
			+ "(UserID, QuizID, Score) "
			+ "values "
			+ "(:userid, :quizid, :score)")
	@GetGeneratedKeys
	public long insert(@Bind("userid") long userid, 
					   @Bind("quizid") long quizid,
					   @Bind("score") float score);
	
}