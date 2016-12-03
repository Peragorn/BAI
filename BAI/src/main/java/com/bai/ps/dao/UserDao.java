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

	public List<User> getUsersList() {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        
        List<User> userList = new ArrayList<User>();
        userList = criteria.list();
        
        session.close();
        return userList;
	}

	public User getUserByID(long id){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("user_id", id));
        User u = new User();
        u = (User) criteria.list().get(0);
        
        session.close();   
        return u;
	}
}
