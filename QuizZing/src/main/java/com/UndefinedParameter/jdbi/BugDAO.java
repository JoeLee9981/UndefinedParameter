package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Bug;

@RegisterMapper(BugMapper.class)
public interface BugDAO {

	/**
	 * Inserting an entry with the one who reported the bug, the urgancy of it, the description of the bug,
	 * and how to reproduce it (hoping we are lucky enough to get a legible entry).
	 * @param reporter
	 * @param urgency
	 * @param description
	 * @param steps
	 * @return
	 */
	@SqlUpdate("INSERT INTO BugReport (ReporterID, Urgency, Description, StepsToReproduce) VALUES(:reporterId, :urgency, :description, :steps)")
	@GetGeneratedKeys
	public long addBug(@Bind("reporterId") long reporter, @Bind("urgency") int urgency, @Bind("description") String description, @Bind("steps") String steps);
	
	/**
	 * Selecting everything from BugReport table.
	 * @return
	 */
	@SqlQuery("SELECT * FROM BugReport")
	public List<Bug> retrieveBugs();
	
}