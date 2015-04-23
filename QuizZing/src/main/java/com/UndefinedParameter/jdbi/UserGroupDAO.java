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

	/**
	 * Selects all users that are a part of the group, determined by the group id.
	 * @param groupid
	 * @return
	 */
	@SqlQuery("SELECT * FROM UserGroups WHERE GroupID = :groupid")
	public UserGroup findUserByGroupId(@Bind("groupid") int groupid);
	
	/**
	 * Selects all that a user is a part of in the user group table. Useful for finding out which groups
	 * the user belongs to. Determined by the user id.
	 * @param userid
	 * @return
	 */
	@SqlQuery("SELECT * FROM UserGroups WHERE UserID = :userid")
	public UserGroup findUserByUserId(@Bind("userid") int userid);
	
	/**
	 * Selects the amount of user group relationships that matches the user id AND the group id, 
	 * determined by the user id and group id. Really only useful for finding out if the user is 
	 * a part of the group or not. 1 if they are a part of the group, 0 if not part of the group.
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE UserID = :userID AND GroupID = :groupID")
	public long findUserGroupCount(@Bind("userID") long userId, @Bind("groupID") long groupId);
	
	/**
	 * Subtracts one from the member count of the specified group id.
	 * @param groupId
	 */
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount - 1 WHERE GroupID = :groupId")
	public void decrementGroupMembers(@Bind("groupId") long groupId);
	
	/**
	 * Add one to the member count of the specified group id.
	 * @param groupId
	 */
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount + 1 WHERE GroupID = :groupId")
	public void incrementGroupMembers(@Bind("groupId") long groupId);
	
	/**
	 * Creates a relationship between user id and group id.
	 * @param userid
	 * @param groupid
	 * @return
	 */
	@SqlUpdate("INSERT INTO UserGroups "
		+ "(UserID, GroupID) "
		+ "VALUES "
		+ "(:userid, :groupid)")
	@GetGeneratedKeys
	public long insert(@Bind("userid") long userid,
						@Bind("groupid") long groupid);

	/**
	 *  Deletes a relationship between a user id and group id.
	 * @param userID
	 * @param groupID
	 */
	@SqlUpdate("DELETE FROM UserGroups WHERE GroupID = :gId AND UserID = :uId")
	public void delete(@Bind("uId") long userID,
						@Bind("gId") long groupID);
	
	/**
	 * Updates the matching group id and user id with the specified rating.
	 * @param groupID
	 * @param userID
	 * @param rating
	 */
	@SqlUpdate("UPDATE UserGroups SET Rating = :rating WHERE GroupID = :groupID AND UserID = :userID")
	public void updateUserGroupRating(@Bind("groupID") long groupID,
									@Bind("userID") long userID,
									@Bind("rating") int rating);
	
	/**
	 * Adds the value of the rating to the group with the specified group id.
	 * @param groupId
	 * @param rating
	 * @return
	 */
	@SqlUpdate("UPDATE SubGroup SET Rating = Rating + :rating WHERE GroupID = :groupID")
	public int updateSubGroupRating(@Bind("groupID") long groupId, @Bind("rating") int rating);
	
	/**
	 * Finds the rating sum of the specified to the group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT Rating FROM SubGroup WHERE GroupID = :groupID")
	public int getSubGroupRating(@Bind("groupID") long groupId);
	
	/**
	 * Finds the amount of people that have rated the group, specified by the group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT RatingCount FROM SubGroup WHERE GroupID = :groupID")
	public int getSubGroupRatingCount(@Bind("groupID") long groupId);
	
	/**
	 * Finds the rating the user has given a group, determined by the group id and user id.
	 * @param orgID
	 * @param userID
	 * @return
	 */
	@SqlQuery("SELECT Rating FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupRating(@Bind("groupID") long orgID,
									@Bind("userID") long userID);
	
	/**
	 * Increases the amount of people that have rated the group by one.
	 * @param groupId
	 * @return
	 */
	@SqlUpdate("UPDATE SubGroup SET RatingCount = RatingCount + 1 WHERE GroupID = :groupID")
	public int updateSubGroupRatingCount(@Bind("groupID") long groupId);
	
	/**
	 * Updates the user's amount of earned points by the specified amount, group id, and user id.
	 * @param groupId
	 * @param userId
	 * @param points
	 * @return
	 */
	@SqlUpdate("UPDATE UserGroups SET EarnedPoints = EarnedPoints + :points WHERE GroupID = :groupID AND UserID = :userID")
	public int addInUserGroupEarnedPoints(@Bind("groupID") long groupId,
											@Bind("userID") long userId,
											@Bind("points") long points);
	/**
	 * Finds out how many points a user has earned, determined by the group id and user id.
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@SqlQuery("SELECT EarnedPoints FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupEarnedPoints(@Bind("groupID") long groupId,
											@Bind("userID") long userId);
	
	/**
	 * Changes the mod status of a user in a group, with 1 meaning that they are a mod and 0 meaning they are not.
	 * @param groupId
	 * @param userId
	 * @param modstat
	 * @return
	 */
	@SqlUpdate("UPDATE UserGroups SET ModStatus = :modstatus WHERE GroupID = :groupID AND UserID = :userID")
	public int updateUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("userID") long userId,
											@Bind("modstatus") int modstat);
	
	/**
	 * Finds out the mod status of a user in a group, with 1 meaning that they are a mod and 0 that they are not.
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@SqlQuery("SELECT ModStatus FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupModStatus(@Bind("groupID") long groupId, @Bind("userID") long userId);
	
	/**
	 * Finds users ids that are a part of the group and have such modstatus. 1 meaning that it will return those who
	 * are mods, and a 0 meaning that it will return those who aren't mods.
	 * @param groupId
	 * @param modstatus
	 * @return
	 */
	@SqlQuery("SELECT UserID FROM UserGroups WHERE GroupID = :groupID AND ModStatus = :modstatus")
	public List<Long> getUsersBasedOnModStatus(@Bind("groupID") long groupId,
											@Bind("modstatus") long modstatus);
	
	/**
	 * Finds a list of group ids where the user has X mod status. 1 for does have mod status in the groups,
	 * 0 for the groups that they don't have mod status.
	 * @param userId
	 * @param modstatus
	 * @return
	 */
	@SqlQuery("SELECT GroupID FROM UserGroups WHERE UserID = :userID AND ModStatus = :modstatus")
	public List<Long> getGroupsBasedOnModStatus(@Bind("userID") long userId,
											@Bind("modstatus") long modstatus);
	
	/**
	 * Returns the amount of individuals that are a part of a group and have X mod status. 1 for those who are moderators,
	 * 0 for those who don't have mod status in the group.
	 * @param groupId
	 * @param modstatus
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE GroupID = :groupID AND ModStatus = :modstatus")
	public int getTotalUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("modstatus") long modstatus);
	
	/**
	 * Finds out the amount of people that are in the group, determined by the group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE GroupID = :groupID")
	public int getTotalUserGroup(@Bind("groupID") long groupId);
	
	/**
	 * Find the highest amount of earned points in a group. Useful if delete the only mod and needing to find the
	 * next highest.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT Max(EarnedPoints) FROM UserGroups WHERE GroupID = :groupID")
	public int getMaxPointsUserGroup(@Bind("groupID") long groupId);
	
	/**
	 * Find the second highest amount of earned points in the group. Useful for when deleting the only moderator in a group
	 * needing to find the next person to promote.
	 * @param groupId
	 * @param points
	 * @return
	 */
	@SqlQuery("SELECT Max(EarnedPoints) FROM UserGroups WHERE GroupID = :groupID AND EarnedPoints != :points")
	public int getSecondMaxPointsUserGroup(@Bind("groupID") long groupId,
											@Bind("points") long points);
	
	/**
	 * Finding the user in the group that has earned X points.
	 * @param groupId
	 * @param points
	 * @return
	 */
	@SqlQuery("SELECT UserID FROM SubGroup WHERE GroupID = :groupID AND EarnedPoints = :points")
	public int getUserByPointsAndGroup(@Bind("groupID") long groupId,
										@Bind("points") long points);
}
