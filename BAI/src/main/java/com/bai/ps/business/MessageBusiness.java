package com.bai.ps.business;

import java.util.List;

import com.bai.ps.dao.MessageDao;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;

public class MessageBusiness {

	public MessageDao messageDao = new MessageDao();
	
	public List<Message>getUsersMessages(){
		return this.messageDao.getUsersMessages();
	}
	
	public void addMessge(Message message, User user){
		if(user != null){
			this.messageDao.addMessage(message);			
		}
		else{
			System.out.println("Brak zalogowanego uzytkownika");
		}
	}
	
	/**
	 * Usuwa wiadomosc, wczesniej sprawdzaja czy zalogowany uzytkownik jest jej autorem
	 * @param messageToRemove wiadomosc do usuniecia
	 * @param user musi byc autorem wiadomosci
	 */
	public void removeMessage(Message messageToRemove, User user){
		if(user.getName().equals(getMessageAuthor(messageToRemove).getName())){
			this.messageDao.removeMessage(messageToRemove);
		}
	}
	
	/**
	 * Metoda zwraca autora wiadomosci
	 * @param message 
	 * @return autor wiadomosci
	 */
	public User getMessageAuthor(Message message){
		if(message.getUser_id() != null){
			return this.messageDao.getMessageAuthor(message);
		}
		return message.getUser_id();
	}
	
	public void editMessage(User user, Message messageToEdit){
		AllowedMessagesBusiness allowed = new AllowedMessagesBusiness();
		if(getMessageAuthor(messageToEdit).getName().equals(user.getName()) || allowed.isAllowedForUser(user, messageToEdit)){
			this.messageDao.editMessage(messageToEdit);
		}
	}
	
	public List<Message>getUserMessages(User user){
		return this.messageDao.getUserMessages(user);
	}
}
