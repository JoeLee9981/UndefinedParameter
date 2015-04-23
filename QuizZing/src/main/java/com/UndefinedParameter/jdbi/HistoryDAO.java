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

	/**
	 * Select all of the entries from History by a user, specified by the user id
	 * @param userid
	 * @return
	 */
	@SqlQuery("SELECT * FROM History WHERE UserID = :userid")
	public User findAllByUserID(@Bind("username") long userid);
	
	/**
	 * Finds all the entries for a specific quiz.
	 * @param quizid
	 * @return
	 */
	@SqlQuery("SELECT * FROM History WHERE QuizID = :quizid")
	public User findAllByQuizID(@Bind("userId") long quizid);
	
	/**
	 * Find all the entries that match the specified DateTime entry.
	 * @param date
	 * @return
	 */
	@SqlQuery("SELECT * FROM History WHERE DateTime = :date")
	public User findAllByDateTime(@Bind("userId") DateTime date);
	
	/**
	 * Returns the specific entry from History, specified by the history id.
	 * @param historyid
	 * @return
	 */
	@SqlQuery("SELECT * FROM History WHERE HistoryID = :historyid")
	public User findByHistoryID(@Bind("userId") long historyid);
	
	/**
	 * Inserting an entry into History, with the specified user id, quiz id, DateTime entry, and the score of the quiz.
	 * @param userid
	 * @param quizid
	 * @param date
	 * @param datetime
	 * @return
	 */
	@SqlUpdate("INSERT INTO History "
			+ "(UserID, QuizID, DateTime, Score) "
			+ "values "
			+ "(:userid, :quizid, :date, :score")
	@GetGeneratedKeys
	public long insert(@Bind("userid") float userid, 
					   @Bind("quizid") float quizid,
					   @Bind("date") DateTime date,
					   @Bind("score") float score);	
}
