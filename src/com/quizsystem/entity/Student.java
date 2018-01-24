package com.quizsystem.entity;

public class Student extends Professor{
	
	public Student(int id, String firstName, String lastName, String email, String userId, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userId = userId;
		this.password = password;
	}

}
