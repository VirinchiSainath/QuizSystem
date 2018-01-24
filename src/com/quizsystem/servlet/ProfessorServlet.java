package com.quizsystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizsystem.entity.Professor;
import com.quizsystem.entity.Student;
import com.quizsystem.service.ProfessorService;

/**
 * Servlet implementation class ProfessorServlet
 */
@WebServlet("/ProfessorServlet")
public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorServlet() {
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
			ProfessorService ps = new ProfessorService();
	
			if(action.equals("signup")){
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String userId = request.getParameter("userId");
				String password = request.getParameter("password");	
				
				Professor p = new Professor(0,firstName,lastName,email,userId,password);
				ps.createProfessor(p);
				targetPage = "login.jsp";
			}
			else if(action.equals("logout")){
				
			}
			else if(action.equals("studentsignup")){
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String userId = request.getParameter("userId");
				String password = request.getParameter("password");
				
				Student s = new Student(0,firstName,lastName,email,userId,password);
				ps.createStudent(s);
				targetPage = "studentsignup.jsp";
			}
			ps.close();
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
