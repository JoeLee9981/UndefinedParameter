package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.OrgMember;

@RegisterMapper(OrgMemberMapper.class)
public interface OrgMemberDAO {

	@SqlQuery("SELECT * FROM User u, Organization o, UserOrganization uo WHERE "
			+ "u.UserID = uo.UserID AND "
			+ "uo.OrgID = o.OrgID AND "
			+ "o.OrgID = :orgId")
	public List<OrgMember> retrieveMembersByOrg(@Bind("orgId") long orgId);
	
	
	@SqlQuery("SELECT * FROM User u, SubGroup sg, UserGroups ug WHERE "
			+ "u.UserID = ug.UserID AND "
			+ "ug.GroupID = sg.GroupID AND "
			+ "sg.GroupID = :groupId")
	@RegisterMapper(GroupMemberMapper.class)
	public List<OrgMember> retrieveMembersByGroup(@Bind("groupId") long groupId);
}
