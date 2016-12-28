package com.bai.ps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bai.ps.business.UnregisteredUserBusiness;
import com.bai.ps.business.UserBusiness;
import com.bai.ps.dao.UnregisteredUserDao;
import com.bai.ps.model.UnregisteredUser;
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
		UnregisteredUserDao ub = new UnregisteredUserDao();
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
			user = userBusiness.getUserByName(login);
			if(user != null){
				if(user.isAccountLocked()){
					request.setAttribute("lock", "Konto zablokowane");
				}
				else{
					request.setAttribute("down", "Kolejna proba mozliwa:" + user.getAccountLoginBlocked());
				}
			}else{
				UnregisteredUser user2 = ub.getUserByName(login);
				if(user2 != null){
					if(user2.isAccountLocked()){
						request.setAttribute("lock", "Konto zablokowane");
					}
					else{
						request.setAttribute("down", "Kolejna proba mozliwa:" + user2.getAccountLoginBlocked());
					}
				}
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
