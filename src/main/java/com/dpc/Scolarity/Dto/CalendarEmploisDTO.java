package com.dpc.Scolarity.Dto;

import java.util.Date;

import lombok.Data;

/**
 * @author slim
 *
 */
@Data
public class CalendarEmploisDTO {

	private Long id ;
	private String typeprog;
	private MatiereDTO matiere ;
	private SemaineDTO semaine ;
	private SalleDTO salle ;
	private ClasseRoomDTO classe ;
	private JoursDTO jour ;
	private HeuresDTO heure ;
	private String dateprog ;  
	private String datefinprog;
	//@author Adleni: pour Emploi de temps d'Eleve.
	private UserDTO user;
}
