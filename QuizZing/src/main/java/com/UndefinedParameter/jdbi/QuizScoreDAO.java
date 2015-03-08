package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.joda.time.DateTime;

import com.UndefinedParameter.app.core.QuizScore;

@RegisterMapper(UserMapper.class)
public interface QuizScoreDAO {

	@SqlQuery("SELECT * FROM QuizScore WHERE UserID = :userId")
	public UserDAO findUserByUserId(@Bind("userId") long userId);
	
	@SqlQuery("SELECT * FROM QuizScore WHERE QuizID = :quizid")
	public UserDAO findUserByQuizID(@Bind("userId") long quizid);
	
	@SqlQuery("SELECT * FROM QuizScore WHERE QuizID = :quizid AND UserID = :userId")
	public UserDAO findUserByQuizID(@Bind("quizId") long quizid, @Bind("userId") long userid);
	
		
	@SqlUpdate("INSERT INTO QuizScore "
			+ "(UserID, QuizID, DateTime, Score) "
			+ "values "
			+ "(:userid, :quizid, :datetime, :score)")
	@GetGeneratedKeys
	public long insert(@Bind("userid") long userid, 
					   @Bind("quizid") long quizid,
					   @Bind("datetime") DateTime datetime,
					   @Bind("score") float score);
	
}