package com.dpc.Scolarity.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Note_enseignant;
import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AbsenceEleveDTO;
import com.dpc.Scolarity.Dto.NoteProfDTO;
import com.dpc.Scolarity.Dto.UserDTO;
import com.dpc.Scolarity.Repository.HeuresRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.JoursRepository;
import com.dpc.Scolarity.Repository.NoteEnseignantRepository;
import com.dpc.Scolarity.Repository.ProgrammeRepository;

@Service
public class NoteService extends MainController {
	
	@Autowired
	ProgrammeRepository progrepos ;
	@Autowired
	NoteEnseignantRepository noterepos ;
	@Autowired
  IUtilisateur userrepository ;
	@Autowired
	JoursRepository jourrepository ;
	@Autowired
	HeuresRepository  heurerepo ;
	
	public void AddNote (Note_enseignant note) {
		
		
		/*Utilisateur u = new Utilisateur();
		u = this.userrepository.findByUsername(username);
		System.out.println("user"+u.getPassword());

		UserDTO prof = convertUtilisateurToDto(u);
		Jours day = new Jours();
		day = this.jourrepository.findByJour(jour);
		System.out.println("day"+day);

		Heures heures = new Heures();
		heures = this.heurerepo.findByHeureDebut(heure);
		System.out.println("heures"+heures);
*/
		
		//Programme p = this.progrepos.findById(id);
			 //Programme pp = this.progrepos.findByProfAndJourAndHeure("ens36", 1, 6);
		//System.out.println("programme"+p.getId());
	//	System.out.println("PROGRAMME"+p);

		//note.setProgramme(p);
		System.out.println("note"+note);

		this.noterepos.save(note);
		
		
	}
	
	


}
