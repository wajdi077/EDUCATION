package com.dpc.Scolarity.Dto;


import java.util.Date;

import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDirecteurDTO {
	private	int absence ;
	private	int exclu ;
	private	int avertisement ;
	private	int retenu ;
	private int penalites ;
	private Long id ;
    private Jours jour;
    private Heures heure;
    private ClasseRoomDTO classe;
    private Utilisateur prof;
    private MatiereDTO matiere;
    private SalleDTO salle;
	
	private Date dateprog ;  
	private Date datefinprog;
	boolean programmeverou ;
	

}
