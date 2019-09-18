package com.dpc.Scolarity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.NiveauUser;
import com.dpc.Scolarity.Domain.Niveau_etablissement;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.EtablissementRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.NiveauEtabRepository;
import com.dpc.Scolarity.Repository.NiveauRepository;
import com.dpc.Scolarity.Repository.NiveauUserRepository;

@Service
public class NiveauService {
	
	 @Autowired 
	 NiveauUserRepository repos ;
	 @Autowired
	 private IUtilisateur iUtilisateur ; 
	@Autowired
	private NiveauEtabRepository niveauetabrepos ; 
	@Autowired
	NiveauRepository niveauRepository ; 
	@Autowired
	EtablissementRepository etablissementRepository ;
	
	
	public List<Niveau> getallniveau() {

	    return (List<Niveau>)niveauRepository.findAll();  
	}

	//affecter nivea to enseignant
public void Affecterniveau (Long id,List<String> nomniveau) {
		
		Niveau niveauAjouter ;
		Utilisateur u ;
		NiveauUser us ; 
		for(int i=0; i<nomniveau.size();i++) {
			niveauAjouter  = null ;
        us = new NiveauUser();
		Utilisateur user = this.iUtilisateur.findById(id);
if (nomniveau.get(i)!=null) {
			niveauAjouter = this.niveauRepository.findBynomniveau(nomniveau.get(i));
			us.setEnseignant(user); 
			us.setNiveau(niveauAjouter);
			us=this.repos.save(us);
			user.setNiveau(niveauAjouter);			
			
			 iUtilisateur.save(user);}}}



//affecter niveau to etablissement
public void Affecterniveautoetablissement (String username ,List<String> nomniveau) {
	
	Niveau niveauAjouter ;
	Niveau_etablissement us ; 
	for(int i=0; i<nomniveau.size();i++) {
		niveauAjouter  = null ;
    us = new Niveau_etablissement();
	
    Utilisateur userconncete = iUtilisateur.findByUsername(username);
  Etablissement etab=userconncete.getEtablissement();
    
    
if (nomniveau.get(i)!=null) {
		niveauAjouter = this.niveauRepository.findBynomniveau(nomniveau.get(i));
		us.setEtablissement(etab); 
		us.setNiveau(niveauAjouter);
		niveauetabrepos.save(us);}}}





}
		



