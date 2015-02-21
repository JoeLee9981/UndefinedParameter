package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.User;

@RegisterMapper(UserMapper.class)
public interface LogDAO {

	@SqlQuery("SELECT * FROM Log WHERE UserID = :userid")
	public User findAllByUserID(@Bind("username") long userid);
	
	@SqlQuery("SELECT * FROM Log WHERE GroupID = :groupid")
	public User findAllByGroupID(@Bind("userid") long groupid);

	@SqlQuery("SELECT * FROM Log WHERE OrgID = :orgid")
	public User findAllByOrgID(@Bind("orgid") long orgid);
	
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
