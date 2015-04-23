package com.UndefinedParameter.jdbi;

import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Badge;
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
}
