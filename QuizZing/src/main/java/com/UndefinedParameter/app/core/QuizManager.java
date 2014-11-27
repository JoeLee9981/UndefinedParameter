package com.UndefinedParameter.app.core;

import java.util.ArrayList;

/*
 * Class is responsible for managing of quizzes
 * 	Contains methods for creating, editing, removing
 * 	quizzes. Functions as a model layer over the database.
 */
public class QuizManager {
	
	/*
	 * Generates a random quiz
	 * 		TODO: Need parameters and possible overloaded methods
	 * 			  to allow customization
	 */
	public static Quiz generateRandomQuiz() {
		//TODO: retreive quiz from database
		Quiz quiz = new Quiz();
		
		//TODO: Implement
		
		return quiz;
	}
	
	/*
	 * TODO: Implement this
	 */
	public static void deleteQuiz(int id) {
		//remove quiz from database
	}
	
	/*
	 * TODO: Add methods used for quiz management
	 */
	public static Quiz findQuiz(int id) {
		//TODO: Make a call to db to find this
		Quiz quiz = new Quiz();
		
		quiz.setQuizId(id);
		quiz.setCreatorId(100);
		quiz.setDescription("THIS IS A FUN QUIZ");
		quiz.setRating(5);
		quiz.setDifficulty(1);
		quiz.setTime(1000);
		quiz.setQuestions(getQuestions(id));
		return quiz;
	}
	
	/*
	 * TODO: Implement this
	 */
	private static ArrayList<Question> getQuestions(int quizId) {
		ArrayList<Question> questions = new ArrayList<Question>(); //TODO: get this from database
		
		for(int i = 0; i < 5; i++) {
			questions.add(getQuestion(i));
		}
		return questions;
	}
	
	/*
	 *  TODO: Implement this - NOTE THIS WILL NOT WORK FOR REAL CODING
	 */
	private static Question getQuestion(int questionId) {
		Question question = new Question(questionId);
		question.setType(Question.QuestionType.MULTIPLE_CHOICE);
		question.setCreatorId(questionId * questionId);
		question.setQuestionDifficulty(5);
		question.setQuestionText("This is question " + questionId + ", please pick your answer");
		question.setCorrectAnswer("This is the correct answer");
		question.setCorrectPosition(questionId);
		String[] wrongAnswers = {
				"This is a wrong answer", 
				"This is another wrong answer", 
				"This is the 3rd wrong answer", 
				"Last wrong answere here"
			};
		question.setWrongAnswers(wrongAnswers);
		
		return question;
	}
}
