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

	/**
	 * Selects everything relating to groups such as info about the group. Returns each entry in Group type in a List.
	 * @return List<Group>
	 */
	@SqlQuery("SELECT * FROM SubGroup ORDER BY MemberCount DESC")
	public List<Group> findTopGroups();
	
	/**
	 * Find groups based on provided keywords
	 * @param keywords
	 * @return
	 */
	@SqlQuery("	SELECT * FROM "
+ " 					(SELECT "
+ "						  CASE WHEN Name = :keywords									THEN 256 	ELSE 0 END "
+ "						+ CASE WHEN Name LIKE CONCAT('%', :keywords, '%') 				THEN 128 	ELSE 0 END "
+ "						+ CASE WHEN Description LIKE CONCAT('%', ':keywords', '%') 		THEN 64		ELSE 0 END "
+ "						AS KeywordRanking, "
+ "						GroupID, "
+ "						Name, "
+ "						Description, "
+ "						MemberCount, "
+ "						QuizCount, "
+ "						QuestionCount, "
+ "						DateCreated, "
+ "						GroupRating, "
+ "						GroupRatingCount, "
+ "						OrgId "
+ "					FROM SubGroup) g WHERE g.KeywordRanking > 0 ORDER BY g.KeywordRanking "
			
			)
	public List<Group> findGroupsByKeywords(@Bind("keywords") String keywords);
	
	/**
	 * Selects everything from subgroup except the groups the userID is registered with, organized by member count.
	 * @param userId
	 * @return List<Group>
	 */
	@SqlQuery("SELECT * FROM SubGroup sg WHERE NOT EXISTS "
			+ "(SELECT * FROM UserGroups ug, User user WHERE "
			+ "ug.UserID = user.UserID AND "
			+ "ug.GroupID = sg.GroupID AND "
			+ "user.UserID = :userId) "
			+ "ORDER BY MemberCount DESC")
	public List<Group> findUnregisteredTopGroups(@Bind("userId") long userId);
	
	/**
	 * Selects all from Subgroup table, but only the groups that are related to the entered org id.
	 * @param id
	 * @return List<Group>
	 */
	@SqlQuery("SELECT * FROM SubGroup WHERE OrgID = :orgId")
	public List<Group> findGroupsByOrgId(@Bind("orgId") long id);
	
	/**
	 * Selects the group that matches the group id from the SubGroup table.
	 * @param id
	 * @return Group
	 */
	@SqlQuery("SELECT * FROM SubGroup WHERE GroupID = :groupId")
	public Group findGroupById(@Bind("groupId") long id);
	
	/**
	 * Selects the group that matches the creator of the quiz, by the quiz id.
	 * @param id
	 * @return Group
	 */
	@SqlQuery("SELECT * FROM SubGroup sg, GroupQuiz quiz WHERE sg.GroupID = quiz.GroupID AND quiz.QuizId = :quizId")
	public Group findGroupByQuizId(@Bind("quizId") long id);
	
	/**
	 * Select a list of groups that the user is not part of from the SubGroup table and that is part of a
	 * particular org id, and does this by getting the user id and org id, and then removing options before returning. 
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	@SqlQuery("SELECT * FROM SubGroup sg WHERE sg.OrgID = :orgId AND NOT EXISTS "
			+ "(SELECT * FROM UserGroups ug, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId)")
	public List<Group> findUnregisteredGroupsByUser(@Bind("userId") long userId, @Bind("orgId") long orgId);
	
	/**
	 * Finds and selects groups which that a user is a part of, and also specifies the organization. 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	@SqlQuery("SELECT * FROM UserGroups ug, SubGroup sg, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId AND "
			+ "sg.OrgID = :orgId")
	public List<Group> findGroupsByUser(@Bind("userId") long userId, @Bind("orgId") long orgId);
	
	/**
	 * Finds and selects groups which that a user is a part of, and only a part of.
	 * 
	 * @param userId
	 * @return
	 */
	@SqlQuery("SELECT * FROM UserGroups ug, SubGroup sg, User user WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "ug.UserID = user.UserID AND "
			+ "user.UserID = :userId")
	public List<Group> findGroupsByUser(@Bind("userId") long userId);
	
	/**
	 * Find the amount of groups that the user is a part of and matches the group id. Mostly used for finding
	 * if the user is a part of that group already or not. 1 if yes, 0 if not part of the group.
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE UserID = :userID AND GroupID = :groupID")
	public long findUserGroupCount(@Bind("userID") long userId, @Bind("groupID") long groupId);
	
	/**
	 * Create a group with name, description, and the corresponding org id that it will belong to.
	 * @param name
	 * @param description
	 * @param orgId
	 * @return
	 */
	@SqlUpdate("INSERT INTO SubGroup (Name, Description, OrgID) VALUES (:name, :desc, :orgId)")
	@GetGeneratedKeys
	public long insertGroup(@Bind("name") String name, 
							@Bind("desc") String description, 
							@Bind("orgId") long orgId);
	
	/**
	 * Changing around the name and description of the specified group by its group id.
	 * @param groupId
	 * @param name
	 * @param description
	 */
	@SqlUpdate("UPDATE SubGroup SET Name = :name, Description = :description WHERE GroupID = :groupId")
	public void updateGroup(@Bind("groupId") long groupId, @Bind("name") String name, @Bind("description") String description);
	
	/**
	 * Adding in a new relationship between a user and the group, by the user id and the group id.
	 * @param userId
	 * @param groupId
	 */
	@SqlUpdate("INSERT INTO UserGroups (UserID, GroupID) VALUES (:userId, :groupId)")
	public void registerGroup(@Bind("userId") long userId, @Bind("groupId") long groupId);
	
	/**
	 * Adding in one person into the Groups member count, and the group is specified by the group id.
	 * @param groupId
	 */
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount + 1 WHERE GroupID = :groupId")
	public void incrementGroupMembers(@Bind("groupId") long groupId);
	
	/**
	 * Removing a relationship between a user and the group, by the user id and the group id.
	 * @param groupId
	 * @param userId
	 */
	@SqlUpdate("DELETE FROM UserGroups WHERE GroupID = :groupId AND UserID = :userId")
	public void removeUserGroup(@Bind("groupId") long groupId, @Bind("userId") long userId);
	
	/**
	 * Subtracting out one person from the Groups member count, and the group is specified by the group id.
	 * @param groupId
	 */
	@SqlUpdate("UPDATE SubGroup SET MemberCount = MemberCount - 1 WHERE GroupID = :groupId")
	public void decrementGroupMembers(@Bind("groupId") long groupId);
	
	/**
	 * Finding and returning the amount of questions from a group, determined by the group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(QuestionID) FROM Question WHERE GroupID = :groupId")
	public int countQuestions(@Bind("groupId") long groupId);
	
	/**
	 * Finding and return the amount of quizzes from a group, determined by the group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM Quiz quiz, GroupQuiz gquiz WHERE quiz.QuizID = gquiz.QuizID and gquiz.GroupID = :groupId")
	public int countQuizzes(@Bind("groupId") long groupId);

	/**
	 * Counting and returning the amount of flagged question that a user has in a group, determined by the user id and the group id
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM Question q, User u, SubGroup sg "
			+ "WHERE q.CreatorID = u.UserID "
			+ "AND q.Flagged = 1 "
			+ "AND sg.GroupID = q.GroupID "
			+ "AND u.UserID = :userId "
			+ "AND sg.GroupID = :groupId")
	public int countFlagsByUser(@Bind("userId") long userId, @Bind("groupId") long groupId);
	
	/**
	 * Counts and returns the amount of quizzes by the user in a group, determined by the user id and group id.
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(quiz.QuizID) FROM Quiz quiz, GroupQuiz gq, SubGroup sg, User u WHERE "
			+ "quiz.QuizID = gq.QuizID "
			+ "AND gq.GroupID = sg.GroupID "
			+ "AND quiz.CreatorID = u.UserID "
			+ "AND u.UserID = :userId "
			+ "AND sg.GroupId = :groupId")
	public int countUserQuizzes(@Bind("groupId") long groupId, @Bind("userId") long userId);
	
	/**
	 * Counts and returns the amount of questions by the user in a group, determined by the user id and group id.
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(qq.QuestionID) FROM Quiz quiz, GroupQuiz gq, SubGroup sg, User u, QuizQuestion qq WHERE "
			+ "quiz.QuizID = gq.QuizID "
			+ "AND gq.GroupID = sg.GroupID "
			+ "AND quiz.CreatorID = u.UserID "
			+ "AND quiz.QuizID = qq.QuizID "
			+ "AND u.UserID = :userId "
			+ "AND sg.GroupID = :groupId")
	public int countUserQuestions(@Bind("groupId") long groupId, @Bind("userId") long userId);
	
	/**
	 * Counts and returns the amount of flagged questions that a group has, determined by the group id.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT COUNT(*) FROM Question q, SubGroup sg "
			+ "WHERE q.Flagged = 1 "
			+ "AND sg.GroupID = q.GroupID "
			+ "AND sg.GroupID = :groupId")
	public int countFlagsByGroup(@Bind("groupId") long groupId);
	
	/**
	 * Finds and returns a string list of categories that are a part of the group specified.
	 * @param groupId
	 * @return
	 */
	@SqlQuery("SELECT DISTINCT(CategoryType) FROM SubGroup sg, GroupQuiz gq, Quiz q, Question que, QuizQuestion qq, QuestionCategory qc, Category c "
			+ "WHERE sg.GroupID = gq.GroupID "
			+ "AND gq.QuizID = q.QuizID "
			+ "AND q.QuizID = qq.QuizID "
			+ "AND qq.QuestionID = que.QuestionID "
			+ "AND que.QuestionID = qc.QuestionID "
			+ "AND qc.CategoryID = c.CategoryID "
			+ "AND sg.GroupID = :groupId")
	public List<String> findCategoriesByGroup(@Bind("groupId") long groupId);


}
