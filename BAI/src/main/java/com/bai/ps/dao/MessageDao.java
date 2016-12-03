package com.bai.ps.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.AllowedMessages;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;

public class MessageDao {

	@Deprecated
	public List<Message> getUsersMessages() {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Message.class);
        
        List<Message> messageList = new ArrayList<Message>();
        messageList = criteria.list();
        
        session.close();   
        return messageList;
	}

	/**
	 * Metoda dodaje wiadomosc do tabeli Message
	 * @param message wiadomosc do dodania
	 */
	public void addMessage(Message message){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        
        session.save(message);
        tx.commit();
	}

	/**
	 * Metoda do pobrania uzytkownika ktory stworzy te wiadomosc
	 * @param message wiadomosc do spawdzenia autora
	 * @return zwraca autora wiadomosci
	 */
	public User getMessageAuthor(Message message) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        if(message.getMessage_id() > 0){
        	criteria.add(Restrictions.eq("message_id", message.getMessage_id()));
        }
        if(message.getText() != null){
            criteria.add(Restrictions.like("text", message.getText()));
        }
        
        criteria.uniqueResult();
        List<Message> list = criteria.list();
        session.close();
        if(list.size() > 0){
            return list.get(0).getUser_id();            
        }
        else{
            return null;
        }
	}

	/**
	 * Metoda usuwa wiadomosc, wczesniej usuwa wszystkie uprawnienia z tabeli AllowedMessages
	 * @param messageToRemove wiadomosc do usuniecia
	 */
	public void removeMessage(Message messageToRemove) {
        messageToRemove = findMessage(messageToRemove);
        AllowedMessagesDao allowedMessagesDao = new AllowedMessagesDao();
        AllowedMessages allowedMessage = new AllowedMessages();
    	for(AllowedMessages am : allowedMessagesDao.findPermissionIds(messageToRemove)){
    		allowedMessage.setId(am.getId());
    		allowedMessage.setUser_id(am.getUser_id());
    		allowedMessage.setMessage_id(am.getMessage_id());
    		allowedMessagesDao.removePermission(allowedMessage);
    	}
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction(); 
		session.delete(messageToRemove);
		tx.commit(); 
	}

	public void editMessage(Message messageToEdit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        
        session.update(messageToEdit);
        tx.commit();
	}

	/**
	 * Metoda pobiera wszystkie wiadomosc napisane przez zalogowanego uzytkownika oraz te do ktorych ma uprawnienia
	 * @param user zalogowany uzytkownik
	 * @return lista wiadomosci zalogowanego uzytkownika
	 */
	//TODO dopisac pobieranie takze wiadomosci do ktorych uzytkownik ma uprawnienia
	public List<Message> getUserMessages(User user) {
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    Transaction tx = session.beginTransaction();
//        Criteria criteria = session.createCriteria(Message.class);
//        criteria.add(Restrictions.eq("user_id", user));
//        List<Message> messageList = new ArrayList<Message>();
//        messageList = criteria.list();
//        
//        session.close();   
		AllowedMessagesDao amd = new AllowedMessagesDao();
        return amd.findAllMessageForUser(user);
	}
	/**
	 * Metoda znajduje wiadomosc 
	 * @param message
	 * @return
	 * @see problemy z serializacja i zmiennym id
	 */
	public Message findMessage(Message message){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        criteria.add(Restrictions.eq("user_id", message.getUser_id()));
        criteria.add(Restrictions.eq("text", message.getText()));
        criteria.add(Restrictions.eq("message_id", message.getMessage_id()));
        criteria.uniqueResult();
        Message msg = new Message();
        List<Message> list = criteria.list();
        if(list.size() > 0){
            msg =  list.get(0);            
        }
        session.close();
        
        return msg;
	}
	
	public Message getMessageByID(long id){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        criteria.add(Restrictions.eq("message_id", id));
        Message messageList = new Message();
        messageList = (Message) criteria.list().get(0);
        
        session.close();   
        return messageList;
	}
	
}
