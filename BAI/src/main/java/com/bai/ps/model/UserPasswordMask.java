package com.bai.ps.model;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserPasswordMask", schema = "bai")
public class UserPasswordMask implements Serializable{

	private static final long serialVersionUID = -5643327274414074620L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userPasswordMask_id_seq")
	@SequenceGenerator(name = "userPasswordMask_id_seq", sequenceName = "userPasswordMask_id_seq", allocationSize = 1)
	@Column(name = "userPasswordMask_id", unique = true, nullable = false)
	@Getter @Setter private long userPasswordMask_id;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_ids")
	@Getter @Setter private User user_id;
	
	@Column(name = "mask")
	@Getter @Setter private String mask;
	
	@Column(name = "password_hash")
	@Getter @Setter private String password_hash;
	
	@Column(name = "salt")
	@Getter @Setter private int salt;
	
	@Column(name = "active")
	@Getter @Setter private boolean active;
}
