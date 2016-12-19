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
@Table(name = "UnregisteredUser", schema = "bai")
public class UnregisteredUser implements Serializable{

	private static final long serialVersionUID = -7538946669429682339L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "unregisteredUser_id_seq")
	@SequenceGenerator(name = "unregisteredUser_id_seq", sequenceName = "unregisteredUser_id_seq", allocationSize = 1)
	@Column(name = "unregisteredUser_id", unique = true, nullable = false)
	@Getter @Setter private long nregisteredUser_id_seq;

	@Column(name = "name")
	@Getter @Setter private String name;
	
	@Column(name = "password_hash")
	@Getter @Setter private String password_hash;

	@Column(name = "salt")
	@Getter @Setter private int salt;

	@Column(name = "last_login")
	@Getter @Setter private Date last_login;
	
	@Column(name = "last_fail_login")
	@Getter @Setter private Date last_fail_login;
	
	@Column(name = "loginAttemptCounter")
	@Getter @Setter private int loginAttemptCounter;
	
	@Column(name = "loginAttempt")
	@Getter @Setter private int loginAttempt;
	
	@Column(name = "loginAttemptCounterToSucces")
	@Getter @Setter private int loginAttemptCounterToSucces;

	@Column(name = "accountLocked")
	@Getter @Setter private boolean accountLocked;
	
	@Column(name = "accountLoginBlocked")
	@Getter @Setter private Date accountLoginBlocked;
	
}
