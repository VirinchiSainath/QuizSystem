package com.quizsystem.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Auth
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if ( (session.getAttribute("id") != null) || (req.getRequestURI().contains("/images/")) || (req.getRequestURI().contains("/signup")) || (req.getRequestURI().contains("/stylesheet")) || (req.getRequestURI().contains("LoginServlet")) || (req.getRequestURI().contains("ProfessorServlet")) || (req.getRequestURI().contains("QuizServlet")) || (req.getRequestURI().contains("StudentServlet"))){
			chain.doFilter(request, response);
		}
		else {
			req.getRequestDispatcher("login.jsp").forward(request, response);
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
