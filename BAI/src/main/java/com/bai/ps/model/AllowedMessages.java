package com.bai.ps.model;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AllowedMessages", schema = "bai")
public class AllowedMessages implements Serializable {

	private static final long serialVersionUID = 8879335848354928908L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "am_id_seq")
	@SequenceGenerator(name = "am_id_seq", sequenceName = "am_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	@Getter @Setter private long id;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = User.class)
	@Getter @Setter private User user_id;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Message.class)
	@Getter @Setter private Message message_id;

}
