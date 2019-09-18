package com.dpc.Scolarity.Dto;

import java.util.Date;
import java.util.List;

import com.dpc.Scolarity.Domain.Anneescolaire;
import com.dpc.Scolarity.Domain.Classeleves;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor

@Data
public class ClassesDesEleveDTO {
	private AnneeScolaireDTO anneeScolaire ;

	private List<String> numInscription ;
	private String nomclasse ;;
	private String statutEleve ;
}
