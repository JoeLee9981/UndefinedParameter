package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.User;

public class UserMapper implements ResultSetMapper<User> {
	
	public User map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		
		User user = new User();
		
		user.setId(r.getInt("UserID"));
		user.setUserName(r.getString("Username"));
		user.setFirstName(r.getString("FirstName"));
		user.setMiddleName(r.getString("MiddleName"));
		user.setLastName(r.getString("LastName"));
		user.setCountry(r.getString("Country"));
		user.setCity(r.getString("City"));
		user.setState(r.getString("State"));
		user.setEmail(r.getString("Email"));
		user.setPassword(r.getString("Password"));
		user.setSecretQuestion(r.getString("SQuestion"));
		user.setSecretAnswer(r.getString("SAnswer"));
		user.setAdmin(r.getBoolean("Admin"));
		
		return user;
	}

}
