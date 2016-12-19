package com.bai.ps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bai.ps.business.UserBusiness;
import com.bai.ps.model.User;
import com.bai.ps.model.UserPasswordMask;

/**
 * Servlet implementation class Login_ps4
 */
public class Login_ps4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_ps4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		UserBusiness userBusiness = new UserBusiness();
		
		String mask = "0000000000000000";
		char[] passwordMask = null;
		
		if(action.equals("Dalej")){
			
			String login = request.getParameter("login");
			User user = userBusiness.getUserByName(login);
			if(user != null){
				UserPasswordMask userPasswordMask = userBusiness.getUserPasswordMaskByUserId(user);
				passwordMask = userPasswordMask.getMask().toCharArray();
			}else{
				passwordMask = mask.toCharArray();
			}
			
			request.setAttribute("passwordMask", passwordMask);
			request.setAttribute("login", login);
			request.getRequestDispatcher("/loginMask_ps4.jsp").forward(request, response);
			
		}else if(action.equals("Zaloguj")){
			
			StringBuilder result = new StringBuilder("");
			String login = request.getParameter("login");
			User user = userBusiness.getUserByName(login);
			
			if(user != null){
				UserPasswordMask userPasswordMask = userBusiness.getUserPasswordMaskByUserId(user);
				passwordMask = userPasswordMask.getMask().toCharArray();
				
				for (int i=0;i<passwordMask.length;i++) {
					if(passwordMask[i] == '1'){
						String passChar = request.getParameter("pass[" + i + "]");
						if(passChar != null && passChar.length() == 1){
							result.append(passChar);
						}
					}
				}
				if(userBusiness.loginPwdMask(userPasswordMask, result.toString())){
					
					if(userBusiness.changeUserMask(userPasswordMask)){
						User userLoged = userBusiness.login(user);
						
						if(userLoged != null){
							session.setAttribute("userObject", userLoged);
							session.setAttribute("user", userLoged.getName());
							session.setMaxInactiveInterval(300);
							response.sendRedirect("/BAI");			
						}
						else{
							response.sendRedirect("login.jsp");
						}
					}
				}
				
			}
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
