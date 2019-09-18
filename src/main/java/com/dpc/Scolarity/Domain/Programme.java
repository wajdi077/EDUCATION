package com.dpc.Scolarity.Domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Programme {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;

	private String typeprog;
	@ManyToOne 
	private Matiere matiere ;
	
	private String statut ;
	private Boolean archiver ;
	@ManyToOne 
	private Utilisateur prof  ;
	@ManyToOne
	private Salle salle ;
	@ManyToOne
	private Classes classe ;
	@ManyToOne
    private Jours jour ;
	@ManyToOne
	private Heures heure ;
	
	@Temporal(TemporalType.DATE)
	private Date datedejour ;
	@ManyToOne
	@JsonIgnore
	private SemaineAnneeScolaire semaineAnneeScolaire ;
	@ManyToOne
	@JsonIgnore
	private Semaine semaine ;
	@OneToMany(mappedBy="programme",fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<AbsencesEleve> actioneleves ; 
	

	


	
	private Date dateprog ;  
	private Date datefinprog;
	private Boolean verou ;

}
