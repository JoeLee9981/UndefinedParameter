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
	
	private int questionId;
	private int creatorId;
	private int questionDifficulty;
	private int rating;
	private String questionText;
	private String correctAnswer;
	private String[] wrongAnswers;
	private boolean flagged = false;
	
	//TODO: These variables need to be added to table
	//		default them for now for prototype only
	private int answerCount = 5; //TODO: this has to be set properly
	private int correctPosition = 0; //this only applies to unordered answers
	private boolean ordered = false;
	private QuestionType type = QuestionType.MULTIPLE_CHOICE;
	
	//contains a list of answers ordered by the flags
	private String[] allAnswers;
	
	public Question(int qID)
	{
		this.questionId = qID;
	}
	
	public Question(int qID, int cID, int difficulty, int rate, String qt, String qText, String answer, String[] wrong, Boolean flag)
	{
		this.questionId = qID;
		this.creatorId = cID;
		this.questionDifficulty = difficulty;
		this.rating = rate;
		this.type = QuestionType.valueOf(qt);
		this.questionText = qText;
		this.correctAnswer = answer;
		this.wrongAnswers = wrong;
		this.flagged = flag;
	}
	
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
	
	public boolean isCorrectAnswer(String answer) {
		return answer.equals(correctAnswer);
	}
	
	public String getAnswerAt(int index) {
		if(allAnswers == null || allAnswers.length == 0)
			setAnswers();
		if(index < allAnswers.length)
			return allAnswers[index];
		else 
			return "";
	}
	
	public String[] getAnswers() {
		if(allAnswers == null || allAnswers.length == 0) {
			setAnswers();
		}
		return allAnswers;
	}
	
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
