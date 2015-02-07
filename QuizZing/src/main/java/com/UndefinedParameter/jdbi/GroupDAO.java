package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Group;

@RegisterMapper(GroupMapper.class)
public interface GroupDAO {

	@SqlQuery("SELECT * FROM SubGroup WHERE OrgID = :orgId")
	public List<Group> findGroupsByOrgId(@Bind("orgId") long id);
	
	@SqlQuery("SELECT * FROM SubGroup WHERE GroupId = :groupId")
	public Group findGroupById(@Bind("groupId") long id);
	
	@SqlQuery("SELECT * FROM UserGroups ug, SubGroup sg, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId AND "
			+ "sg.OrgID = :orgId")
	public List<Group> findGroupsByUser(@Bind("userId") long userId, @Bind("orgId") long orgId);
	
	@SqlUpdate("INSERT INTO SubGroup (Name, Description, OrgID) VALUES (:name, :desc, :orgId)")
	@GetGeneratedKeys
	public long insertGroup(@Bind("name") String name, 
							@Bind("desc") String description, 
							@Bind("orgId") long orgId);
	
	@SqlUpdate("INSERT INTO UserGroups (UserID, GroupID) VALUES (:userId, :groupId)")
	public void registerGroup(@Bind("userId") long userId, @Bind("groupId") long groupId);
	
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount + 1 WHERE GroupID = :groupId")
	public void incrementGroupMembers(@Bind("groupId") long groupId);
	
	@SqlUpdate("DELETE FROM UserGroups WHERE GroupID = :groupId AND UserID = :userId")
	public void removeUserGroup(@Bind("groupId") long groupId, @Bind("userId") long userId);
	
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount - 1 WHERE GroupID = :groupId")
	public void decrementGroupMembers(@Bind("groupId") long groupId);
}
