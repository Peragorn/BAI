package com.bai.ps.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.User;


public class UserDao{

	public void addUser(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

	    session.save(user);
	    tx.commit();
	}

}
