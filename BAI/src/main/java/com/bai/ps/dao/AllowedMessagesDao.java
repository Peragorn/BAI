package com.bai.ps.dao;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.ReductionResult;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.AllowedMessages;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;

public class AllowedMessagesDao {

	public void addPermission(AllowedMessages allowedMessage) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        
        session.save(allowedMessage);
        tx.commit();
	}

	public void removePermission(AllowedMessages forbiddenMessage) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        
        session.delete(forbiddenMessage);
        tx.commit();
	}

	public long findPermissionId(Message message) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(AllowedMessages.class);
        if(message.getUser_id() != null){
        	criteria.add(Restrictions.eq("user_id", message.getUser_id().getUser_id()));
        }
        if(message.getMessage_id() > 0){
            criteria.add(Restrictions.eq("message_id", message.getMessage_id()));
        }
        
        criteria.uniqueResult();
        List<AllowedMessages> list = criteria.list();
        if(list.size() > 0){
            return list.get(0).getId();            
        }
        else{
            return 0;
        }
	}

	public List<User> findAllAllowedUser(Message message) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
	    List<User> userList = new ArrayList<User>();
	    
        Criteria criteria = session.createCriteria(AllowedMessages.class);
        if(message.getMessage_id() > 0){
        	criteria.add(Restrictions.eq("id", message.getMessage_id()));
        }
        List<AllowedMessages> list = (List<AllowedMessages>) criteria.list();
        for(AllowedMessages am : list){
        	userList.add(am.getUser_id());
        }
        return userList;
	}

}
