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
@Table(name = "Message", schema = "bai")
public class Message implements Serializable {

	private static final long serialVersionUID = 986334802113065160L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "message_id_seq")
	@SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
	@Column(name = "message_id", unique = true, nullable = false)
	@Getter @Setter private long message_id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_ids")
	@Getter @Setter private User user_id;

	@Column(name = "text")
	@Getter @Setter private String text;

	@Column(name = "mod")
	@Getter @Setter private String mod;

}
