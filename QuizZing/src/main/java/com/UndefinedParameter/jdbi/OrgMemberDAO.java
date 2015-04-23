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

	/**
	 * Selects organization members, specified by the org id.
	 * @param orgId
	 * @return
	 */
	@SqlQuery("SELECT * FROM User u, Organization o, UserOrganization uo WHERE "
			+ "u.UserID = uo.UserID AND "
			+ "uo.OrgID = o.OrgID AND "
			+ "o.OrgID = :orgId")
	public List<OrgMember> retrieveMembersByOrg(@Bind("orgId") long orgId);
	
	/**
	 * Gets all group members from the group with matching group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT * FROM User u, SubGroup sg, UserGroups ug WHERE "
			+ "u.UserID = ug.UserID AND "
			+ "ug.GroupID = sg.GroupID AND "
			+ "sg.GroupID = :groupId")
	@RegisterMapper(GroupMemberMapper.class)
	public List<GroupMember> retrieveMembersByGroup(@Bind("groupId") long groupId);
	
	/**
	 * Finds all organization members with the matching org id and user id.
	 * @param orgid
	 * @param userid
	 * @return
	 */
	@SqlQuery("SELECT * FROM UserOrganization WHERE "
			+ "UserID = :userID AND "
			+ "OrgID = :orgID")
	@RegisterMapper(GroupMemberMapper.class)
	public List<OrgMember> retrieveMemberByOrgIdUserId(@Bind("orgID") long orgid,
														@Bind("userID") long userid);
	/**
	 * Find the amount of earned points that the user has gained in all groups that they are a part of, and
	 * that is a part of the organization. Determined by the user id and org id.
	 * @param orgid
	 * @param userid
	 * @return
	 */
	@SqlQuery("SELECT SUM(ug.EarnedPoints) FROM UserGroups ug, SubGroup sg, Organization og "
			+ "WHERE og.OrgID = :orgID AND "
			+ "og.OrgID = sg.OrgID AND "
			+ "sg.GroupID = ug.GroupID AND "
			+ "ug.UserID = :userID")
	public int getAmountEarnedPointsOrg(@Bind("orgID") long orgid,
										@Bind("userID") long userid);
	
	/**
	 * Find out the set mod status of a user for their organization. Determined by the org id and user id.
	 * Results will be a 1 if they have been set as a moderator, 0 if they are not a moderator.
	 * @param orgid
	 * @param userid
	 * @return
	 */
	@SqlQuery("SELECT Moderator FROM UserOrganization "
			+ "WHERE OrgID = :orgID AND "
			+ "UserID = :userID")
	public int getModStatus(@Bind("orgID") long orgid,
								@Bind("userID") long userid);
	
	/**
	 * Setting a overriding status in regards to being a moderator. Can easily be overwritten. 1 for setting as a moderator,
	 * 0 for not being a moderator. Determining done by the org id and user id. 
	 * @param status
	 * @param orgid
	 * @param userid
	 */
	@SqlUpdate("UPDATE UserOrganization SET Moderator = :status "
			+ "WHERE OrgID = :orgID AND "
			+ "UserID = :userID")
	public void setModStatus(@Bind("status") long status,
								@Bind("orgID") long orgid,
								@Bind("userID") long userid);
}
