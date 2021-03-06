package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.unstable.BindIn;

import com.UndefinedParameter.app.core.Organization;
//import com.UndefinedParameter.app.core.OrganizationType;
import com.UndefinedParameter.app.core.Quiz;


@RegisterMapper(OrganizationMapper.class)
public interface OrganizationDAO {
	
	@SqlQuery("SELECT *"
+ 			  " FROM"
+				" (SELECT"
+					"   CASE WHEN Name = :keywords										THEN 256 	ELSE 0 END"
+					" + CASE WHEN Name LIKE CONCAT('%', :keywords, '%') 				THEN 128 	ELSE 0 END"
+					" + CASE WHEN Description LIKE CONCAT('%', :keywords, '%') 			THEN 64		ELSE 0 END"
+					" + CASE WHEN City LIKE CONCAT('%', :keywords, '%') 				THEN 32		ELSE 0 END"
+					" + CASE WHEN State LIKE CONCAT('%', :keywords, '%') 				THEN 16		ELSE 0 END"
+					" + CASE WHEN Country LIKE CONCAT('%', :keywords, '%') 				THEN 8		ELSE 0 END"
+					" + CASE WHEN OrganizationType LIKE CONCAT('%', :keywords, '%') 	THEN 4		ELSE 0 END"
+					" AS KeywordRanking,"
+					" OrgID,"
+					" Name,"
+					" Description,"
+					" City,"
+					" State,"
+					" Country,"
+					" OrganizationType,"
+					" MemberCount,"
+					" QuizCount,"
+					" QuestionCount,"
+					" DateCreated,"
+					" Rating,"
+					" RatingCount"
+				" FROM innodb.Organization) orgs"
+			" WHERE orgs.KeywordRanking > 0"
+			" ORDER BY orgs.KeywordRanking DESC")
	public List<Organization> findOrganizationByKeywords(@Bind("keywords") String keywords, @BindIn("keywordList") List<String> keywordList);
	
	@SqlQuery("SELECT Rating FROM Organization WHERE OrgID = :orgID")
	public int getOrganizationRating(@Bind("orgID") long orgId);
	
	@SqlQuery("SELECT RatingCount FROM Organization WHERE OrgID = :orgID")
	public int getOrganizationRatingCount(@Bind("orgID") long orgId);
	
	@SqlQuery("SELECT Rating FROM UserOrganization WHERE OrgID = :orgID AND UserID = :userID")
	public int getUserOrganizationRating(@Bind("orgID") long orgID,
										 @Bind("userID") long userID);
	
	@SqlUpdate("UPDATE UserOrganization SET Rating = :rating WHERE OrgID = :orgID AND UserID = :userID")
	public void updateUserOrgRating(@Bind("orgID") long orgID,
									@Bind("userID") long userID,
									@Bind("rating") int rating);
	
	@SqlQuery("UPDATE Organization SET Rating = Rating + :rating WHERE OrgID = :orgID")
	public int updateOrganizationRating(@Bind("orgID") long orgId, @Bind("rating") int rating);
	
	@SqlQuery("UPDATE Organization SET RatingCount = RatingCount + 1 WHERE OrgID = :orgID")
	public int updateOrganizationRatingCount(@Bind("orgID") long orgId);
	
	@SqlQuery("SELECT * FROM OrganizationType")
	public List<String> findAllOrganizationTypes();
	
	@SqlQuery("SELECT * FROM Organization")
	public List<Organization> findOrganizations();
	
	@SqlQuery("SELECT * FROM Organization "
			+ "ORDER BY DateCreated DESC "
			+ "LIMIT :startCount, :endCount")
	public List<Organization> findNewestOrganizations(@Bind("startCount") int startCount,
													  @Bind("endCount") int endCount);
	
	@SqlQuery("SELECT * FROM Organization "
			+ "ORDER BY MemberCount DESC "
			+ "LIMIT :startCount, :endCount")
	public List<Organization> findLargestOrganizations(@Bind("startCount") int startCount,
													   @Bind("endCount") int endCount);
	
	@SqlQuery("SELECT COUNT(*) FROM UserOrganization WHERE UserID = :userID AND OrgID = :orgID")
	public long findUserOrganizationCount(@Bind("userID") long userid,
										@Bind("orgID") long orgid);
	
	@SqlQuery("SELECT * FROM Organization org WHERE NOT EXISTS "
			+ "(SELECT * FROM User user, UserOrganization userOrg WHERE "
			+ "userOrg.UserID = user.UserID AND "
			+ "org.OrgID = userOrg.OrgID AND "
			+ "user.UserID = :userId)")
	public List<Organization> findUnregisteredOrganizations(@Bind("userId") long userId);
	
	@SqlQuery("SELECT * FROM Organization org, User user, UserOrganization userOrg "
			+ "WHERE userOrg.UserID = user.UserID "
			+ "AND org.OrgID = userOrg.OrgID "
			+ "AND user.UserID = :userId")
	public List<Organization> findOrganizationsByUserId(@Bind("userId") long userId);
	
