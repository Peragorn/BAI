package com.bai.ps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.UShortSeqHolder;

import com.bai.ps.business.UserBusiness;
import com.bai.ps.model.User;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User user = new User();
		UserBusiness userBusiness = new UserBusiness();
		user.setName(login);
		user.setPassword_hash(password);
		user = userBusiness.login(user);
		
		if(user != null){
			session.setAttribute("userObject", user);
			session.setAttribute("user", user.getName());
			session.setMaxInactiveInterval(300);
			response.sendRedirect("/BAI");			
		}
		else{
			response.sendRedirect("login.jsp");
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
