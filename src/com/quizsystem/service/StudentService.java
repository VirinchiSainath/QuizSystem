package com.quizsystem.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quizsystem.entity.Professor;
import com.quizsystem.entity.Student;

public class StudentService extends BaseService {
	
	public Student loginStudent(Student s) throws SQLException {
		String q = "select id, firstname, lastname from student where username=? and password =?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setString(1,s.getUserId());
		ps.setString(2,s.getPassword());
		ResultSet rs = ps.executeQuery();
		int id=0;
		String firstname=null, lastname=null;
		
		if(rs.next()){
			id = rs.getInt(1);
			firstname = rs.getString(2);
			lastname = rs.getString(3);
		}
		
		return new Student(id,firstname,lastname,null,null,null);
		
		}

	public List<Student> listStudents() throws SQLException{
		String q = "select * from student";
		PreparedStatement ps = con.prepareStatement(q);
		ResultSet rs = ps.executeQuery();
		List<Student> students = new ArrayList<Student>();
		Student s= null;
		while(rs.next()){
			s = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			students.add(s);
		}
		return students;
	}

	public void createStudent(Student s) throws SQLException {
		String q ="select max(id) from student";
		PreparedStatement ps = con.prepareStatement(q);
		ResultSet rs = ps.executeQuery();
		int id = 0;
		if(rs.next()){
			id = rs.getInt(1);
		}
		q = "insert into student values (?,?,?,?,?,?)";
		ps = con.prepareStatement(q);
		ps.setInt(1, id+1);
		ps.setString(2, s.getFirstName());
		ps.setString(3, s.getLastName());
		ps.setString(4, s.getEmail());
		ps.setString(5, s.getUserId());
		ps.setString(6, s.getPassword());
		ps.execute();
		
	}

	public Student getStudentDetails(int id) throws SQLException {
		String q = "select * from student where id=?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Student s=null;
		if(rs.next()){
			s = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
		}
		return s;
	}

	public void updateStudent(Student s) throws SQLException {
		String q = "update student set firstname=?, lastname=?, email=?, username=?, password=? where id=?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setString(1, s.getFirstName());
		ps.setString(2, s.getLastName());
		ps.setString(3, s.getUserId());
		ps.setString(4, s.getUserId());
		ps.setString(5, s.getPassword());
		ps.setInt(6, s.getId());
		ps.executeUpdate();
		
	}

	public void deleteStudent(int id) throws SQLException {
		String q = "delete from student where id=?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setInt(1, id);
		ps.execute();
	}

	public List<Student> searchStudents(String search) throws SQLException {
		String q = "select * from student where upper(firstname) like ?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setString(1, "%"+search.toUpperCase()+"%");
		ResultSet rs = ps.executeQuery();
		List<Student> students = new ArrayList<Student>();
		while(rs.next()){
			students.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
		}
		return students;
	}

}
