package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Repository.MotifRepository;
import com.dpc.Scolarity.service.implementation.MotifService;



@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/motif")
public class MotifController {
	@Autowired 
	MotifRepository motfirepos ; 
	@Autowired
	MotifService motifservice ; 
	
	
	@RequestMapping(value = "/getNmbrePenaltByNiveau", method = RequestMethod.GET )
	public @ResponseBody List<Object> getNmbrePenaltByClasse(Model model ) throws ParseException {
		return	this.motfirepos.countPenalitesByNiveauByEtablissement();
	}
/*	
	
	@RequestMapping(value = "/getNiveauByPenality", method = RequestMethod.GET )
	public List<Object> getNiveauByMotif(String niveau){
		List<Object> niveauDTO = new ArrayList<>();
		System.out.println("hahahahahahahahahah");
		System.out.println(this.penalitesservice.getNiveauByMotif(niveau));
		eleveDetailsDTO = this.penalitesservice.getEleveByMotif(niveau);
	
		return eleveDetailsDTO;
	}

	*/
}
