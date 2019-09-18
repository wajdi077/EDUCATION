package com.dpc.Scolarity.Dto;





import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalleDTO {
	private Long idsalle ;
	private String nomSalle;
	private String type ;
	private String remarque ;
private EtablissementDTO etablissement;
private String username;
}
