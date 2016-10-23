package com.bai.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AllowedMessages", schema = "bai")
public class AllowedMessages implements Serializable {

	private static final long serialVersionUID = 8879335848354928908L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "am_id_seq")
	@SequenceGenerator(name = "am_id_seq", sequenceName = "am_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = User.class)
	private User user_id;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Message.class)
	private Message message_id;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Message getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Message message_id) {
		this.message_id = message_id;
	}

}
