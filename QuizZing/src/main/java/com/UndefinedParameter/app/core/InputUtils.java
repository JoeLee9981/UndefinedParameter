package com.UndefinedParameter.app.core;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * Input Utils is a class with static methods used to handle
 * 		escaping and unescaping text.
 * 
 * This helps to prevent xss, xsrf attacks, as well as helps to prevent
 * input used in the javascript from causing the code to fail.
 *
 */
public class InputUtils {
	
	/**
	 * Generic method to sanitize input from a user
	 * @param input the input to sanitize
	 * @return the sanitized result from the input
	 */
	public static String sanitizeInput(String input) {
		if(StringUtils.isBlank(input)) {
			return input;
		}
		input = StringEscapeUtils.unescapeHtml(input);
		//replace all newlines
		input = input.replace("\n", "<br/>");
		input = input.replace("'", "&#39;");
		input = input.replace("<", "&lt;");
		input = input.replace(">", "&gt;");

		//escape and re-add <br/>
		return StringEscapeUtils.escapeHtml(input).replace("&amp;lt;br/&amp;gt;", "<br/>");
	}
	
	/**
	 * 
	 * THIS METHOD IS DEPRECATED - USE BCrypt TO SALT AND HASH PASSWORDS
	 * 
	 * Crypto method used to salt and hash a users password
	 * @param salt the salt to use
	 * @param password the password to hash
	 * @return a byte[] of hashed results
	 */
	public static byte[] hashPassword(byte[] salt, String password) {
		
		try {
		
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = f.generateSecret(spec).getEncoded();
			
			return hash;
		}
		catch(NoSuchAlgorithmException nse) {
			return null;
		}
		catch(InvalidKeySpecException ike) {
			return null;
		}
	}
	
	/**
	 * 
	 * THIS METHOD IS DEPRECATED, USE BCRYPT TO SALT AND HASH
	 * 
	 * Method used to generate a salt
	 * @return randomized salt
	 */
	public static byte[] getSalt() {
		Random random = new Random();
		
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		return salt;
	}
	
	/**
	 * Used to normalize an input string by converting it all to lower case characters
	 * 		Primary use is normalizing things such as category tags
	 * @param input string to normalize
	 * @return normalized input
	 */
	public static String normalizeInput(String input) {
		return input.toLowerCase();
	}
}
