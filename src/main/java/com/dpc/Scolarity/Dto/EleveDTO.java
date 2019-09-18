package com.dpc.Scolarity.Dto;

import java.util.Date;

import com.dpc.Scolarity.Domain.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleveDTO {
	
	private Long id ;
	private String  nom;
	private String prenom ;
	private String remarque ;
	
	private Date dateNaissance ;

	//private  Date dateInscription; 
	private String etat ;
	private String sexe ;
	private String situationFamiliale ;
	private String Photo ;
	private Long cin ;
	private String niveauEtude ; 
	private String numInscription;
	
	private String classeActuel ;
	private String username;
	private EtablissementDTO etablissement ;
	private EleveParrainDTO eleveParrain;
	
}
