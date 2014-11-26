package com.UndefinedParameter.app.core;

import java.util.Random;


public class Question {
	
	/*
	 *  TODO: May be necessary to use inheritance here instead
	 *  	of tracking the enum type
	 */
	public enum QuestionType {
		MULTIPLE_CHOICE,
		FILL_IN_THE_BLANK,
		SHORT_ANSWER
		//TODO: Add more question types as needed
	}
	
	/*
	 * The id of a question
	 */
	private int questionId;
	
	/*
	 * The user id of the creator
	 */
	private int creatorId;
	
	/*
	 * Difficulty rating of the question
	 */
	private int questionDifficulty;
	
	/*
	 * Text Body of the question
	 */
	private String questionText;
	
	/*
	 * The correct answer of the question
	 */
	private String correctAnswer;
	
	/*
	 * Incorrect answers to the question - this is for
	 * 		multiple choice and is optional
	 */
	private String[] wrongAnswers;
	
	/*
	 * Whether the question has been flagged as incorrect
	 * 		or unuseful by a user
	 */
	private boolean flagged = false;
	
	//TODO: These variables need to be added to table
	//		default them for now for prototype only
	private int answerCount = 5; //TODO: this has to be set properly

	private int correctPosition = 0; //this only applies to unordered answers
	private boolean ordered = false;
	private QuestionType type = QuestionType.MULTIPLE_CHOICE;
	
	//contains a list of answers ordered by the flags
	private String[] allAnswers;
	
	public void setAnswers() {
		String[] answers = new String[answerCount];
		
		//build the answers from correct and wrong
		int pos = 0;
		for(; pos < correctPosition; pos++)
			answers[pos] = wrongAnswers[pos];
		answers[correctPosition] = correctAnswer;
		pos++;
		for(; pos < answerCount; pos++)
			answers[pos] = wrongAnswers[pos-1];

		//set all answers
		allAnswers = answers;
		//shuffle if ordered flag is false
		if(!ordered) {
			Random rand = new Random();
			for(int i = allAnswers.length - 1; i > 0; i--) {
				int idx = rand.nextInt(i+1);
				
				String temp = allAnswers[idx];
				allAnswers[idx] = allAnswers[i];
				allAnswers[i] = temp;
			}
		}
	}
	
	/*
	 * Check for correct answer to a question
	 */
	public boolean isCorrectAnswer(String answer) {
		return answer.equals(correctAnswer);
	}
	
	/*
	 * Returns an answer of a question by an int index
	 */
	public String getAnswerAt(int index) {
		if(allAnswers == null || allAnswers.length == 0)
			setAnswers();
		if(index < allAnswers.length)
			return allAnswers[index];
		else 
			return "";
	}
	
	/*
	 * Returns an array of all the answers in a quiz
	 */
	public String[] getAnswers() {
		if(allAnswers == null || allAnswers.length == 0) {
			setAnswers();
		}
		return allAnswers;
	}
	
	/*********************************** Getters and Setters *********************************/
	
	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public int getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	
	public int getQuestionDifficulty() {
		return questionDifficulty;
	}
	
	public void setQuestionDifficulty(int questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String[] getWrongAnswers() {
		return wrongAnswers;
	}
	
	public void setWrongAnswers(String[] wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
	
	public boolean isOrdered() {
		return ordered;
	}
	
	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public int getCorrectPosition() {
		return correctPosition;
	}

	public void setCorrectPosition(int correctPosition) {
		this.correctPosition = correctPosition;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	
}
