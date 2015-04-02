package com.UndefinedParameter.app.core;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This is the class to represent a Quiz.
 * 		It contains a list of incorrect questions (multiple choice)
 * 		as well as the correct question and other stats
 * 		This class can be used to randomize order of quiz, determine a correct
 * 		answer and any other functions INTERNAL to the quiz
 * 
 * 		This class can function similar to an iterator. it can keep track of what
 * 		question is currently being viewed, and is used to retreive the next
 * 		or the last question.
 */
public class Quiz {

	/*
	 * 	This is the id of the quiz
	 */
	private long quizId;
	
	/*
	 * Name of the quiz
	 */
	private String name;
	
	/*
	 * This is the user id of the quiz creator
	 */
	private long creatorId;
	
	/*
	 * Display Name of the Creator
	 */
	private String creatorName;
	
	/*
	 * Name of the group the quiz belongs to
	 */
	private String parentGroupName;
	
	/*
	 * ID of the parent group the quiz belongs to
	 */
	private long parentGroupId;
	
	/*
	 * This is the difficulty of the quiz
	 */
	private int difficulty;
	
	/*
	 * This is the user rating of the quiz
	 * 		This is an averaged rating
	 */
	private int rating;
	
	/*
	 * This is the description of the quiz
	 */
	private String description;
	
	/*
	 * This is an optional argument for time
	 * 		in milliseconds.
	 */
	private int time;
	
	/*
	 * When the quiz was created
	 */
	private DateTime dateCreated;
	
	/*
	 * Time stamp of the last time the quiz was modified
	 */
	private DateTime dateModified;
	
	/*
	 * Timestamp for when the quiz was last accessed
	 */
	private DateTime lastAccessed;
	
	/*
	 * This signifies the quiz is open for editing from any group user
	 */
	private boolean open = false;
	
	/*
	 * A list of populated questions
	 */
	private List<Question> questions;
	
	/*
	 * Count of questions in the quiz
	 */
	private int questionCount;
	
	/*
	 * This is the position of the current question in the quiz
	 */
	public int questionPosition = -1;
	
	//Constructor
	public Quiz() {
		questions = new ArrayList<Question>();
	}
	
	//parameterized constructor to set a list of questions
	public Quiz(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	/********************************* Quiz helper questions *********************************/
	
	/*
	 * Submit a list of answers and determine results of the quiz
	 * 		returns the number of correct answers
	 */
	public int submitQuestions(String[] answers) {
		//TODO: Implement
		return 0;
	}
	
	
	/*
	 * Return the number of questions in a quiz
	 */
	public int getQuestionCount() {
		return this.questionCount;
	}
	
	/*
	 * Set the count of questions
	 */
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	
	/*
	 * Return an array of questions
	 */
	public Question[] getQuestions() {
		return questions.toArray(new Question[questions.size()]);
	}
	
	/*
	 * Sets the questions of a quiz to a list of provided questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	/*
	 * Add a single question to the list
	 */
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	
	/******************************** Standard getters and setters ********************************/
	
	@JsonProperty
	public long getQuizId() {
		return quizId;
	}
	
	@JsonProperty
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	
	@JsonProperty
	public long getCreatorId() {
		return creatorId;
	}
	
	@JsonProperty
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	
	@JsonProperty
	public int getDifficulty() {
		return difficulty;
	}
	
	@JsonProperty
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	@JsonProperty
	public int getRating() {
		return rating;
	}
	
	@JsonProperty
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@JsonProperty
	public String getDescription() {
		return description;
	}
	
	@JsonProperty
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public int getTime() {
		return time;
	}
	
	@JsonProperty
	public void setTime(int time) {
		this.time = time;
	}
	
	@JsonProperty
	public DateTime getDateCreated() {
		return dateCreated;
	}

	@JsonProperty
	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonProperty
	public DateTime getDateModified() {
		return dateModified;
	}

	@JsonProperty
	public void setDateModified(DateTime dateModified) {
		this.dateModified = dateModified;
	}

	@JsonProperty
	public DateTime getLastAccessed() {
		return lastAccessed;
	}

	@JsonProperty
	public void setLastAccessed(DateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	
	public String getDateCreatedAsString() {
		return dateCreated.toString("MM/dd/yyyy");
	}
	
	public String getLastAccessedAsString() {
		return dateCreated.toString("MM/dd/yyyy");
	}
	
	public String getDateModifiedAsString() {
		return dateCreated.toString("MM/dd/yyyy");
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/********************** Iteration Methods ************************************/
	
	/*
	 * Move the position of the quiz 1 over and return the question
	 */
	public Question getNextQuestion() throws Exception {
		if(questions == null)
			throw new Exception("Questions are empty, please add first");
		if(questionPosition >= questions.size()) {
			return questions.get(questionPosition);
		}
		else {
			return questions.get(++questionPosition);
		}
	}
	
	/*
	 * Move the position of the quiz back by 1 and return the question
	 */
	public Question getPreviousQuestion() throws Exception {
		if(questions == null)
			throw new Exception("Questions are empty, please add first");
		if(questionPosition <= 0) {
			return questions.get(questionPosition);
		}
		else {
			return questions.get(--questionPosition);
		}
	}
	
	/*
	 * Return the current question
	 */
	public Question getCurrentQuestion() {
		Question question = questions.get(questionPosition);
		return question;
	}
	
	public Question getQuestion(int pos) {
		if(pos < getQuestionCount())
			return questions.get(pos);
		else
			return null;
	}
	
	/*
	 * Check if at the end of the question list
	 */
	public boolean hasNext() {
		return questionPosition < questions.size() - 1;
	}
	
	/*
	 * Check if at the beginning of the question list
	 */
	public boolean hasPrevious() {
		return questionPosition > 0;
	}

	public String getTimeString() {
		int hours = time / 3600;
		int minutes = time % 3600 / 60;
		int seconds = time % 60;
		
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
