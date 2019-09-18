/**
 * 
 */
package com.dpc.Scolarity.Controller;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Dto.EleveDetailsDTO;
import com.dpc.Scolarity.Dto.NotificationPenalitesDTO;
import com.dpc.Scolarity.Dto.PenalitesDTO;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.service.implementation.EleveService;
import com.dpc.Scolarity.service.implementation.EmploisServcie;
import com.dpc.Scolarity.service.implementation.PenalitesService;
import com.google.gson.JsonArray;

/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/penalites")
public class PenalitesController {
	
	@Autowired
EleveService eleveservice ;
	@Autowired
	EmploisServcie emploisservice  ; 
	@Autowired
	ProgrammeRepository seanceservice;
	
	@Autowired 
	PenalitesService penalitesservice ;
	
	
	
	@RequestMapping(value = "/exclus", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody NotificationPenalitesDTO exclus(Model model ,Long id  ,@RequestBody PenalitesDTO pendto) {
		
		Programme seance = this.seanceservice.findOne(id);
	this.penalitesservice.exclus(seance, pendto);
		

	
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return this.penalitesservice.exclus(seance, pendto);
	}
	
	
	@RequestMapping(value = "/retenu", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody String retenu(Model model ,Long id  ,@RequestBody PenalitesDTO pendto) {
		
		Programme seance = this.seanceservice.findOne(id);
	this.penalitesservice.retenu(seance, pendto);
		

	
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return "ok";
	}
	
	@RequestMapping(value = "/getexclusbyday", method = RequestMethod.GET )
	public   @ResponseBody int getexclusbyday(Model model ) throws ParseException {
		
	

		

	
			return	this.penalitesservice.countExcclusbyday();
	
	}
	@RequestMapping(value = "/exclusbyetablissement", method = RequestMethod.GET )
	public   @ResponseBody List<NotificationPenalitesDTO> exclusbyetablissement(Model model, String username )  {
		
	

		

	
			return	this.penalitesservice.findallexclus(username);
	}
	@RequestMapping(value = "/retenubyetablissement", method = RequestMethod.GET )
	public   @ResponseBody List<NotificationPenalitesDTO> retenubyetablissement(Model model, String username )  {
		
	
			return	this.penalitesservice.findallretenu(username);
	}
	@RequestMapping(value = "/getretenubyday", method = RequestMethod.GET )
	public   @ResponseBody int getretenuebyday(Model model ) throws ParseException {

			return	this.penalitesservice.countRetenuByDay();
	}
	
	
	@RequestMapping(value = "/getEleveByPenality", method = RequestMethod.GET )
	public List<Object> getEleveByMotif(String numInscription){
		List<Object> eleveDetailsDTO = new ArrayList<>();
		System.out.println("hahahahahahahahahah");
		System.out.println(this.penalitesservice.getEleveByMotif(numInscription));
		eleveDetailsDTO = this.penalitesservice.getEleveByMotif(numInscription);
	
		
	/*	for(int i =0; i<eleveDetailsDTO.size() ; i++) {
			
			System.out.print("motif"+ eleveDetailsDTO.get(i).get(0));
		}
		System.out.println();*/
		return eleveDetailsDTO;
	}

}
