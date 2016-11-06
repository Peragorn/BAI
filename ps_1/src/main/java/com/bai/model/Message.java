package com.bai.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Message", schema = "bai")
public class Message implements Serializable {

	private static final long serialVersionUID = 986334802113065160L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "message_id_seq")
	@SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
	@Column(name = "message_id", unique = true, nullable = false)
	private long message_id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_ids")
	private User user_id;

	@Column(name = "text")
	private String text;

	@Column(name = "mod")
	private String mod;

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

}