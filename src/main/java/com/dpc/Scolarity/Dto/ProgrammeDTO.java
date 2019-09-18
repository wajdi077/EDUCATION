/**
 * 
 */
package com.dpc.Scolarity.Dto;

import java.util.Collection;

import javax.persistence.ManyToOne;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.SemaineAnneeScolaire;
import com.dpc.Scolarity.Domain.StatutProgramme;
import com.dpc.Scolarity.Domain.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author slim
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgrammeDTO {
private Long id ;

	private String nomclasse ;

	private String nomsalle ;
	private String nommatiere ;



	private String jour ;
	private Heures heure ;
	private String prof  ;
	private String typeprog;
	
	private String remarque ; 
	private String semaine;


}
