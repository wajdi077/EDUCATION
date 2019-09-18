package com.dpc.Scolarity.Domain;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author slim
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	
	private Boolean archiver ;
	private Date date_message ;
	
	private String message ;
	private String sujet ;
	@ManyToOne
	private Utilisateur sender ;
	@ManyToOne
	private Utilisateur reciever ;
	

	public Message(Date date_message, String message, String sujet, Utilisateur sender, Utilisateur reciever) {
		super();
		this.date_message = date_message;
		message = message;
		sujet = sujet;
		this.sender = sender;
		this.reciever = reciever;
	}
	public Message(Date date_message, String message, Utilisateur sender, Utilisateur reciever) {
		super();
		this.date_message = date_message;
		message = message;
		this.sender = sender;
		this.reciever = reciever;
	}
	public Message(Long id, Date date_message, String message, Utilisateur sender, Utilisateur reciever) {
		super();
		this.id = id;
		this.date_message = date_message;
		message = message;
		this.sender = sender;
		this.reciever = reciever;
	}
	
	
	
	
}