	@SqlQuery("SELECT * FROM Organization WHERE OrgID = :orgId")
	public Organization findOrganization(@Bind("orgId") long id);
	
	@SqlUpdate("INSERT INTO Organization (OrganizationType, Name, Description, City, State, Country) VALUES (:type, :name, :desc, :city, :state, :country)")
	@GetGeneratedKeys
	public long insertOrganization(@Bind("type") String type,
								   @Bind("name") String name,
								   @Bind("desc") String description,
								   @Bind("city") String city,
								   @Bind("state") String state,
								   @Bind("country") String country);
	
	
	@SqlUpdate("UPDATE Organization SET OrganizationType = :type, Name = :name, Description = :description, City = :city, State = :state, Country = :country WHERE OrgID = :orgId")
	public void updateOrganization(@Bind("orgId") long orgId,
								   @Bind("type") String type,
								   @Bind("name") String name,
								   @Bind("description") String description,
								   @Bind("city") String city,
								   @Bind("state") String state,
								   @Bind("country") String country);
	
	/*
	 * TODO: Below are 2 statements to register a user and increment member count.
	 * 	If possible do this at same time
	 */
	@SqlUpdate("INSERT INTO UserOrganization (OrgID, UserID) values (:orgId, :userId)")
	public void registerOrganization(@Bind("orgId") long orgId, @Bind("userId") long userId);
	
	@SqlUpdate("UPDATE Organization SET MemberCount = MemberCount + 1 WHERE OrgID = :orgId")
	public void incrementOrgMembers(@Bind("orgId") long orgId);
	
	@SqlUpdate("DELETE FROM UserOrganization WHERE OrgID = :orgId AND UserID = :userId")
	public void removeUserOrganization(@Bind("orgId") long orgId, @Bind("userId") long userId);
	
	@SqlUpdate("UPDATE Organization SET MemberCount = MemberCount - 1 WHERE OrgID = :orgId")
	public void decrementOrgMembers(@Bind("orgId") long orgId);
	
	@SqlQuery("SELECT COUNT(QuestionID) FROM Question question, SubGroup sub WHERE question.GroupID = sub.GroupID AND sub.OrgID = :orgId")
	public int countQuestions(@Bind("orgId") long orgId);
	
	@SqlQuery("SELECT COUNT(*) FROM Quiz quiz, GroupQuiz gquiz, SubGroup g WHERE quiz.QuizID = gquiz.QuizID and gquiz.GroupID = g.GroupID and g.OrgID = :orgId")
	public int countQuizzes(@Bind("orgId") long orgId);
	
	@SqlQuery("SELECT COUNT(quiz.QuizID) FROM Quiz quiz, GroupQuiz gq, SubGroup sg, User u WHERE "
			+ "quiz.QuizID = gq.QuizID "
			+ "AND gq.GroupID = sg.GroupID "
			+ "AND quiz.CreatorID = u.UserID "
			+ "AND u.UserID = :userId "
			+ "AND sg.OrgID = :orgId")
	public int countUserQuizzes(@Bind("orgId") long orgId, @Bind("userId") long userId);
	
	@SqlQuery("SELECT COUNT(qq.QuestionID) FROM Quiz quiz, GroupQuiz gq, SubGroup sg, User u, QuizQuestion qq WHERE "
			+ "quiz.QuizID = gq.QuizID "
			+ "AND gq.GroupID = sg.GroupID "
			+ "AND quiz.CreatorID = u.UserID "
			+ "AND quiz.QuizID = qq.QuizID "
			+ "AND u.UserID = :userId "
			+ "AND sg.OrgID = :orgId")
	public int countUserQuestions(@Bind("orgId") long orgId, @Bind("userId") long userId);
	
	@SqlQuery("SELECT COUNT(*) FROM UserOrganization WHERE OrgID = :orgId")
	public int countMembers(@Bind("orgId") long orgId);
	
	@SqlUpdate("Update Organization SET MemberCount = :count WHERE OrgID = :orgId")
	public void updateMemberCount(@Bind("orgId") long orgId, @Bind("count") int count);
	
	@SqlQuery("SELECT quiz.*, sub.Name AS GroupName, sub.GroupID, u.FirstName, u.LastName "
			+ "FROM Quiz quiz, SubGroup sub, GroupQuiz gquiz, User u "
			+ "WHERE quiz.QuizID = gquiz.QuizID "
			+ "AND sub.GroupID = gquiz.GroupID "
			+ "AND u.UserID = quiz.CreatorID "
			+ "AND sub.OrgID = :orgId")
	@RegisterMapper(QuizMapper.class)
	public List<Quiz> retrieveQuizzesByOrg(@Bind("orgId") long orgId);
}
