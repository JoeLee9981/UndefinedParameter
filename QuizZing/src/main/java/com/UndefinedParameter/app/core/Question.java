package com.UndefinedParameter.app.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Question {
	
	/*
	 *  ENUMS used to represent the types of the questions
	 */
	public enum QuestionType {
		MULTIPLE_CHOICE,
		TRUE_FALSE,
		FILL_IN_THE_BLANK,
		SHORT_ANSWER,
		MATCHING
	}
	
	/**** member variables ****/
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
	private String flaggedReason;
	private String explanation;
	private String reference;
	private String referenceLink;
	
	private int correctPosition = 0; //this only applies to unordered answers
	private boolean ordered = false;
	private QuestionType type = QuestionType.MULTIPLE_CHOICE;
	
	//contains a list of answers ordered by the flags
	private String[] allAnswers;
	
	//this is necessary for the JSON Serialization
	public Question()
	{
	}
	
	/**** CONSTRUCTORS ****/
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
	
	/**
	 * Set all the answers in the quiz
	 * 	This will provide randomization of the answers when necessary
	 */
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
	
	/**
	 * Used to validate a correct answer
	 * @param answer
	 * @return
	 */
	public boolean isCorrectAnswer(String answer) {
		return answer.equals(correctAnswer);
	}
	
	/**
	 * Gets thanswer at an index
	 * @param index
	 * @return
	 */
	public String getAnswerAt(int index) {
		if(allAnswers == null || allAnswers.length == 0)
			setAnswers();
		if(index < allAnswers.length)
			return allAnswers[index];
		else 
			return "";
	}
	
	/**
	 * Returns all answers
	 * @return
	 */
	public String[] getAnswers() {
		if(allAnswers == null || allAnswers.length == 0) {
			setAnswers();
		}
		return allAnswers;
	}
	
	/**** GETTERS AND SETTERS ****/
	
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
	
	/**
	 * 
	 * Gets the first line of text in a question only
	 * @return the first line of text
	 */
	public String getQuestionTextFirstLine()
	{
		int indexOfLinebreak = questionText.indexOf("<br/>");
		if (indexOfLinebreak >= 0)
		{
			return questionText.substring(0, indexOfLinebreak);
		}
		else
		{
			return questionText;
		}
	}
	
	/**
	 * Formats the question text for display inside of html and javascript elements
	 * @return
	 */
	public String getQuestionTextFormatted() {
		if(type != QuestionType.MATCHING && type != QuestionType.FILL_IN_THE_BLANK) {
			return questionText.replace("&amp;", "&");
		}
		return questionText.replace("&amp;lt;blank&amp;gt;", "___________").replace("&amp;", "&");					
	}
	
	/**
	 * Provides an alternate display for the question text for
	 * 		Fill in the blank questions where it shows the <blank>
	 * 		instead of ___________
	 * @return
	 */
	public String getQuestionTextBlankFormatted() {
		return questionText.replace("&amp;", "&");
	}
	
	/*** MORE GETTERS AND SETTERS ****/
	
	@JsonProperty
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	@JsonProperty
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	public String getCorrectAnswerFormatted() {
		return correctAnswer.replace("&amp;", "&");
	}
	
	@JsonProperty
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	@JsonProperty
	public ArrayList<String> getWrongAnswers() {
		
		return wrongAnswers;
	}
	
	/**
	 * Provides formatted answers in text used for html and javascript
	 * @return
	 */
	public ArrayList<String> getWrongAnswersFormatted() {
		if(this.type != QuestionType.MATCHING) {
			return wrongAnswers;
		}
		
		ArrayList<String> answers = new ArrayList<String>();
		
		for(int i = 0; i < wrongAnswers.size(); i++) {
			char c = (char)(66 + i);
			answers.add(wrongAnswers.get(i).replace("&amp;", "&"));
		}
		return answers;
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
	public String getFlaggedReason() {
		
		if(StringUtils.isBlank(flaggedReason)) {
			return "Reason not provided.";
		}
		return flaggedReason;
	}

	@JsonProperty
	public void setFlaggedReason(String flaggedReason) {
		this.flaggedReason = flaggedReason;
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
	
	/**
	 * Provides formatted explanation text used for html and javascript display
	 * @return
	 */
	public String getExplanationFormatted() {
		if(StringUtils.isBlank(explanation)) {
			return "";
		}
		return explanation.replace("<br/>", "\n").replace("&amp;", "&");
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
	
	/**
	 * Creates a comma separated string of all categories.
	 * 	Used for display in miscellaneous tables
	 * @return
	 */
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
	
	/**
	 * A method used to parse out the questions in a matching type question text
	 * 		This should only be used for matching questions
	 * 		This also formats them for html and javascript
	 * @return
	 */
	public List<String> getMatchingQuestionsFormatted() {
		
		ArrayList<String> matchingQuestions = new ArrayList<String>();
		Matcher matcher;
		Pattern pattern5 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)&amp;lt;4&amp;gt;(.*)&amp;lt;5&amp;gt;(.*)");
		matcher = pattern5.matcher(questionText);
		
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(2).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(3).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(4).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(5).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			return matchingQuestions;
		}
		
		
		Pattern pattern4 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)&amp;lt;4&amp;gt;(.*)");
		matcher = pattern4.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(2).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(3).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(4).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			return matchingQuestions;
		}
		
		Pattern pattern3 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)");
		matcher = pattern3.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(2).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(3).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			return matchingQuestions;
		}
		
		Pattern pattern2 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)");
		matcher = pattern2.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			matchingQuestions.add(matcher.group(2).trim().replace("&amp;", "&").replace("<br/>", "\n"));
			return matchingQuestions;
		}
		return matchingQuestions;
	}
	
	/**
	 * Like the above it parses out the matching questions, however it does not format
	 * 	This is used for the question building in the quiz page itself.
	 * @return
	 */
	public List<String> getMatchingQuestions() {
		
		ArrayList<String> matchingQuestions = new ArrayList<String>();
		Matcher matcher;
		Pattern pattern5 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)&amp;lt;4&amp;gt;(.*)&amp;lt;5&amp;gt;(.*)");
		matcher = pattern5.matcher(questionText);
		
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim());
			matchingQuestions.add(matcher.group(2).trim());
			matchingQuestions.add(matcher.group(3).trim());
			matchingQuestions.add(matcher.group(4).trim());
			matchingQuestions.add(matcher.group(5).trim());
			return matchingQuestions;
		}
		
		
		Pattern pattern4 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)&amp;lt;4&amp;gt;(.*)");
		matcher = pattern4.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim());
			matchingQuestions.add(matcher.group(2).trim());
			matchingQuestions.add(matcher.group(3).trim());
			matchingQuestions.add(matcher.group(4).trim());
			return matchingQuestions;
		}
		
		Pattern pattern3 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)&amp;lt;3&amp;gt;(.*)");
		matcher = pattern3.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1).trim());
			matchingQuestions.add(matcher.group(2).trim());
			matchingQuestions.add(matcher.group(3).trim());
			return matchingQuestions;
		}
		
		Pattern pattern2 = Pattern.compile("&amp;lt;1&amp;gt;(.*)&amp;lt;2&amp;gt;(.*)");
		matcher = pattern2.matcher(questionText);
		if(matcher.find()) {
			matchingQuestions.add(matcher.group(1));
			matchingQuestions.add(matcher.group(2));
			return matchingQuestions;
		}
		return matchingQuestions;
	}

}
