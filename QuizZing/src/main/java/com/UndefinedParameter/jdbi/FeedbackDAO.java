package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Feedback;

@RegisterMapper(FeedbackMapper.class)
public interface FeedbackDAO {

	/**
	 * Inserting a string or multiple strings into the feedback table.
	 * @param feedback
	 * @param improvement
	 * @param miscellanoues
	 * @return
	 */
	@SqlUpdate("INSERT INTO Feedback (Feature, Improvement, Miscellaneous) VALUES(:feature, :improvement, :miscellaneous)")
	@GetGeneratedKeys
	public long addFeedback(@Bind("feature") String feedback, @Bind("improvement") String improvement, @Bind("miscellaneous") String miscellanoues);
	
	/**
	 * Select all from the feedback table.
	 * @return
	 */
	@SqlQuery("SELECT * FROM Feedback")
	public List<Feedback> retrieveFeedback();
	
}
