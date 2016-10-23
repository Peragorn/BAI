package com.bai.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bai.database.HibernateUtil;
import com.bai.model.User;


public class UserDao{

	public void addUser(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

	    session.save(user);
	    tx.commit();
	}

}
