package com.dpc.Scolarity.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Niveau_Matiere {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	private Double nbheures;
	private String remarque;
	
	@Temporal(TemporalType.DATE)
	private Date datecreation;
	@ManyToOne		
	 private Niveau niveau ;

	@ManyToOne		
	 private Matiere matiere ;

	@ManyToOne		
	 private Specialite specialite ;
}
