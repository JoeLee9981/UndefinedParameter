package com.UndefinedParameter.app.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Question {
	
	/*
	 *  TODO: May be necessary to use inheritance here instead
	 *  	of tracking the enum type
	 */
	public enum QuestionType {
		MULTIPLE_CHOICE,
		TRUE_FALSE,
		FILL_IN_THE_BLANK,
		SHORT_ANSWER,
		MATCHING
	}
	
	private List<String> categories;
	private long questionId;
	private long creatorId;
	private long groupId; //this is the original group the question was created for
	private double difficulty;
	private double rating;
	private int userRating;
	private int userDifficulty;
	private String questionText;
	private String correctAnswer;
	private ArrayList<String> wrongAnswers;
	private boolean flagged = false;
	private String explanation;
	private String reference;
	private String referenceLink;
	
	//TODO: These variables need to be added to table
	//		default them for now for prototype only
	private int correctPosition = 0; //this only applies to unordered answers
	private boolean ordered = false;
	private QuestionType type = QuestionType.MULTIPLE_CHOICE;
	
	//contains a list of answers ordered by the flags
	private String[] allAnswers;
	
	//this is necessary for the JSON Serialization
	public Question()
	{
	}
	
	public Question(long qID, long cID, long groupId, double difficulty, double rate, String qt, String qText, String answer, ArrayList<String> wrong, Boolean flag, String ref, String explan)
	{
		this.questionId = qID;
		this.creatorId = cID;
		this.groupId = groupId;
		this.difficulty = difficulty;
		this.rating = rate;
		this.type = QuestionType.valueOf(qt);
		this.questionText = qText;
		this.correctAnswer = answer;
		this.wrongAnswers = wrong;
		this.flagged = flag;
		this.reference = ref;
		this.explanation = explan;
		this.categories = new ArrayList<String>();
	}
	
	public Question(long qID, long cID, long groupId, double difficulty, double rate, String qt, String qText, String answer, ArrayList<String> wrong, Boolean flag, String ref, String explan, ArrayList<String> categories)
	{
		this.questionId = qID;
		this.creatorId = cID;
		this.groupId = groupId;
		this.difficulty = difficulty;
		this.rating = rate;
		this.type = QuestionType.valueOf(qt);
		this.questionText = qText;
		this.correctAnswer = answer;
		this.wrongAnswers = wrong;
		this.flagged = flag;
		this.reference = ref;
		this.explanation = explan;
		this.categories = categories;
	}
	
	public void setAnswers() {
		String[] answers = new String[wrongAnswers.size() + 1];
		
		//build the answers from correct and wrong
		int pos = 0;
		for(; pos < correctPosition; pos++)
			answers[pos] = wrongAnswers.get(pos);
		answers[correctPosition] = correctAnswer;
		pos++;
		for(; pos < getAnswerCount(); pos++)
			answers[pos] = wrongAnswers.get(pos-1);

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
	
	public long getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
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
	public double getDifficulty() {
		return difficulty;
	}
	
	@JsonProperty
	public void setDifficulty(double f) {
		this.difficulty = f;
	}
	
	@JsonProperty
	public String getQuestionText() {
		return questionText;
	}
	
	@JsonProperty
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	@JsonProperty
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	@JsonProperty
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	@JsonProperty
	public ArrayList<String> getWrongAnswers() {
		return wrongAnswers;
	}
	
	@JsonProperty
	public void setWrongAnswers(ArrayList<String> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	
	@JsonProperty
	public boolean isFlagged() {
		return flagged;
	}
	
	@JsonProperty
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
	
	@JsonProperty
	public boolean isOrdered() {
		return ordered;
	}
	
	@JsonProperty
	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	@JsonProperty
	public QuestionType getType() {
		return type;
	}

	@JsonProperty
	public void setType(QuestionType type) {
		this.type = type;
	}

	@JsonProperty
	public int getCorrectPosition() {
		return correctPosition;
	}

	@JsonProperty
	public void setCorrectPosition(int correctPosition) {
		this.correctPosition = correctPosition;
	}

	@JsonProperty
	public int getAnswerCount() {
		return wrongAnswers.size() + 1;
	}

	@JsonProperty
	public double getRating() {
		return rating;
	}

	@JsonProperty
	public void setRating(double f) {
		this.rating = f;
	}

	@JsonProperty
	public void setQuestionType(QuestionType questionType) {
		this.type = questionType;		
	}
	
	@JsonProperty
	public QuestionType getQuestionType()
	{
		return type;
	}

	@JsonProperty
	public long getGroupId() {
		return groupId;
	}

	@JsonProperty
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	@JsonProperty
	public String getExplanation() {
		return explanation;
	}
	
	public String getExplanationFormatted() {
		return explanation.replace("<br/>", "\n");
	}

	@JsonProperty
	public void setExplanation(String explan) {
		this.explanation = explan;
	}
	
	@JsonProperty
	public String getReference() {
		return reference;
	}

	@JsonProperty
	public void setReference(String ref) {
		this.reference = ref;
	}

	@JsonProperty
	public String getReferenceLink() {
		return referenceLink;
	}

	@JsonProperty
	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}

	@JsonProperty
	public int getUserRating() {
		return userRating;
	}

	@JsonProperty
	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}

	@JsonProperty
	public int getUserDifficulty() {
		return userDifficulty;
	}

	@JsonProperty
	public void setUserDifficulty(int userDifficulty) {
		this.userDifficulty = userDifficulty;
	}

	@JsonProperty
	public List<String> getCategories() {
		return categories;
	}

	@JsonProperty
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	public String getCategoriesString() {
		
		if(categories == null || categories.size() == 0) {
			return "None";
		}
		
		StringBuilder catString = new StringBuilder();
		
		for(int i = 0; i < categories.size(); i++) {
			catString.append("#");
			catString.append(categories.get(i));
			if(i != categories.size() - 1) {
				catString.append(", ");
			}
			
		}
		return catString.toString();
	}
	
	public List<String> getMatchingQuestions() {
		
		ArrayList<String> matchingQuestions = new ArrayList<String>();
		Matcher matcher;
		Pattern pattern5 = Pattern.compile("&lt;1&gt;(.*)&lt;2&gt;(.*)&lt;3&gt;(.*)&lt;4&gt;(.*)&lt;5&gt;(.*)");
		matcher = pattern5.matcher(questionText);
		
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim());
			matchingQuestions.add(matcher.group(2).trim());
			matchingQuestions.add(matcher.group(3).trim());
			matchingQuestions.add(matcher.group(4).trim());
			matchingQuestions.add(matcher.group(5).trim());
			return matchingQuestions;
		}
		
		
		Pattern pattern4 = Pattern.compile("&lt;1&gt;(.*)&lt;2&gt;(.*)&lt;3&gt;(.*)&lt;4&gt;(.*)");
		matcher = pattern4.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim());
			matchingQuestions.add(matcher.group(2).trim());
			matchingQuestions.add(matcher.group(3).trim());
			matchingQuestions.add(matcher.group(4).trim());
			return matchingQuestions;
		}
		
		Pattern pattern3 = Pattern.compile("&lt;1&gt;(.*)&lt;2&gt;(.*)&lt;3&gt;(.*)");
		matcher = pattern3.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim());
			matchingQuestions.add(matcher.group(2).trim());
			matchingQuestions.add(matcher.group(3).trim());
			return matchingQuestions;
		}
		
		Pattern pattern2 = Pattern.compile("&lt;1&gt;(.*)&lt;2&gt;(.*)");
		matcher = pattern2.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1));
			matchingQuestions.add(matcher.group(2));
			return matchingQuestions;
		}
		return matchingQuestions;
	}

}
