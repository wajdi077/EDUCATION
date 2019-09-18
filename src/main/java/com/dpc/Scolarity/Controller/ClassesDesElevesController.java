package com.dpc.Scolarity.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AnneeScolaireDTO;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;
import com.dpc.Scolarity.Dto.ClassesDesEleveDTO;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.service.implementation.ClasseDesEleveService;
import com.dpc.Scolarity.service.implementation.ClasseRoomService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author slim
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/classedeseleves", produces = MediaType.APPLICATION_JSON_VALUE)

public class ClassesDesElevesController {
	

	@Autowired
	ClasseDesEleveService classesdeselevesservice ;
	@Autowired
	ClasseRoomService classeservice ;
	@Autowired
	EleveRepository eleverepository ;
	
	
	
	@RequestMapping(value = "/allelevebyclasse", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<ClassesDesEleveDTO> getAllEleveByClassesAndAnneeScolaire(@RequestBody ClasseRoomDTO classdto ,@RequestBody AnneeScolaireDTO anneescolaireDTO) {
		return this.classesdeselevesservice.ListDesElevesByClassesEstAnneeScolaire(classdto, anneescolaireDTO);

}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody String addStudentToClassestest1(Model model , String nomclasse ,@RequestBody List<String> numinscription) {
		//String nomclasse  ="7emme B";
		Classes c = this.classeservice.findByNomClasses(nomclasse);
		this.classesdeselevesservice.AddEleveToClasses2(c, numinscription);
		
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return "ok";
	}
	

}
