package com.quizsystem.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quizsystem.entity.Professor;
import com.quizsystem.entity.Student;

public class ProfessorService extends BaseService {

	public void createProfessor(Professor p) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("select max (id) from professor");
		ResultSet rs = ps.executeQuery();
		int id = 0;
		if(rs.next()){
			id = rs.getInt(1);
		}				
				
		ps = con.prepareStatement("insert into professor values (?,?,?,?,?,?)");
		ps.setInt(1, id+1);
		ps.setString(2, p.getFirstName());
		ps.setString(3, p.getLastName());
		ps.setString(4, p.getEmail());
		ps.setString(5, p.getUserId());
		ps.setString(6, p.getPassword());
		ps.execute();	
		
	}
	public Professor loginProfessor(Professor p) throws SQLException {
		String q = "select id, firstname, lastname from professor where username=? and password =?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setString(1,p.getUserId());
		ps.setString(2,p.getPassword());
		ResultSet rs = ps.executeQuery();
		int id=0;
		String firstname=null, lastname=null;
		
		if(rs.next()){
			id = rs.getInt(1);
			firstname = rs.getString(2);
			lastname = rs.getString(3);
		}
		
		return new Professor(id,firstname,lastname,null,null,null);
		
		}
	public void createStudent(Student s) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("select max (id) from student");
		ResultSet rs = ps.executeQuery();
		int id = 0;
		if(rs.next()){
			id = rs.getInt(1);
		}	
		
		String q = "insert into student values (?,?,?,?,?,?)";
		ps = con.prepareStatement(q);
		ps.setInt(1, id+1);
		ps.setString(2, s.getFirstName());
		ps.setString(3, s.getLastName());
		ps.setString(4, s.getEmail());
		ps.setString(5, s.getUserId());
		ps.setString(6, s.getPassword());
		ps.execute();
	}

}
