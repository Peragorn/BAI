package com.bai.ps.database;

import java.util.Date;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bai.ps.model.AllowedMessages;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;
import com.bai.ps.model.UserPasswordMask;

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
		user1.setName("Michal");
		user1.setPassword_hash("mioduszewski");
		user1.setSalt("mioduszewski".hashCode());
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
		
		String mask = generateMask(user1.getPassword_hash());
		int salt = generateSalt();
		
		UserPasswordMask userPasswordMask = new UserPasswordMask();
		userPasswordMask.setUser_id(user1);
		userPasswordMask.setMask(mask);
		userPasswordMask.setActive(true);
		userPasswordMask.setSalt(salt);
		userPasswordMask.setPassword_hash(createHashForMaskAndSalt(mask,user1.getPassword_hash(),salt));
		session.save(userPasswordMask);
		
		for(int i=0; i<9; i++){
			String mask2 = generateMask(user1.getPassword_hash());
			int salt2 = generateSalt();
			
			UserPasswordMask userPasswordMask2 = new UserPasswordMask();
			userPasswordMask2.setUser_id(user1);
			userPasswordMask2.setMask(mask2);
			userPasswordMask2.setActive(false);
			userPasswordMask2.setSalt(salt2);
			userPasswordMask2.setPassword_hash(createHashForMaskAndSalt(mask2,user1.getPassword_hash(),salt2));
			session.save(userPasswordMask2);
		}
		
		Message message = new Message();
		message.setUser_id(user);
		message.setText("Witaj Damian");
		message.setMod("");
		session.save(message);
		
		Message message1 = new Message();
		message1.setUser_id(user1);
		message1.setText("Witaj Michal");
		message1.setMod("");
		session.save(message1);
		
		Message message2 = new Message();
		message2.setUser_id(user2);
		message2.setText("Witaj Karolina");
		message2.setMod("");
		session.save(message2);
		
		Message message3 = new Message();
		message3.setUser_id(user3);
		message3.setText("Witaj Andrzej");
		message3.setMod("");
		session.save(message3);
		
		AllowedMessages allowedMessages = new AllowedMessages();
		allowedMessages.setUser_id(user);
		allowedMessages.setMessage_id(message);
		session.save(allowedMessages);
		
		AllowedMessages allowedMessages1 = new AllowedMessages();
		allowedMessages1.setUser_id(user1);
		allowedMessages1.setMessage_id(message1);
		session.save(allowedMessages1);
		
		AllowedMessages allowedMessages2 = new AllowedMessages();
		allowedMessages2.setUser_id(user2);
		allowedMessages2.setMessage_id(message2);
		session.save(allowedMessages2);
		
		AllowedMessages allowedMessages3 = new AllowedMessages();
		allowedMessages3.setUser_id(user3);
		allowedMessages3.setMessage_id(message3);
		session.save(allowedMessages3);


		tx.commit();
		System.out.println("DB data initialized");
	}
	
	private static String generateMask(String pwd) {

		StringBuilder result = new StringBuilder("0000000000000000");

		Random rnd = new Random();
		int length = pwd.length() / 2;

		if (length > 5) {

			int lengthMask = 0;
			do {
				lengthMask = rnd.nextInt(length + 1);
			} while (lengthMask < 5);

			for (int j = 0; j < lengthMask; j++) {
				int charPostion = 0;
				do {
					charPostion = rnd.nextInt(pwd.length());
				} while (result.charAt(charPostion) == '1');
				
				result.setCharAt(charPostion, '1');
			}
		} else {
			for (int j = 0; j < 5; j++) {
				int charPostion = 0;
				do {
					charPostion = rnd.nextInt(pwd.length());
				} while (result.charAt(charPostion) == '1');
				result.setCharAt(charPostion, '1');
			}
		}
		return result.toString();
	}
	
	private static String createHashForMaskAndSalt(String mask, String pass, int salt) {
		
		StringBuilder result = new StringBuilder("");
		
		for(int i=0; i< mask.length(); i++){
			if(mask.charAt(i) == '1'){
				result.append(pass.charAt(i));
			}
		}
		result.append(salt);
	
		return Integer.toString(result.toString().hashCode());
	}
	
	private static int generateSalt(){
		Random rnd = new Random();
		return rnd.nextInt(100);
	}
	
	public static void main(String[] args) {
		init();
		System.exit(0);
	}
}
