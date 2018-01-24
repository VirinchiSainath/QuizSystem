package com.quizsystem.entity;

public class Answer {
	int id;
	int questionId;
	String Answer;
	String correctness;
	public Answer(int id, int questionId, String answer, String correctness) {
		super();
		this.id = id;
		this.questionId = questionId;
		Answer = answer;
		this.correctness = correctness;
	}
	public int getId() {
		return id;
	}
	public String getCorrectness() {
		return correctness;
	}
	public void setCorrectness(String correctness) {
		this.correctness = correctness;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	
	

}
