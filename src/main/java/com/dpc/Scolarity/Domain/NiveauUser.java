package com.dpc.Scolarity.Domain;

import java.io.Serializable;
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
public class NiveauUser implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Temporal(TemporalType.DATE)
	private Date datecreation;
	private Long nombreHeureParMatiere;
	 @ManyToOne		
	 private Niveau niveau ;
	 @ManyToOne		
	 private Utilisateur enseignant ;
	 
		

}
