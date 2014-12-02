package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.ArrayList;

import com.UndefinedParameter.app.core.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizCreatorView extends View {

	private ArrayList<Question> questions;
	
	public QuizCreatorView() {
		super("quiz_create.ftl");
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
}
