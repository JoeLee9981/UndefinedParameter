package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.UserGroup;

@RegisterMapper(UserMapper.class)
public interface UserGroupDAO {

	@SqlQuery("SELECT * FROM UserGroups WHERE GroupID = :groupid")
	public UserGroup findUserByGroupId(@Bind("groupid") int groupid);
	
	@SqlQuery("SELECT * FROM UserGroups WHERE UserID = :userid")
	public UserGroup findUserByUserId(@Bind("userid") int userid);
	
	@SqlUpdate("INSERT INTO UserGroup "
		+ "(UserID, GroupID) "
		+ "values "
		+ "(:userid, :groupid)")
	@GetGeneratedKeys
	public void insert(@Bind("userid") int userid,
						@Bind("groupid") int groupid);

	@SqlUpdate("DELETE FROM UserGroups WHERE GroupID = :gId AND UserID = :uId")
	public void delete(@Bind("uId") int user,
						@Bind("gId") int group);
	
	@SqlUpdate("UPDATE UserGroups SET Rating = :rating WHERE GroupID = :groupID AND UserID = :userID")
	public void updateUserGroupRating(@Bind("groupID") long groupID,
									@Bind("userID") long userID,
									@Bind("rating") int rating);
	
	@SqlQuery("UPDATE SubGroup SET Rating = Rating + :rating WHERE GroupID = :groupID")
	public int updateSubGroupRating(@Bind("groupID") long groupId, @Bind("rating") int rating);
	
	@SqlQuery("SELECT Rating FROM SubGroup WHERE GroupID = :groupID")
	public int getSubGroupRating(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT RatingCount FROM SubGroup WHERE GroupID = :groupID")
	public int getSubGroupRatingCount(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT Rating FROM UserGroups WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupRating(@Bind("groupID") long orgID,
									@Bind("userID") long userID);
	
	@SqlQuery("UPDATE SubGroup SET RatingCount = RatingCount + 1 WHERE GroupID = :groupID")
	public int updateSubGroupRatingCount(@Bind("groupID") long groupId);
	
	
	
}
