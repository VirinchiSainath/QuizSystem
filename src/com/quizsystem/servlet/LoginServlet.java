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
import com.quizsystem.service.StudentService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			StudentService ss = new StudentService();
			String userFirstName = null;
			
			if (action.equals("login")){
				String userId = request.getParameter("loginid");
				String password = request.getParameter("password");
				String userRole = request.getParameter("userrole");
				int id = 0;
				if(userRole.equals("professor"))	{			
					Professor p = new Professor(0,null,null,null,userId,password);
					p = ps.loginProfessor(p);
					id = p.getId();
					userFirstName = p.getFirstName();
				}
				else {
					Student s =new Student(0,null,null,null,userId,password);
					s = ss.loginStudent(s);
					id = s.getId();
					userFirstName = s.getFirstName();					
				}
				
				if(id==0){
					request.setAttribute("error", "Login Failed ! Please try again !");
					targetPage = "login.jsp";
				}
				else{
					request.getSession().setAttribute("id", id );
					request.getSession().setAttribute("firstname",userFirstName);	
					request.getSession().setAttribute("userRole", userRole);
					targetPage = "home.jsp";
				}	
			}
			else {
				request.getSession().invalidate();
				targetPage = "login.jsp";
			}
			ps.close();
			request.getRequestDispatcher(targetPage).forward(request, response);
		}
		catch(Exception e){
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
