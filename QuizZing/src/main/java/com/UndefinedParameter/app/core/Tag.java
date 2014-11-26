package com.UndefinedParameter.app.core;

/*
 * Tag represents categories that can be added to questions
 * 		This can be used to query for specific types of questions
 * 		and quizzes. However, the tags of the quiz will be determined by
 * 		the tags of the questions.
 */
public class Tag {
	int id;
	String name;
	String description;
	
	//constructor
	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/***************** GETTERS AND SETTERS ***********************/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
