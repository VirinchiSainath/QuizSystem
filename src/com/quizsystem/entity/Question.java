package com.quizsystem.entity;

import java.util.List;

public class Question {
	int id;
	int QuizId;
	String description;
	List<Answer> answers;
	
	public Question(int id, int quizId, String description, List<Answer> answers) {
		super();
		this.id = id;
		QuizId = quizId;
		this.description = description;
		this.answers = answers;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuizId() {
		return QuizId;
	}

	public void setQuizId(int quizId) {
		QuizId = quizId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
