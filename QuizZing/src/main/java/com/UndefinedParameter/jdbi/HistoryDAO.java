package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.joda.time.DateTime;

import com.UndefinedParameter.app.core.User;

@RegisterMapper(UserMapper.class)
public interface HistoryDAO {

	@SqlQuery("SELECT * FROM History WHERE UserID = :userid")
	public User findAllByUserID(@Bind("username") long userid);
	
	@SqlQuery("SELECT * FROM History WHERE QuizID = :quizid")
	public User findAllByQuizID(@Bind("userId") long quizid);
	
	@SqlQuery("SELECT * FROM History WHERE DateTime = :date")
	public User findAllByDateTime(@Bind("userId") DateTime date);
	
	@SqlQuery("SELECT * FROM History WHERE HistoryID = :historyid")
	public User findByHistoryID(@Bind("userId") long historyid);
	
	
	@SqlUpdate("INSERT INTO History "
			+ "(UserID, QuizID, DateTime, Score) "
			+ "values "
			+ "(:userid, :quizid, :date, :score")
	@GetGeneratedKeys
	public long insert(@Bind("userid") float userid, 
					   @Bind("quizid") float quizid,
					   @Bind("date") DateTime date,
					   @Bind("score") float datetime);	
}
