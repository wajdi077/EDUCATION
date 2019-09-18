package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.Niveau_Matiere;
import com.dpc.Scolarity.Repository.NombreheureRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/nbrheure")
public class NombreheureController {
	@Autowired 
	NombreheureRepository nbrrepos ; 
	
	@RequestMapping(value = "/nbrheureparniveauparclass", method = RequestMethod.GET )
	public @ResponseBody List<Object> getNmbrePenaltByClasse(Model model ) throws ParseException {
		return	this.nbrrepos.nombreheureparmatiereparniveau();
	}
	 
	 @RequestMapping(value = "/getall", method = RequestMethod.GET)
		public @ResponseBody List<Niveau_Matiere> getalluser(Model model, String nomniveau) {
			return nbrrepos.findAll();
		}  
}
