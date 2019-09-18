package com.dpc.Scolarity.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.MatiereReferenciel;
import com.dpc.Scolarity.Domain.Niveau_Matiere;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.MatierReferencielRepository;
@Service
public class MatiereReferentielService extends MainController {
@Autowired
MatierReferencielRepository matierrepos ; 
	@Autowired 
	IUtilisateur iuser ; 
	public List<MatiereReferenciel> findAll() {

		return this.matierrepos.findAll();
	
	}
	
/*
	
	public void addeleve(Date datecreation, Long nbheures, String remarque,Long matiere_idmatiere,Long niveau_idniveau) {
		// TODO Auto-generated method stub
		
		Niveau_Matiere niveau_matiere= new Niveau_Matiere();
		
		eleve.setEtat(etat);
		eleve.setClasseActuel(classeactulle);
		eleve.setDateNaissance(datenaissonce);
		eleve.setNiveauEtude(niveau);	
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		eleve.setRemarque(remarque);
		eleve.setSituationFamiliale(situation);
		eleve.setSexe(sexe);
		eleve.setNumInscription(numinsc);
		eleve.setDateInscription(new Date(System.currentTimeMillis()));
		String a = eleve.getSexe().substring(0,1); 
		Eleve e = this.eleverepository.save(eleve);
		Long id = e.getId();
		String ids = id.toString();
		e.setDateInscription(new Date(System.currentTimeMillis()));
		//e.setNumInscription(a+ids);
		 this.eleverepository.save(e);
	}
*/	
}
