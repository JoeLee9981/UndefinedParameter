package com.UndefinedParameter.app.core;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class InputUtils {
	
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
	
	public static byte[] getSalt() {
		Random random = new Random();
		
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		return salt;
	}
	
	public static String normalizeInput(String input) {
		return input.toLowerCase();
	}
}
