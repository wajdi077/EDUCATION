package com.dpc.Scolarity.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Repository.NiveauRepository;

@Service
public class MotifService extends MainController {
	@Autowired 
	NiveauRepository niveaurepos ; 
	/*
	public List<Object>  getNiveauByMotif(String niveau){
		List<Object>  niveauDTO = new ArrayList<>();
		Niveau eleve = this.niveaurepos.findByNiveau(niveau);
		niveauDTO = this.penalitesrepo.getEleveByMotif(eleve);
		return eleveDetailsDTO;
	}
*/


}
