package com.UndefinedParameter.app.core;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a message from one user to another
 *
 */
public class UserMessage {

	/*** Member Variables ***/
	
	private long messageId;
	
	//this is the sender when getting received messages
	//but is the sendee when getting sent messages
	private String userName;
	//who sent it
	private long senderId;
	//who was it sent to
	private long sendeeId;
	//message
	private String message;
	//has it been viewed
	private boolean viewed;
	//when the message was sent
	private DateTime timestamp;
	
	/*** Getters and Setters ****/
	
	@JsonProperty
	public long getMessageId() {
		return messageId;
	}

	@JsonProperty
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	@JsonProperty
	public long getSenderId() {
		return senderId;
	}
	
	@JsonProperty
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	
	@JsonProperty
	public String getUserName() {
		return userName;
	}
	
	@JsonProperty
	public void setUserName(String senderName) {
		this.userName = senderName;
	}
	
	@JsonProperty
	public long getSendeeId() {
		return sendeeId;
	}
	
	@JsonProperty
	public void setSendeeId(long sendeeId) {
		this.sendeeId = sendeeId;
	}
	
	@JsonProperty
	public String getMessage() {
		return message;
	}
	
	@JsonProperty
	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonProperty
	public boolean isViewed() {
		return viewed;
	}
	
	@JsonProperty
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
	
	@JsonProperty
	public DateTime getTimestamp() {
		return timestamp;
	}
	
	@JsonProperty
	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	@JsonProperty
	public String getTimeStampString() {
		return this.timestamp.toString("MM/dd/yyyy");
	}
}
