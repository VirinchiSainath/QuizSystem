package com.quizsystem.entity;

public class Quiz {
	int id;
	String name;
	int nOfQues;
	int duration;
	String showAns;
	int profId;
	
	
	public Quiz(int id, String name, int nOfQues, int duration, String showAns, int profId) {
		super();
		this.id = id;
		this.name = name;
		this.nOfQues = nOfQues;
		this.duration = duration;
		this.showAns = showAns;
		this.profId = profId;
	}


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


	public int getnOfQues() {
		return nOfQues;
	}


	public void setnOfQues(int nOfQues) {
		this.nOfQues = nOfQues;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getShowAns() {
		return showAns;
	}


	public void setShowAns(String showAns) {
		this.showAns = showAns;
	}


	public int getProfId() {
		return profId;
	}


	public void setProfId(int profId) {
		this.profId = profId;
	}
	
	
	
	

}
