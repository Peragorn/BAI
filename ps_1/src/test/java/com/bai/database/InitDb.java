package com.bai.database;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bai.model.AllowedMessages;
import com.bai.model.Message;
import com.bai.model.User;



public class InitDb {
	public static void init() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		User user = new User();
		user.setName("Damian");
		user.setPassword_hash("damian");
		user.setSalt("damian".hashCode());
		user.setLast_login(new Date());
		session.save(user);
		
		User user1 = new User();
		user1.setName("Michał");
		user1.setPassword_hash("michał");
		user1.setSalt("michał".hashCode());
		user1.setLast_login(new Date());
		session.save(user1);
		
		User user2 = new User();
		user2.setName("Karolina");
		user2.setPassword_hash("karolina");
		user2.setSalt("karolina".hashCode());
		user2.setLast_login(new Date());
		session.save(user2);
		
		User user3 = new User();
		user3.setName("Andrzej");
		user3.setPassword_hash("andrzej");
		user3.setSalt("andrzej".hashCode());
		user3.setLast_login(new Date());
		session.save(user3);
		
		Message message = new Message();
		message.setUser_id(user);
		message.setText("Witaj Damian");
		message.setMod("");
		session.save(message);
		
		Message message1 = new Message();
		message.setUser_id(user1);
		message.setText("Witaj Michał");
		message.setMod("");
		session.save(message1);
		
		Message message2 = new Message();
		message.setUser_id(user2);
		message.setText("Witaj Karolina");
		message.setMod("");
		session.save(message2);
		
		Message message3 = new Message();
		message.setUser_id(user3);
		message.setText("Witaj Andrzej");
		message.setMod("");
		session.save(message3);
		
		AllowedMessages allowedMessages = new AllowedMessages();
		allowedMessages.setUser_id(user);
		allowedMessages.setMessage_id(message);
		session.save(allowedMessages);
		
		AllowedMessages allowedMessages1 = new AllowedMessages();
		allowedMessages.setUser_id(user1);
		allowedMessages.setMessage_id(message1);
		session.save(allowedMessages1);
		
		AllowedMessages allowedMessages2 = new AllowedMessages();
		allowedMessages.setUser_id(user2);
		allowedMessages.setMessage_id(message2);
		session.save(allowedMessages2);
		
		AllowedMessages allowedMessages3 = new AllowedMessages();
		allowedMessages.setUser_id(user3);
		allowedMessages.setMessage_id(message3);
		session.save(allowedMessages3);


		tx.commit();
		System.out.println("DB data initialized");
	}
	
	public static void main(String[] args) {
		init();
		System.exit(0);
	}
}
