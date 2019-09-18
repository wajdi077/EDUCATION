package com.dpc.Scolarity.Controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.service.implementation.NiveauUserservice;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/niveauutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)


public class UserNiveauController {

	@Autowired
	private IUtilisateur iUtilisateur ; 
@Autowired
private NiveauUserservice niveauuser ;
	
	
	/*
	@RequestMapping(value = "/affecter", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody String affecterniveauutilisateur(Model model , String  email ,@RequestBody List<String> niveau) {
		//String nomclasse  ="7emme B";
		Utilisateur c = this.iUtilisateur.findByEmail(email);
		this.niveauuser.AddEleveToClasses2(c, niveau);
		
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return "ok";
	}
	*/
}
