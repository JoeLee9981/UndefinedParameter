package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.UserGroup;

@RegisterMapper(UserGroupMapper.class)
public interface UserGroupDAO {

	@SqlQuery("SELECT * FROM UserGroups WHERE GroupID = :groupid")
	public UserGroup findUserByGroupId(@Bind("groupid") int groupid);
	
	@SqlQuery("SELECT * FROM UserGroups WHERE UserID = :userid")
	public UserGroup findUserByUserId(@Bind("userid") int userid);
	
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE UserID = :userID AND GroupID = :groupID")
	public long findUserGroupCount(@Bind("userID") long userId, @Bind("groupID") long groupId);
	
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount - 1 WHERE GroupID = :groupId")
	public void decrementGroupMembers(@Bind("groupId") long groupId);
	
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount + 1 WHERE GroupID = :groupId")
	public void incrementGroupMembers(@Bind("groupId") long groupId);
	
	@SqlUpdate("INSERT INTO UserGroups "
		+ "(UserID, GroupID) "
		+ "VALUES "
		+ "(:userid, :groupid)")
	@GetGeneratedKeys
	public long insert(@Bind("userid") long userid,
						@Bind("groupid") long groupid);

	@SqlUpdate("DELETE FROM UserGroups WHERE GroupID = :gId AND UserID = :uId")
	public void delete(@Bind("uId") long userID,
						@Bind("gId") long groupID);
	
	@SqlUpdate("UPDATE UserGroups SET Rating = :rating WHERE GroupID = :groupID AND UserID = :userID")
	public void updateUserGroupRating(@Bind("groupID") long groupID,
									@Bind("userID") long userID,
									@Bind("rating") int rating);
	
	@SqlUpdate("UPDATE SubGroup SET Rating = Rating + :rating WHERE GroupID = :groupID")
	public int updateSubGroupRating(@Bind("groupID") long groupId, @Bind("rating") int rating);
	
	@SqlQuery("SELECT Rating FROM SubGroup WHERE GroupID = :groupID")
	public int getSubGroupRating(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT RatingCount FROM SubGroup WHERE GroupID = :groupID")
	public int getSubGroupRatingCount(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT Rating FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupRating(@Bind("groupID") long orgID,
									@Bind("userID") long userID);
	
	@SqlUpdate("UPDATE SubGroup SET RatingCount = RatingCount + 1 WHERE GroupID = :groupID")
	public int updateSubGroupRatingCount(@Bind("groupID") long groupId);
	
	@SqlUpdate("UPDATE UserGroups SET EarnedPoints = EarnedPoints + :points WHERE GroupID = :groupID AND UserID = :userID")
	public int addInUserGroupEarnedPoints(@Bind("groupID") long groupId,
											@Bind("userID") long userId,
											@Bind("points") long points);
	
	@SqlQuery("SELECT EarnedPoints FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupEarnedPoints(@Bind("groupID") long groupId,
											@Bind("userID") long userId);
	
	@SqlUpdate("UPDATE UserGroups SET ModStatus = :modstatus WHERE GroupID = :groupID AND UserID = :userID")
	public int updateUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("userID") long userId,
											@Bind("modstatus") int modstat);
	
	@SqlQuery("SELECT ModStatus FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupModStatus(@Bind("groupID") long groupId, @Bind("userID") long userId);
	
	@SqlQuery("SELECT UserID FROM UserGroups WHERE GroupID = :groupID AND ModStatus = :modstatus")
	public List<Long> getUsersBasedOnModStatus(@Bind("groupID") long groupId,
											@Bind("modstatus") long modstatus);
	
	@SqlQuery("SELECT GroupID FROM UserGroups WHERE UserID = :userID AND ModStatus = :modstatus")
	public List<Long> getGroupsBasedOnModStatus(@Bind("userID") long userId,
											@Bind("modstatus") long modstatus);
	
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE GroupID = :groupID AND ModStatus = :modstatus")
	public int getTotalUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("modstatus") long modstatus);
	
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE GroupID = :groupID")
	public int getTotalUserGroup(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT Max(EarnedPoints) FROM UserGroups WHERE GroupID = :groupID")
	public int getMaxPointsUserGroup(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT Max(EarnedPoints) FROM UserGroups WHERE GroupID = :groupID AND EarnedPoints != :points")
	public int getSecondMaxPointsUserGroup(@Bind("groupID") long groupId,
											@Bind("points") long points);
	
	@SqlQuery("SELECT UserID FROM SubGroup WHERE GroupID = :groupID AND EarnedPoints = :points")
	public int getUserByPointsAndGroup(@Bind("groupID") long groupId,
										@Bind("points") long points);
}
