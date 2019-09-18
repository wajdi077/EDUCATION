package com.dpc.Scolarity.Dto;

import java.util.Date;

import com.dpc.Scolarity.Domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgrammeEmploiDTO {
	private Long id ;
    private Jours jour;
    private Heures heure;
    private ClasseRoomDTO classe;
    private Utilisateur prof;
    private MatiereDTO matiere;
    private SalleDTO salle;
    private Date datedejour;
	private Date dateprog ;  
	private Date datefinprog;
}
