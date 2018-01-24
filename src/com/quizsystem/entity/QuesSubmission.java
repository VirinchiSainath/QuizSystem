package com.quizsystem.entity;

import java.util.ArrayList;
import java.util.List;

public class QuesSubmission {
	
	static int counter = 0;
	static int quizId = 0;
		
	
	int questionNumber = 0;
	int maxQ = 0;
	int questionId;
	String questionDesc;
	List<Integer> ansList; //student responses
	boolean visited;
	
	
	
	public QuesSubmission(int questionId, String questionDesc){
		this.questionId = questionId;
		this.questionDesc = questionDesc;
		visited = false;
		ansList = new ArrayList<>();
		maxQ = ++counter;
		questionNumber = counter;
		
	}
	
	public static int getQuizId() {
		return quizId;
	}

	public static void setQuizId(int quizId) {
		QuesSubmission.quizId = quizId;
	}
	
	public int getMaxQ() {
		return maxQ;
	}

	public void setMaxQ(int maxQ) {
		this.maxQ = maxQ;
	}

	public QuesSubmission(int questionId, String questionDesc, List<Integer> ansList, boolean answered) {
		super();
		this.questionId = questionId;
		this.questionDesc = questionDesc;
		this.ansList = ansList;
		this.visited = answered;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public List<Integer> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<Integer> ansList) {
		this.ansList = ansList;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	
}
