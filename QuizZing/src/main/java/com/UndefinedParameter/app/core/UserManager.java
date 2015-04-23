package com.UndefinedParameter.app.core;

import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import com.UndefinedParameter.jdbi.UserDAO;
import com.sun.mail.smtp.SMTPTransport;

public class UserManager {

	
	private UserDAO userDAO;
	
	public UserManager(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User findUserById(long userId) {
		if(userDAO == null)
			return null;
		
		return userDAO.findUserByUserId(userId);
	}
	
	public User findUserByUserName(String userName) {
		if(userDAO == null)
			return null;
		
		return userDAO.findUserByUserName(userName);
	}
	
	public User recoverUser(String email) throws Exception {
		if(userDAO == null)
			return null;
		
		User recoverUser = userDAO.findUserByEmail(email);
		if(recoverUser == null)
			return null;
		
		try{
			// Generate recovery code.
			RecoveryCodeGenerator rcg = new RecoveryCodeGenerator(6);
			String recoveryCode = rcg.nextCode();
			
			// Email recovery code.
			String emailSubject = "Recover Your QuizZing Account";
			String emailBody = "Hello, " + recoverUser.getFirstName() + "!\n\n";
			emailBody += "You are receiving this email to recover your account because of a forgotten password.\n";
			emailBody += "Please enter the code below into the QuizZing account recovery page:\n\n";
			emailBody += recoveryCode;
			emailBody += "\n\nPlease do not reply to this email,\n\nSincerely yours,\n\nThe QuizZing Development Team";
			
			GoogleMail gm = new GoogleMail();
			gm.Send("quizzingteam", "undefinedparameter", email, emailSubject, emailBody);
			
			// Update user with recovery code.
			recoverUser.setActiveCode(recoveryCode);
			if(!updateUser(recoverUser))
				return null;
		}
		catch(Exception e) {
			// TODO: Print to log?
			return null;
		}
		
		return recoverUser;
	}
	
	public boolean registerNewUser(User user) throws Exception {
		try {
			
			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			
			userDAO.insert(InputUtils.sanitizeInput(user.getUserName()), 
						   InputUtils.sanitizeInput(user.getFirstName()), 
						   InputUtils.sanitizeInput(user.getLastName()), 
						   InputUtils.sanitizeInput(user.getMiddleName()),
						   InputUtils.sanitizeInput(user.getCountry()), 
						   InputUtils.sanitizeInput(user.getCity()), 
						   InputUtils.sanitizeInput(user.getState()), 
						   InputUtils.sanitizeInput(user.getEmail()), 
						   InputUtils.sanitizeInput(hashed), 
						   InputUtils.sanitizeInput(user.getSecretQuestion()), 
						   InputUtils.sanitizeInput(user.getSecretAnswer()));
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public List<Badge> getBadgesByUser(long userId) {
		
		return userDAO.getBadgesByUser(userId);
	}
	
	public List<Badge> getBadgesByOrg(long userId) {
		
		ArrayList<Badge> badges = new ArrayList<Badge>();
		List<Organization> orgs = userDAO.findUserOrgs(userId);
		
		for(Organization org: orgs) {
			Badge badge = new Badge();
			badge.setUserId(userId);
			badge.setOrganizationId(org.getId());
			badge.setOrganizationName(org.getName());
			badge.setContribution(userDAO.getBadgesByOrganizationAndUser(org.getId(), userId));
			badges.add(badge);
		}
		return badges;
	}
	
	public boolean updateUser(User user) throws Exception {
		user = fillNullColumns(user);
		
		try {
			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			userDAO.update(user.getId(),
							InputUtils.sanitizeInput(user.getUserName()), 
							InputUtils.sanitizeInput(user.getFirstName()), 
							InputUtils.sanitizeInput(user.getLastName()), 
							InputUtils.sanitizeInput(user.getMiddleName()),
							InputUtils.sanitizeInput( user.getCountry()), 
							InputUtils.sanitizeInput(user.getCity()), 
							InputUtils.sanitizeInput(user.getState()), 
							InputUtils.sanitizeInput( user.getEmail()), 
							InputUtils.sanitizeInput(hashed), 
							InputUtils.sanitizeInput(user.getSecretQuestion()), 
							InputUtils.sanitizeInput(user.getSecretAnswer()),
						    user.getActive(),
						    InputUtils.sanitizeInput(user.getActiveCode()),
						    user.getLastAccessed(),
						    user.getSeeAgain());
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Send a message to a user
	 * @param sendeeId the person sent to
	 * @param senderId the person sending
	 * @param message the message
	 * @return generated key used for validation
	 */
	public long sendMessage(long sendeeId, long senderId, String message) {
		return userDAO.sendMessage(senderId, sendeeId, message);
	}
	
	public List<UserMessage> getSentMessages(long userId) {
		return userDAO.getSentMessages(userId);
	}
	
	public List<UserMessage> getReceivedMessages(long userId) {
		return userDAO.getUserMessages(userId);
	}
	
	/*
	 * Fill empty User parameters before updating its row in the database.
	 */
	private User fillNullColumns(User user)
	{
		User currentUser = findUserById(user.getId());
		User userQuery = user;
		
		// Set unchangeable parameters.
		userQuery.setUserName(currentUser.getUserName());
		userQuery.setSecretQuestion(currentUser.getSecretQuestion());
		userQuery.setSecretAnswer(currentUser.getSecretAnswer());
		userQuery.setLastAccessed(DateTime.now());
		userQuery.setActive(currentUser.getActive());
		userQuery.setSeeagain(currentUser.getSeeAgain());
		
		// Fill in any null parameters with old parameters.
		if(user.getLastName() == null || user.getLastName() == "")
		{
			userQuery.setLastName(currentUser.getLastName());
		}
		if(user.getFirstName() == null || user.getFirstName() == "")
		{
			userQuery.setFirstName(currentUser.getFirstName());
		}
		if(user.getMiddleName() == null || user.getMiddleName() == "")
		{
			userQuery.setMiddleName(currentUser.getMiddleName());
		}
		if(user.getCountry() == null || user.getCountry() == "")
		{
			userQuery.setCountry(currentUser.getCountry());
		}
		if(user.getCity() == null || user.getCity() == "")
		{
			userQuery.setCity(currentUser.getCity());
		}
		if(user.getState() == null || user.getState() == "")
		{
			userQuery.setState(currentUser.getState());
		}
		if(user.getEmail() == null || user.getEmail() == "")
		{
			userQuery.setEmail(currentUser.getEmail());
		}
		if(user.getPassword() == null || user.getPassword() == "")
		{
			userQuery.setPassword(currentUser.getPassword());
		}
		if(user.getActiveCode() == null || user.getActiveCode() == "")
		{
			userQuery.setActiveCode(currentUser.getActiveCode());
		}
		
		return userQuery;
	}
	
	// http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
	private class RecoveryCodeGenerator {
		private char[] rCode;	
		private char[] buf;
		private Random rnd = new Random();
		
		public RecoveryCodeGenerator(int length) {
			rCode = buildAlphaNumericDictionary();
			buf = new char[length];
		}
		
		private char[] buildAlphaNumericDictionary() {
			StringBuilder strbldr = new StringBuilder();
			
			for (char c = '0'; c <= '9'; ++c)
				strbldr.append(c);
			for (char c = 'a'; c <= 'z'; ++c)
				strbldr.append(c);
			
			return strbldr.toString().toCharArray();
		}
		
		public String nextCode() {
			for (int i = 0; i < buf.length; ++i) {
				buf[i] = rCode[rnd.nextInt(rCode.length)];
			}
			
			return new String(buf);
		}
	}
	
	// http://stackoverflow.com/questions/3649014/send-email-using-java
	private class GoogleMail {
		
		private GoogleMail() { }
		
		public void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
	        
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	        
	        Properties properties = System.getProperties();
	        properties.setProperty("mail.smtps.host", "smtp.gmail.com");
	        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	        properties.setProperty("mail.smtp.port", "465");
	        properties.setProperty("mail.smtp.socketFactory.port", "465");
	        properties.setProperty("mail.smtps.auth", "true");
	        
	        properties.put("mail.smtps.quitwait", "false");
	        
	        Session session = Session.getInstance(properties, null);
	        
	        final MimeMessage mmsg = new MimeMessage(session);
	        
	        mmsg.setFrom(new InternetAddress(username + "@gmail.com"));
	        mmsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
	        mmsg.setSubject(title);
	        mmsg.setText(message, "utf-8");
	        mmsg.setSentDate(new Date());
	        
	        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

	        t.connect("smtp.gmail.com", username, password);
	        t.sendMessage(mmsg, mmsg.getAllRecipients());      
	        t.close();
		}
	}
}
