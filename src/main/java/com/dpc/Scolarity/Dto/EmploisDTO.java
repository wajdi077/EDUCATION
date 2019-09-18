/**
 * 
 */
package com.dpc.Scolarity.Dto;

import java.util.Date;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Salle;
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
public class EmploisDTO {
	
	private Long id ;
	private String typeprog;
	private MatiereDTO matiere ;
	private SemaineDTO semaine ;
	private SalleDTO salle ;
	private ClasseRoomDTO classe ;
	private JoursDTO jour ;
	private HeuresDTO heure ;
	private Date dateprog ;  
	private Date datefinprog;
	private String start ;
	private String end ;
	private UserDTO prof;
	//@author Adleni: pour Emploi de temps d'Eleve.
	private UserDTO user;
}
