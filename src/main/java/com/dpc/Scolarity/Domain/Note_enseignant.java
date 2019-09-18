package com.dpc.Scolarity.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Note_enseignant implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idnote ;
	
	@Temporal(TemporalType.DATE)
	private Date datecreation;
	@Temporal(TemporalType.DATE)
	private Date  datenotification;
	
	private 	String  time;

	private String description;
	private String remarque;
	private String titre;

	
	@OneToOne(
	        cascade = CascadeType.ALL)	
	private Programme programme ;
	 

}
