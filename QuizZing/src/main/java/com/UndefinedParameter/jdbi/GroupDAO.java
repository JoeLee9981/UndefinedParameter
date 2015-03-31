package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.UserGroup;

@RegisterMapper(GroupMapper.class)
public interface GroupDAO {

	@SqlQuery("SELECT * FROM SubGroup ORDER BY MemberCount DESC")
	public List<Group> findTopGroups();
	
	@SqlQuery("SELECT * FROM SubGroup sg WHERE NOT EXISTS "
			+ "(SELECT * FROM UserGroups ug, User user WHERE "
			+ "ug.UserID = user.UserID AND "
			+ "ug.GroupID = sg.GroupID AND "
			+ "user.UserID = :userId) "
			+ "ORDER BY MemberCount DESC")
	public List<Group> findUnregisteredTopGroups(@Bind("userId") long userId);
	
	@SqlQuery("SELECT * FROM SubGroup WHERE OrgID = :orgId")
	public List<Group> findGroupsByOrgId(@Bind("orgId") long id);
	
	@SqlQuery("SELECT * FROM SubGroup WHERE GroupID = :groupId")
	public Group findGroupById(@Bind("groupId") long id);
	
	@SqlQuery("SELECT * FROM SubGroup sg, GroupQuiz quiz WHERE sg.GroupID = quiz.GroupID AND quiz.QuizId = :quizId")
	public Group findGroupByQuizId(@Bind("quizId") long id);
	
	@SqlQuery("SELECT * FROM SubGroup sg WHERE sg.OrgID = :orgId AND NOT EXISTS "
			+ "(SELECT * FROM UserGroups ug, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId)")
	public List<Group> findUnregisteredGroupsByUser(@Bind("userId") long userId, @Bind("orgId") long orgId);
	
	@SqlQuery("SELECT * FROM UserGroups ug, SubGroup sg, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId AND "
			+ "sg.OrgID = :orgId")
	public List<Group> findGroupsByUser(@Bind("userId") long userId, @Bind("orgId") long orgId);
	
	@SqlQuery("SELECT * FROM UserGroups ug, SubGroup sg, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId")
	public List<Group> findGroupsByUser(@Bind("userId") long userId);
	
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
	
	@SqlQuery("SELECT COUNT(QuestionID) FROM Question WHERE GroupID = :groupId")
	public int countQuestions(@Bind("groupId") long groupId);
	
	@SqlQuery("SELECT COUNT(*) FROM Quiz quiz, GroupQuiz gquiz WHERE quiz.QuizID = gquiz.QuizID and gquiz.GroupID = :groupId")
	public int countQuizzes(@Bind("groupId") long groupId);
	
	@SqlQuery("SELECT * FROM UserGroups WHERE GroupID = :groupid")
	public UserGroup findUserByGroupId(@Bind("groupid") int groupid);
	
	@SqlQuery("SELECT * FROM UserGroups WHERE UserID = :userid")
	public UserGroup findUserByUserId(@Bind("userid") int userid);
	
	@SqlUpdate("INSERT INTO UserGroup "
		+ "(UserID, GroupID) "
		+ "values "
		+ "(:userid, :groupid)")
	@GetGeneratedKeys
	public void insert(@Bind("userid") long userid,
						@Bind("groupid") long rvalue);

	@SqlUpdate("DELETE FROM UserGroups WHERE GroupID = :gId AND UserID = :uId")
	public void delete(@Bind("uId") long userID,
						@Bind("gId") long groupID);
	
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
	
	@SqlQuery("UPDATE SubGroup SET EarnedPoints = EarnedPoints + points WHERE GroupID = :groupID AND UserID = :userID")
	public int addInUserGroupEarnedPoints(@Bind("groupID") long groupId,
											@Bind("userID") long userId,
											@Bind("points") long points);
	
	@SqlQuery("SELECT EarnedPoints FROM SubGroup WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupEarnedPoints(@Bind("groupID") long groupId,
											@Bind("userID") long userId);
	
	@SqlQuery("UPDATE SubGroup SET ModStatus = modstatus WHERE GroupID = :groupID AND UserID = :userID")
	public int updateUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("userID") long userId,
											@Bind("modstatus") int modstat);
	
	@SqlQuery("SELECT ModStatus FROM SubGroup WHERE GroupID = :groupID AND UserID = :userID")
	public int getUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("userID") long userId);
	
	@SqlQuery("SELECT COUNT(*) FROM SubGroup WHERE GroupID = :groupID AND ModStatus = :modstatus")
	public int getTotalUserGroupModStatus(@Bind("groupID") long groupId,
											@Bind("modstatus") long modstatus);
	
	@SqlQuery("SELECT COUNT(*) FROM SubGroup WHERE GroupID = :groupID")
	public int getTotalUserGroup(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT Max(EarnedPoints) FROM SubGroup WHERE GroupID = :groupID")
	public int getMaxPointsUserGroup(@Bind("groupID") long groupId);
	
	@SqlQuery("SELECT Max(EarnedPoints) FROM SubGroup WHERE GroupID = :groupID AND EarnedPoints != :points")
	public int getSecondMaxPointsUserGroup(@Bind("groupID") long groupId,
											@Bind("points") long points);
	
	@SqlQuery("SELECT UserID FROM SubGroup WHERE GroupID = :groupID AND EarnedPoints = :points")
	public int getUserByPointsAndGroup(@Bind("groupID") long groupId,
										@Bind("points") long points);
	
	
}
