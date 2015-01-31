package com.UndefinedParameter.quizzing;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.UndefinedParameter.app.core.User;
import com.UndefinedParameter.app.core.UserManager;
import com.UndefinedParameter.jdbi.UserDAO;
import com.google.common.base.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
	
	private UserManager userManager;
	
	public BasicAuthenticator(UserDAO userDAO) {
		this.userManager = new UserManager(userDAO);
	}
	
	public Optional<User> authenticate(BasicCredentials credentials)
			throws AuthenticationException {
		
		User userToAuth = userManager.findUserByUserName(credentials.getUsername());
		
		if(userToAuth != null && userToAuth.getPassword() != null && userToAuth.getPassword().equals(credentials.getPassword())) {
			//TODO: Make it time out after 20 minutes.
			//TODO: If the authentication request isn't requested within 20 minutes of the last successful authentication, then time out.
			//TODO: Compare time in database to current time.
			//TODO: Then update the last accessed time.
			
			return Optional.of(userToAuth);
		}
		return Optional.absent();
	}
}
