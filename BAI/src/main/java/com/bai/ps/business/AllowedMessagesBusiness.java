package com.bai.ps.business;

import java.util.List;

import com.bai.ps.dao.AllowedMessagesDao;
import com.bai.ps.model.AllowedMessages;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;

public class AllowedMessagesBusiness {

	AllowedMessagesDao allowedMessagesDao = new AllowedMessagesDao();
	
	public void addPermission(Message message, User user, User messageOwner){
		AllowedMessages allowedMessage = new AllowedMessages();
		MessageBusiness messageBussines = new MessageBusiness();
		
		if(messageOwner.getName().equals(messageBussines.getMessageAuthor(message).getName())){
			allowedMessage.setUser_id(user);
			allowedMessage.setMessage_id(message);
			this.allowedMessagesDao.addPermission(allowedMessage);
		}
		else{
			System.out.println("Uzytkownik nie jest wlascicielem tej wiadmosci");
		}
	}
	
	public void removePermission(Message message, User user, User messageOwner){
		AllowedMessages forbiddenMessage = new AllowedMessages();
		MessageBusiness messageBussines = new MessageBusiness();
		
		if(messageOwner.getName().equals(messageBussines.getMessageAuthor(message).getName())){
			forbiddenMessage.setUser_id(user);
			forbiddenMessage.setMessage_id(message);
			forbiddenMessage.setId(getPermissionId(message));
			this.allowedMessagesDao.removePermission(forbiddenMessage);
		}
		else{
			System.out.println("Uzytkownik nie jest wlascicielem tej wiadmosci");
		}
	}

	private long getPermissionId(Message message) {
		return this.allowedMessagesDao.findPermissionId(message);
	}
	
	public boolean isAllowedForUser(User user, Message message){
		List<User> list =  this.allowedMessagesDao.findAllAllowedUser(message);
		for(User u : list){
			if(u.getName().equals(user.getName())){
				return true;
			}
		}
		return false;
	}
}