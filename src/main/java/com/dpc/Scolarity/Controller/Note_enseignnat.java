package com.dpc.Scolarity.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Note_enseignant;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AbsenceEleveDTO;
import com.dpc.Scolarity.Dto.NoteProfDTO;
import com.dpc.Scolarity.Dto.ProgrammeDTO;
import com.dpc.Scolarity.Repository.HeuresRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.JoursRepository;
import com.dpc.Scolarity.Repository.NoteEnseignantRepository;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.service.implementation.MainController;
import com.dpc.Scolarity.service.implementation.NoteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/note")
public class Note_enseignnat extends MainController{
	@Autowired
	NoteService noteservice ;
	@Autowired
	NoteEnseignantRepository noterepos ;
	@Autowired
	IUtilisateur userrepos ;
	@Autowired
	JoursRepository joursrepos ;
	@Autowired
	HeuresRepository heurerepos ;
	@Autowired
	ProgrammeRepository progrepos ;
	
	@RequestMapping(value = "/addnote", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody String addNote(Model model , @RequestBody NoteProfDTO notedto) {
		
		
		
		Programme p = this.progrepos.findById(notedto.getIdprogramme());
       // ProgrammeDTO pp = convertProgrammeToDto(p);
		
		Note_enseignant note =  dtoToNote(notedto);
		 note.setProgramme(p);
		 this.noterepos.save(note);
		// this.noteservice.AddNote(note);
			
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return "ok";
	}
	
	@RequestMapping(value = "/allnotes", method = RequestMethod.GET)

	public Collection<NoteProfDTO> getallnotes( ) {
		
		
		return this.noterepos.findAll().stream().map(note ->convertNoteToNoteDTO(note))
				.collect(Collectors.toList());


}
	
	
	
	
	

}
