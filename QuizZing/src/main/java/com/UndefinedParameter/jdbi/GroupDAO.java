package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Group;

@RegisterMapper(GroupMapper.class)
public interface GroupDAO {

	@SqlQuery("SELECT * FROM SubGroup WHERE OrgID = :orgId")
	public List<Group> findGroupsByOrgId(@Bind("orgId") int id);
	
	@SqlQuery("SELECT * FROM SubGroup WHERE GroupId = :groupId")
	public Group findGroupById(@Bind("groupId") int id);
	
	@SqlUpdate("INSERT INTO SubGroup (Name, Description, OrgID) VALUES (:name, :desc, :orgId)")
	public void insertGroup(@Bind("name") String name, 
							@Bind("desc") String description, 
							@Bind("orgId") int orgId);
}
