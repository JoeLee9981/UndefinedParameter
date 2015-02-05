package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.UserGroup;

@RegisterMapper(UserMapper.class)
public interface UserGroupDAO {

	@SqlQuery("SELECT * FROM UserGroup WHERE GroupID = :groupid")
	public UserGroup findUserByGroupId(@Bind("groupid") int groupid);
	
	@SqlQuery("SELECT * FROM UserGroup WHERE UserID = :userid")
	public UserGroup findUserByUserId(@Bind("userid") int userid);
	

	@SqlUpdate("INSERT INTO UserGroup "
		+ "(UserID, GroupID) "
		+ "values "
		+ "(:userid, :groupid)")
	@GetGeneratedKeys
	public void insert(@Bind("userid") int userid,
						@Bind("groupid") int groupid);

	@SqlUpdate("DELETE FROM UserGroup WHERE GroupID = :gId AND UserID = :uId")
	public void delete(@Bind("uId") int user,
						@Bind("gId") int group);
}
