package com.bai.ps.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;

public class MessageDao {

	public List<Message> getUsersMessages() {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Message.class);
        
        List<Message> messageList = new ArrayList<Message>();
        messageList = criteria.list();
        
        session.close();   
        return messageList;
	}

	public void addMessage(Message message){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        
        session.save(message);
        tx.commit();
	}

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
        if(list.size() > 0){
            return list.get(0).getUser_id();            
        }
        else{
            return null;
        }
	}

	public void removeMessage(Message messageToRemove) {
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
}
