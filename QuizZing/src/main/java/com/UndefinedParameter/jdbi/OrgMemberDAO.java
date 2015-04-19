package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.GroupMember;
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
	public List<GroupMember> retrieveMembersByGroup(@Bind("groupId") long groupId);
	
	@SqlQuery("SELECT * FROM UserOrganization WHERE "
			+ "UserID = :userID AND "
			+ "OrgID = :orgID")
	@RegisterMapper(GroupMemberMapper.class)
	public List<OrgMember> retrieveMemberByOrgIdUserId(@Bind("orgID") long orgid,
														@Bind("userID") long userid);
	
	@SqlQuery("SELECT SUM(ug.EarnedPoints) FROM UserGroups ug, SubGroup sg, Organization og"
			+ " WHERE og.OrgID = :orgID AND"
			+ "og.OrgID = sg.OrgID AND"
			+ "sg.GroupID = ug.GroupID AND "
			+ "ug.UserID = :userID")
	public long getAmountEarnedPointsOrg(@Bind("orgID") long orgid,
										@Bind("userID") long userid);
	
	@SqlQuery("SELECT Moderator FROM UserOrganization "
			+ "WHERE OrgID = :orgID AND"
			+ "UserID = :userID")
	public int getModStatus(@Bind("orgID") long orgid,
								@Bind("userID") long userid);
	
	@SqlUpdate("UPDATE UserOrganization SET Moderator = :status "
			+ "WHERE OrgID = :orgID AND"
			+ "UserID = :userID")
	public void setModStatus(@Bind("status") long status,
								@Bind("orgID") long orgid,
								@Bind("userID") long userid);
}
