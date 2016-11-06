package com.bai.ps.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.User;


public class UserDao{

	public void addUser(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

	    session.save(user);
	    tx.commit();
	}

	public User login(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", user.getName()));
        criteria.add(Restrictions.like("password_hash", user.getPassword_hash()));
        
        List<User> list = criteria.list();
        if(list.size() > 0){
            user.setUser_id(list.get(0).getUser_id());
            user.setLast_login(list.get(0).getLast_login());
            user.setSalt(list.get(0).getSalt());
            session.close();
            return user;            
        }
        else{
            return null;
        }
	}

}
