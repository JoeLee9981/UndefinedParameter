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
	
	@SqlQuery("SELECT COUNT(*) FROM UserGroups WHERE UserID = :userID AND GroupID = :groupID")
	public long findUserGroupCount(@Bind("userID") long userId, @Bind("groupID") long groupId);
	
	@SqlUpdate("INSERT INTO SubGroup (Name, Description, OrgID) VALUES (:name, :desc, :orgId)")
	@GetGeneratedKeys
	public long insertGroup(@Bind("name") String name, 
							@Bind("desc") String description, 
							@Bind("orgId") long orgId);
	
	@SqlUpdate("UPDATE SubGroup SET Name = :name, Description = :description WHERE GroupID = :groupId")
	public void updateGroup(@Bind("groupId") long groupId, @Bind("name") String name, @Bind("description") String description);
	
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

	@SqlQuery("SELECT COUNT(*) FROM Question q, User u, SubGroup sg "
			+ "WHERE q.CreatorID = u.UserID "
			+ "AND q.Flagged = 1 "
			+ "AND sg.GroupID = q.GroupID "
			+ "AND u.UserID = :userId "
			+ "AND sg.GroupID = :groupId")
	public int countFlagsByUser(@Bind("userId") long userId, @Bind("groupId") long groupId);
	
	@SqlQuery("SELECT COUNT(*) FROM Question q, SubGroup sg "
			+ "WHERE q.Flagged = 1 "
			+ "AND sg.GroupID = q.GroupID "
			+ "AND sg.GroupID = :groupId")
	public int countFlagsByGroup(@Bind("groupId") long groupId);
	
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
