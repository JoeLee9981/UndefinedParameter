package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.UserMessage;

public class MessageMapper implements ResultSetMapper<UserMessage> {

	public UserMessage map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		UserMessage message = new UserMessage();
		
		message.setMessageId(r.getLong("MessageID"));
		message.setMessage(r.getString("Message"));
		message.setSendeeId(r.getLong("SendeeID"));
		message.setUserName(r.getString("FirstName") + " " + r.getString("LastName"));
		message.setSenderId(r.getLong("SenderID"));
		message.setViewed(r.getBoolean("Viewed"));
		message.setTimestamp(new DateTime(r.getDate("TimeStamp")));
		
		return message;
	}
	
}