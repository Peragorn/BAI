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
import com.bai.ps.model.AllowedMessages;
import com.bai.ps.model.Message;
import com.bai.ps.model.StatusValue;
import com.bai.ps.model.User;

/**
 * Servlet implementation class UserMessage
 */
public class UserMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMessage() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user = (User) request.getSession().getAttribute( "userObject" );
		request.getParameter("addMessage");
		if(user != null){
			MessageBusiness messageBusiness = new MessageBusiness();
			AllowedMessagesBusiness allowedMessagesBusiness = new AllowedMessagesBusiness();
			UserBusiness userBusiness = new UserBusiness();
			
			if(request.getParameter("addMessage") != null && request.getParameter("addMessage").equals(StatusValue.addMessage.getName())){
				Message message = new Message();
				AllowedMessages allowedMessages = new AllowedMessages();
				String text = request.getParameter("newMessage");
				message.setText(text);
				message.setUser_id(user);
				messageBusiness.addMessge(message, user);
				
				allowedMessages.setMessage_id(message);
				allowedMessages.setUser_id(user);
				allowedMessagesBusiness.addPermission(message, user, user);
			}
			if(request.getParameter("removeMessage") != null && request.getParameter("removeMessage").equals(StatusValue.removeMessage.getName())){
				Message messageToRemove = new Message();
				
				messageToRemove = (Message) request.getSession().getAttribute(request.getParameter("messageToRemove"));
				messageBusiness.removeMessage(messageToRemove, user);
				
			}
			if(request.getParameter("editMessage") != null && request.getParameter("editMessage").equals(StatusValue.editMessage.getName())){
				
			}
			else{
				List<Message> messageList = messageBusiness.getUserMessages(user);
				request.setAttribute("message", messageList);
				
				List<User> userList = userBusiness.getUsersList();
				request.setAttribute("users", userList);
				
				request.getRequestDispatcher("/messages.jsp").forward(request, response);
			}
		}
		else{
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
