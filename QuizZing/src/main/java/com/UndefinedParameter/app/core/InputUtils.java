package com.UndefinedParameter.app.core;

import org.apache.commons.lang.StringEscapeUtils;

public class InputUtils {
	
	public static String sanitizeInput(String input) {
		if(input == null) {
			return input;
		}
		//replace all newlines
		input = input.replace("\n", "<br/>");
		//escape and re-add <br/>
		return StringEscapeUtils.escapeHtml(input).replace("&lt;br/&gt;", "<br/>");
	}
}
