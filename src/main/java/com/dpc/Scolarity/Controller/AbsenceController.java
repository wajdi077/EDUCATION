/**
 * 
 */
package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.service.implementation.AbsencesService;
import com.dpc.Scolarity.service.implementation.EleveService;
import com.dpc.Scolarity.service.implementation.EmploisServcie;

/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/absence")
public class AbsenceController {
	
	@Autowired
EleveService eleveservice ;
	@Autowired
	EmploisServcie emploisservice  ; 
	@Autowired
	ProgrammeRepository seanceservice;
	@Autowired 
	AbsencesService absenceservice ; 
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody String addAbsence(Model model , Long id  ,@RequestBody List<String> numinscription) {
		
		Programme seance = this.seanceservice.findOne(id);
	this.absenceservice.AddAbsence(seance, numinscription);
		

		
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return "ok";
	}
	
	@RequestMapping(value = "/getabsencebyday", method = RequestMethod.GET )
	public   @ResponseBody int getabsencebyday(Model model ) throws ParseException {
		
	

		
			return	this.absenceservice.countAbsenceByDay();
	}
	
	

}
