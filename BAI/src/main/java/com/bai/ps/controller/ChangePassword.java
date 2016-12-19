package com.bai.ps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bai.ps.business.UserBusiness;
import com.bai.ps.dao.MessageDao;
import com.bai.ps.dao.UserDao;
import com.bai.ps.model.User;
import com.bai.ps.model.UserPasswordMask;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		UserBusiness userBusiness = new UserBusiness();
		user = (User) request.getSession().getAttribute( "userObject" );
		
		if(user != null){
			String action = request.getParameter("action");
			
			if(action == null){
				UserPasswordMask userPasswordMask = userBusiness.getUserPasswordMaskByUserId(user);
				char[] passwordMask = userPasswordMask.getMask().toCharArray();
				request.setAttribute("passwordMask", passwordMask);
				request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
			}else if(action.equals("Zmien")){
				
			}
			
		}else{
			System.err.println("Blad UserMessage.java, Nie jestes zalogowany");
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
