package com.bai.ps.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class EditMessage
 */
public class EditMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AllowedMessagesBusiness allowedMessageBusiness = new AllowedMessagesBusiness();
	public MessageBusiness messageBusiness = new MessageBusiness();
	public UserBusiness userBusiness = new UserBusiness();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("editMessage") != null && request.getParameter("editMessage").equals(StatusValue.editMessage.getName())){
			Message message = messageBusiness.getMessageByID(Integer.parseInt(request.getParameter("messageID")));
			String editedText = request.getParameter("editedMessage");
			
			User user = (User) request.getSession().getAttribute( "userObject" );
			messageBusiness.editMessage(user, message, editedText);
		}
		Message message = messageBusiness.getMessageByID(Integer.parseInt(request.getParameter("messageID")));
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("/editMessage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
