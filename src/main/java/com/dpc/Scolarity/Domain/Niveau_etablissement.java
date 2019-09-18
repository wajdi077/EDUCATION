package com.dpc.Scolarity.Domain;

import java.util.Date;
import java.util.List;

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
public class Niveau_etablissement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Temporal(TemporalType.DATE)
	private Date datecreation;
	private Boolean archiver =false ;
	 @ManyToOne		
	 private Niveau niveau ;
	 @ManyToOne		
	 private Etablissement etablissement ;
	public void setUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		
	}
	
}
