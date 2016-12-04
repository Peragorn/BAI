package com.bai.ps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bai.ps.business.AllowedMessagesBusiness;
import com.bai.ps.business.MessageBusiness;
import com.bai.ps.business.UserBusiness;
import com.bai.ps.model.Message;
import com.bai.ps.model.StatusValue;
import com.bai.ps.model.User;

/**
 * Servlet implementation class UserPanel
 */
public class UserPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AllowedMessagesBusiness allowedMessageBusiness = new AllowedMessagesBusiness();
	public MessageBusiness messageBusiness = new MessageBusiness();
	public UserBusiness userBusiness = new UserBusiness();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPanel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute( "userObject" );
		if(request.getParameter("lock") != null && request.getParameter("lock").equals(StatusValue.lock.getName())){
			user.setLoginAttempt(Integer.parseInt(request.getParameter("n")));
			userBusiness.setLoginAttemptCounter(user);
			
		}
		request.getSession().setAttribute("userAttempt", user.getLoginAttempt());
		request.setAttribute("userLogs", user);
		request.getRequestDispatcher("/userPanel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
