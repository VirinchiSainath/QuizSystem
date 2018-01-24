package com.quizsystem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizsystem.entity.Professor;
import com.quizsystem.entity.Student;
import com.quizsystem.service.ProfessorService;
import com.quizsystem.service.StudentService;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			String targetPage = null;
			StudentService ss = new StudentService();
	
			if(action.equals("listStudents")){
				List<Student> students= ss.listStudents();	
				request.setAttribute("students", students);
				targetPage="students.jsp";
			}
			
			else if(action.equals("addStudent")){
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String userId = request.getParameter("userId");
				String password = request.getParameter("password");
				
				Student s = new Student(0,firstName,lastName,email,userId,password);
				ss.createStudent(s);
				
				List<Student> students= ss.listStudents();	
				request.setAttribute("students", students);
				targetPage="students.jsp";
			}
			else if(action.equals("editStudent")){
				int id = Integer.parseInt(request.getParameter("id"));
				Student s = ss.getStudentDetails(id);
				request.setAttribute("student", s);
				targetPage="editStudent.jsp";		
			}
			else if(action.equals("updateStudent")){
				int id = Integer.parseInt(request.getParameter("id"));
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String userId = request.getParameter("userId");
				String password = request.getParameter("password");
				
				Student s = new Student(id,firstName,lastName,email,userId,password);
				ss.updateStudent(s);
				
				List<Student> students= ss.listStudents();	
				request.setAttribute("students", students);
				targetPage="students.jsp";		
			}
			else if(action.equals("deleteStudent")){
				int id = Integer.parseInt(request.getParameter("id"));
				ss.deleteStudent(id);				
				List<Student> students= ss.listStudents();	
				request.setAttribute("students", students);
				targetPage="students.jsp";		
			}
			else if(action.equals("searchStudents")){
				String search = request.getParameter("search");
				
				List<Student> searchResults = ss.searchStudents(search);
				request.setAttribute("students", searchResults);
				targetPage="students.jsp";				
			}
			ss.close();
			request.getRequestDispatcher(targetPage).forward(request, response);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
