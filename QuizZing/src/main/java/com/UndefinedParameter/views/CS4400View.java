package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;

import com.UndefinedParameter.app.core.Quiz;
import com.UndefinedParameter.app.core.QuizManager;

public class CS4400View extends View{

	public CS4400View() {
		super("cs4400.ftl");
	}
	
	public ArrayList<Quiz> getQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		
		quizzes.add(QuizManager.findQuiz(2));
		quizzes.add(QuizManager.findQuiz(3));
		quizzes.add(QuizManager.findQuiz(4));
		
		return quizzes;
	}
}
