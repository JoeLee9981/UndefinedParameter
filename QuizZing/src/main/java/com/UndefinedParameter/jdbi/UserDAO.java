package com.UndefinedParameter.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.User;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

	@SqlQuery("SELECT * FROM User WHERE UserName = :username")
	public User findUserByUserName(@Bind("username") String username);
	
	@SqlQuery("SELECT * FROM User WHERE UserID = :userId")
	public User findUserByUserId(@Bind("userId") int userId);
	
	@SqlUpdate("INSERT INTO User (Username, FirstName, LastName, MiddleName, Country, City, "
			+ "State, Email, Password, SQuestion, SAnswer) values (:username, :firstName, "
			+ ":lastName, :middleName, :country, :city, :state, :email, :password, :squestion, :sanswer)")
	public void insert(@Bind("username") String username, 
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
	
	
}
