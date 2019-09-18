package com.dpc.Scolarity.service.implementation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Anneescolaire;
import com.dpc.Scolarity.Domain.Classeleves;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Dto.AnneeScolaireDTO;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;
import com.dpc.Scolarity.Dto.ClassesDesEleveDTO;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Repository.*;
@Service
public class ClasseDesEleveService extends MainController {
	@Autowired
	ClassesRepository classerepository ; 
	@Autowired
	EleveRepository eleverepository ; 
	@Autowired 
	AnneeScolaireRepository anneescolairerepository ;
	@Autowired
	ClasseDesElevesRepository classesdeselevesRepository ;
	
	// fonction d'affectation des eleves à leur classes 
	public void AddEleveToClasses (List<ClassesDesEleveDTO> classedeselevedto) {
	
		for(int i=0; i<classedeselevedto.size();i++) {
			Classeleves classeDesEleves = dtoToClasseDesEleves(classedeselevedto.get(i));
			//Annee_scolaire  anneeScolaire = anneescolairerepository.findByAnneeScolaire(classeDesEleves.getAnneeScolaire().getAnneeScolaire());
			//Anneescolaire  anneeScolaires = anneescolairerepository.findByAnneeScolaire("2016-2017");

			Eleve eleve = eleverepository.findByNomAndPrenom(classeDesEleves.getEleve().getNom(),	classeDesEleves.getEleve().getPrenom() );
			//Classes myclass = classerepository.findByNomclasse(classeDesEleves.getMyclass().getNomclasse());
		
			//classeDesEleves.setEleve(eleve);
			//classeDesEleves.setMyclass(myclass);
			//classeDesEleves.setAnneeScolaire(anneeScolaires);
			classeDesEleves.setStatutEleve("en cours d'education");
			Classeleves classesdeselevesCreated = this.classesdeselevesRepository.save(classeDesEleves);
		
		}
	
	
	}
	// fonction qui retourne la liste des classes par annee scolaire 
	public Collection<ClassesDesEleveDTO> findAllbyAnneeScolaire(AnneeScolaireDTO anneescolaireDTO) {
		
		Anneescolaire anneescolaires = dtoToAnnee_scolaire(anneescolaireDTO);
		Anneescolaire anneescolaire = this.anneescolairerepository.findByAnneeScolaire(anneescolaires.getAnneeScolaire());

		return this.classesdeselevesRepository.findByAnneeScolaire(anneescolaire).stream().map(entity -> convertClassesDesEleveToDto(entity)).collect(Collectors.toList());
	
	}
	// fonction qui retourne la liste des eleves par classe et l'annee scolaire dedié 
	public Collection<ClassesDesEleveDTO> ListDesElevesByClassesEstAnneeScolaire(ClasseRoomDTO classdto,AnneeScolaireDTO anneescolaireDTO) {
		Classes classes = dtoToClasses(classdto);
		Classes monclass = this.classerepository.findByNomclasse(classes.getNomclasse());
		Anneescolaire anneescolaires = dtoToAnnee_scolaire(anneescolaireDTO);
		Anneescolaire anneescolaire = this.anneescolairerepository.findByAnneeScolaire(anneescolaires.getAnneeScolaire());

		return this.classesdeselevesRepository.findByMyclassAndAnneeScolaire(monclass,anneescolaire).stream().map(entity -> convertClassesDesEleveToDto(entity)).collect(Collectors.toList());
	
	}
	
	
	
	public void AddEleveToClasses2 (Classes classdto,List<String> elevedto) {
	
		Eleve eleveAjouter ;
		Classes myclass ;
		
		Classes c = this.classerepository.findByNomclasse(classdto.getNomclasse());
		//Anneescolaire  anneeScolaires = anneescolairerepository.findByAnneeScolaire("2016-2017");
		//classeDesEleves.setAnneeScolaire(anneeScolaires);
		String a = "en cours d'education";
		
		for(int i=0; i<elevedto.size();i++) {
			eleveAjouter  = null ;
			
		if (elevedto.get(i)!=null) {
			Classeleves classeDesEleves = new Classeleves();
			eleveAjouter = this.eleverepository.findByNumInscription(elevedto.get(i));
			classeDesEleves.setEleve(eleveAjouter);
			classeDesEleves.setStatutEleve("en cours d'education");
			classeDesEleves.setMyclass(c);
			eleveAjouter.setClasseActuel(c.getNomclasse());
			eleveAjouter = eleverepository.save(eleveAjouter);
			Classeleves classesdeselevesCreated = this.classesdeselevesRepository.save(classeDesEleves);
			//Annee_scolaire  anneeScolaire = anneescolairerepository.findByAnneeScolaire(classeDesEleves.getAnneeScolaire().getAnneeScolaire());
			

		}
		
			
		
		}
	
	
	
	}

	
	
	// fonction d'affectation des eleves à leur classes 
	/*public void AddEleveToClasses3 (List<ClassesDesEleveDTO> classedeselevedto) {
		Eleve eleveAjouter ;
	
	//	Classes myclass = dtoToClasses(classdto);
		Classes c ;
		Anneescolaire  anneeScolaires = anneescolairerepository.findByAnneeScolaire("2016-2017");
		Classeleves classeDesEleves = new Classeleves();
		
		classeDesEleves.setAnneeScolaire(anneeScolaires);
		classeDesEleves.setStatutEleve("en cours d'education");
		for(int i=0; i<classedeselevedto.size();i++) {
			eleveAjouter  = null ;

			c  = null ; 
			
			c =  this.classerepository.findByNomclasse(classedeselevedto.get(i).getMyclass().getNomclasse());
			classeDesEleves.setMyclass(c);
	if ( eleveAjouter!=null) {
				
				classeDesEleves.setEleve(eleveAjouter);
				Classeleves classesdeselevesCreated = this.classesdeselevesRepository.save(classeDesEleves);
				
			}
		
		}
	
	
	}
	*/
}
