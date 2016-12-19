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
				
				UserPasswordMask userPasswordMask = userBusiness.getUserPasswordMaskByUserId(user);
				char[] passwordMask = userPasswordMask.getMask().toCharArray();
				StringBuilder result = new StringBuilder("");
				
				if(request.getParameter("password").length() < 8 || 
						request.getParameter("password").length() > 16){
					
					request.setAttribute("info", "Haslo musi miec 8-16 znakow.");
					request.setAttribute("passwordMask", passwordMask);
					request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
					return;
				}
				
				for (int i=0;i<passwordMask.length;i++) {
					if(passwordMask[i] == '1'){
						String passChar = request.getParameter("pass[" + i + "]");
						if(passChar != null && passChar.length() == 1){
							result.append(passChar);
						}
					}
				}
				
				if(userBusiness.loginPwdMask(userPasswordMask, result.toString())){
				
					userBusiness.changePassword(userPasswordMask, request.getParameter("password"));
					response.sendRedirect("/BAI");	
					
				}else{
					request.setAttribute("info", "Bledne stare haslo.");
					request.setAttribute("passwordMask", passwordMask);
					request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
				}
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
