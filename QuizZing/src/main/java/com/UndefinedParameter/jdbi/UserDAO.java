package com.UndefinedParameter.jdbi;

import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Badge;
import com.UndefinedParameter.app.core.UserMessage;
import com.UndefinedParameter.app.core.Organization;
import com.UndefinedParameter.app.core.User;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

	@SqlQuery("SELECT * FROM User WHERE UserName = :username")
	public User findUserByUserName(@Bind("username") String username);
	
	@SqlQuery("SELECT * FROM User WHERE UserID = :userId")
	public User findUserByUserId(@Bind("userId") long userId);
	
	@SqlQuery("SELECT * FROM User WHERE Email = :email")
	public User findUserByEmail(@Bind("email") String email);
	
	@SqlUpdate("INSERT INTO User "
			+ "(Username, FirstName, LastName, MiddleName, Country, City, State, Email, Password, SQuestion, SAnswer) "
			+ "values "
			+ "(:username, :firstName, :lastName, :middleName, :country, :city, :state, :email, :password, :squestion, :sanswer)")
	@GetGeneratedKeys
	public long insert(@Bind("username") String username, 
					   @Bind("firstName") String firstName,
					   @Bind("lastName") String lastName,
					   @Bind("middleName") String middleName,
					   @Bind("country") String country,
					   @Bind("city") String city,
					   @Bind("state") String state,
					   @Bind("email") String email,
					   @Bind("password") String password,
					   @Bind("squestion") String squestion,
					   @Bind("sanswer") String sanswer);
	
	@SqlUpdate("UPDATE User SET "
			+ "UserName=:username, FirstName=:firstName, LastName=:lastName, "
			+ "MiddleName=:middleName, Country=:country, City=:city, State=:state, "
			+ "Email=:email, Password=:password, SQuestion=:squestion, SAnswer=:sanswer, "
			+ "Active=:active, ActivationCode=:activecode, LastAccessed=:lastaccessed, SeeAgain=:seeagain "
			+ "WHERE UserID = :userid")
	@GetGeneratedKeys
	public long update(@Bind("userid") long userid,
					   @Bind("username") String username, 
					   @Bind("firstName") String firstName,
					   @Bind("lastName") String lastName,
					   @Bind("middleName") String middleName,
					   @Bind("country") String country,
					   @Bind("city") String city,
					   @Bind("state") String state,
					   @Bind("email") String email,
					   @Bind("password") String password,
					   @Bind("squestion") String squestion,
					   @Bind("sanswer") String sanswer,
					   @Bind("active") int active,
					   @Bind("activecode") String code,
					   @Bind("lastaccessed") DateTime date,
					   @Bind("seeagain") int again);
	
	/**
	 * Updates a user without changing the password
	 * @param userid
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param country
	 * @param city
	 * @param state
	 * @param email
	 * @param password
	 * @param squestion
	 * @param sanswer
	 * @param active
	 * @param code
	 * @param date
	 * @param again
	 * @return
	 */
	@SqlUpdate("UPDATE User SET "
			+ "UserName=:username, FirstName=:firstName, LastName=:lastName, "
			+ "MiddleName=:middleName, Country=:country, City=:city, State=:state, "
			+ "Email=:email, SQuestion=:squestion, SAnswer=:sanswer, "
			+ "Active=:active, ActivationCode=:activecode, LastAccessed=:lastaccessed, SeeAgain=:seeagain "
			+ "WHERE UserID = :userid")
	@GetGeneratedKeys
	public long updateNoPassword(@Bind("userid") long userid,
					   @Bind("username") String username, 
					   @Bind("firstName") String firstName,
					   @Bind("lastName") String lastName,
					   @Bind("middleName") String middleName,
					   @Bind("country") String country,
					   @Bind("city") String city,
					   @Bind("state") String state,
					   @Bind("email") String email,
					   @Bind("squestion") String squestion,
					   @Bind("sanswer") String sanswer,
					   @Bind("active") int active,
					   @Bind("activecode") String code,
					   @Bind("lastaccessed") DateTime date,
					   @Bind("seeagain") int again);
	
	@SqlQuery("SELECT * FROM User u, SubGroup sg, UserGroups ug WHERE "
			+ "sg.GroupID = ug.GroupID "
			+ "AND u.UserID = ug.UserID "
			+ "AND u.UserID = :userId ORDER BY EarnedPoints DESC")
	@RegisterMapper(BadgeMapper.class)
	public List<Badge> getBadgesByUser(@Bind("userId") long userId);
	
	@SqlQuery("SELECT SUM(ug.EarnedPoints) FROM UserGroups ug, SubGroup sg WHERE "
			+ "ug.GroupID = sg.GroupID AND "
			+ "sg.OrgID = :orgId AND "
			+ "ug.UserID = :userId")
	public int getBadgesByOrganizationAndUser(@Bind("orgId") long orgId, @Bind("userId") long userId);
	
	@SqlQuery("SELECT org.* FROM Organization org, UserOrganization uo WHERE org.OrgID = uo.OrgID AND uo.UserID = :userId")
	@RegisterMapper(OrganizationMapper.class)
	public List<Organization> findUserOrgs(@Bind("userId") long userId);
	
	/**************************************** Messages Section **********************************************/
	
	@SqlQuery("SELECT msg.*, u.FirstName, u.LastName FROM Message msg, User u WHERE msg.SenderID = u.UserID AND msg.MessageID = :messageId")
	@RegisterMapper(MessageMapper.class)
	public UserMessage getMessage(@Bind("messageId") long messageId);
	
	/**
	 * Get all of a users messages
	 * @param userId the user
	 * @return All of the user's messages
	 */
	@SqlQuery("SELECT msg.*, u.FirstName, u.LastName FROM Message msg, User u WHERE msg.SenderID = u.UserID AND SendeeDeleted = 0 AND msg.SendeeID = :userId ORDER BY TimeStamp DESC")
	@RegisterMapper(MessageMapper.class)
	public List<UserMessage> getUserMessages(@Bind("userId") long userId);
	
	/**
	 * Get all of a users unread messages
	 * @param userId the user
	 * @return all unread messages
	 */
	@SqlQuery("SELECT msg.*, u.FirstName, u.LastName FROM Message msg, User u WHERE msg.SenderID = u.UserID AND msg.Viewed = 0 AND SendeeDeleted = 0 AND msg.SendeeID = :userId ORDER BY TimeStamp DESC")
	@RegisterMapper(MessageMapper.class)
	public List<UserMessage> getUnreadMessages(@Bind("userId") long userId);
	
	/**
	 * Send a message
	 * @param senderId who sent it
	 * @param sendeeId who is it for
	 * @param message the message
	 * @return the key of the message created
	 */
	@SqlUpdate("INSERT INTO Message (SenderID, SendeeID, Message) VALUES(:senderId, :sendeeId, :message)")
	@GetGeneratedKeys
	public long sendMessage(@Bind("senderId") long senderId, @Bind("sendeeId") long sendeeId, @Bind("message") String message);
	
	/**
	 * get all sent messages
	 * @param userId of the user than sent messages
	 * @return the messages
	 */
	@SqlQuery("SELECT msg.*, u.FirstName, u.LastName FROM Message msg, User u WHERE msg.SendeeID = u.UserID AND SenderDeleted = 0 AND msg.SenderID = :userId ORDER BY TimeStamp DESC")
	@RegisterMapper(MessageMapper.class)
	public List<UserMessage> getSentMessages(@Bind("userId") long userId);
	
	/**
	 * Mark a message as viewed
	 * @param userId the sendee
	 */
	@SqlUpdate("UPDATE Message SET Viewed = 1 WHERE MessageID = :messageId")
	public void markMessageAsRead(@Bind("messageId") long messageId);
	
	/**
	 * Mark the message as sender deleted (from outbox)
	 * @param messageId
	 */
	@SqlUpdate("Update Message SET SenderDeleted = 1 WHERE MessageID = :messageId")
	public void senderDeleteMessage(@Bind("messageId") long messageId);
	
	/**
	 * Mark the message as sendee deleted (from inbox)
	 * @param messageId
	 */
	@SqlUpdate("Update Message SET SendeeDeleted = 1 WHERE MessageID = :messageId")
	public void sendeeDeleteMessage(@Bind("messageId") long messageId);
}
