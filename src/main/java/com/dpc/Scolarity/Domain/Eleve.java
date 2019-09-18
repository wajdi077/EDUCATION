package com.dpc.Scolarity.Domain;

import java.io.Serializable;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Eleve implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String numInscription;
	private String  nom;
	private String prenom ;
	private String remarque ;
	@OneToMany(mappedBy="eleves",fetch=FetchType.LAZY)
	private Collection<Penalites> penals ;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance ;
	@Temporal(TemporalType.DATE)
	private  Date dateInscription ;
	private String etat ;
	private String sexe ;
	private String situationFamiliale ;
	private String photo ;
	private String niveauEtude ; 
	private Long cin ;
	private String classeActuel ;
	private Boolean archiver ;
	private String ancien_etab;
	private String ancien_classe;
	private Boolean presence;
	
	@ManyToOne
	private Etablissement etablissement ; 
	
	 @ManyToOne
	private Utilisateur parrain ;
	 
	 @OneToMany(mappedBy="e",fetch=FetchType.LAZY)
	private Collection<AbsencesEleve> mesActions ;
	
		@OneToMany(mappedBy="eleve",fetch=FetchType.LAZY)
		private Collection<Classeleves> classedesseleves ; 

	
	
	
	
	
}
