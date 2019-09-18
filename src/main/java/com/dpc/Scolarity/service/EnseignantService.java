package com.dpc.Scolarity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.EnseignantRepository;

@Service
public class EnseignantService {

	@Autowired 
	public EnseignantRepository enseignant ; 
	//fct pour modifier le contenue de l'entité utilisateur
	   public Utilisateur update(Utilisateur utilisateur) {

		   enseignant.saveAndFlush(utilisateur); //ajouter les employes d'une maniere asynchrone
	       return utilisateur;
	   }

	
}