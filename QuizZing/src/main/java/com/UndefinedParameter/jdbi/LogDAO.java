package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.User;

@RegisterMapper(UserMapper.class)
public interface LogDAO {

	/**
	 * Selects everything from log that matches the user id.
	 * @param userid
	 * @return
	 */
	@SqlQuery("SELECT * FROM Log WHERE UserID = :userid")
	public User findAllByUserID(@Bind("username") long userid);
	
	/**
	 * Selects everything from Log that matches the Group id.
	 * @param groupid
	 * @return
	 */
	@SqlQuery("SELECT * FROM Log WHERE GroupID = :groupid")
	public User findAllByGroupID(@Bind("userid") long groupid);

	/**
	 * Selects and returns everything from Log that matches the Org id.
	 * @param orgid
	 * @return
	 */
	@SqlQuery("SELECT * FROM Log WHERE OrgID = :orgid")
	public User findAllByOrgID(@Bind("orgid") long orgid);
	
	/**
	 * Inserts an entry into Log with the specified org id, group id, user id, and the strong log entry.
	 * @param orgid
	 * @param groupid
	 * @param userid
	 * @param log
	 * @return
	 */
	@SqlUpdate("INSERT INTO Log "
			+ "(OrgID, GroupID, UserID, Log) "
			+ "values "
			+ "(:orgid, :groupid, :userid, :log")
	@GetGeneratedKeys
	public long insert(@Bind("orgid") long orgid, 
					   @Bind("groupid") long groupid,
					   @Bind("useride") long userid,
					   @Bind("log") String log);	
}
