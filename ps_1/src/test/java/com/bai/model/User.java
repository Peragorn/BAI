package com.bai.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "User", schema = "bai")
public class User implements Serializable {

	private static final long serialVersionUID = -2156173354481673517L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id", unique = true, nullable = false)
	private long user_id;

	@Column(name = "name")
	private String name;

	@Column(name = "password_hash")
	private String password_hash;

	@Column(name = "salt")
	private int salt;

	@Column(name = "last_login")
	private Date last_login;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public int getSalt() {
		return salt;
	}

	public void setSalt(int i) {
		this.salt = i;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
