package com.bai.ps.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "User", schema = "bai")
public class User implements Serializable {

	private static final long serialVersionUID = -2156173354481673517L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id", unique = true, nullable = false)
	@Getter @Setter private long user_id;

	@Column(name = "name")
	@Getter @Setter private String name;

	@Column(name = "password_hash")
	@Getter @Setter private String password_hash;

	@Column(name = "salt")
	@Getter @Setter private int salt;

	@Column(name = "last_login")
	@Getter @Setter private Date last_login;

}
