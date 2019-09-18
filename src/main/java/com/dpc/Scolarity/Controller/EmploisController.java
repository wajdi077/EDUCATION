package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Dto.EmploisDTO;
import com.dpc.Scolarity.Dto.HeuresDTO;
import com.dpc.Scolarity.Dto.JoursDTO;
import com.dpc.Scolarity.Dto.ProgrammeDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.service.implementation.EmploisServcie;
import com.dpc.Scolarity.service.implementation.HeuresService;
import com.dpc.Scolarity.service.implementation.JoursService;
import com.dpc.Scolarity.service.implementation.SemaineService;


/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/emplois")
public class EmploisController {
	
	@Autowired
	JoursService jourservice ;
	@Autowired
	HeuresService heureservice ;
	@Autowired
	EmploisServcie emploisservice ; 
	@Autowired
	SemaineService semaineservice ;
	@Autowired
	ProgrammeRepository progrepo;
	
	@RequestMapping(value = "/allJours", method = RequestMethod.GET)
	public Collection<JoursDTO> getAlljours() {
		return this.jourservice.findAll();

}
	@RequestMapping(value = "/allheures", method = RequestMethod.GET)
	public Collection<Heures> getAllheurs() {
		return this.heureservice.findAll();

}
	@RequestMapping(value = "/allsemaine", method = RequestMethod.GET)
	public Collection<Semaine> getallsemaine() throws ParseException {
		return this.semaineservice.findAllSemainebydate();

}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Boolean> addprogramme(Model model, @RequestBody ProgrammeDTO p  ) throws ParseException
	 {
		emploisservice.AjouterProgramme(p);;

		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		return success;
	 }
	
	@RequestMapping(value = "/modifierseance", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Boolean> updateprogramme(Model model, @RequestBody ProgrammeDTO seancescolaire  ) throws ParseException
	 {
		emploisservice.modifieremplois(seancescolaire);

		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		return success;
	 }
	
	@RequestMapping(value = "/allbyprof", method = RequestMethod.GET)
	
	public Collection<EmploisDTO> getprogbyprof(Model model,  String username) {
	
		return this.emploisservice.findbyProf(username);

}
	@RequestMapping(value = "/injectprogramme", method = RequestMethod.GET)
	
	public Boolean  injectprogramme(Model model,  String username,String semaine) throws ParseException {
		this.emploisservice.injectEmploisToWeak(username, semaine);
		return true ; 

}
	@RequestMapping(value = "/allbyprofday", method = RequestMethod.GET)
	
	public Collection<EmploisDTO> getprogbyprofAndDay(Model model,  String username ,String jour) {
	
		
		
		
	System.out.println("le username est : "+username+"le jour est : "+jour);
		return this.emploisservice.findByProfAndDay(username, jour);

}
	
	
	@RequestMapping(value = "/getseance", method = RequestMethod.GET)
	
	public EmploisDTO getSeanceEnseigant(Model model,  String username ,String jour,String heure) {
	
		
		
		



		return this.emploisservice.getEmploisdetempEnseignant(username, jour, heure);

}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> updateemplois(Model model, @RequestBody ProgrammeDTO emplois) {
		//System.out.println();
		//System.out.println(user.getUsername()+"***"+user.getTelephone()+"***"+user.getEmail());

		//System.out.println();
			
		if(emplois!=null) {
			
			
		 try {
			 emploisservice.modifieremplois(emplois);
			 Map<String, Boolean> success = new TreeMap<String, Boolean>();
				success.put("response", true);
				return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
		}else {
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
	
	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public  EmploisDTO getbyId(Model model, long id) {
	
		return  this.emploisservice.findProgById(id);
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public  Boolean delet(Model model, long id) {
		System.out.println("delete");
	Programme p = this.progrepo.findOne(id);
	this.progrepo.delete(p);
	return true;
	}
	@RequestMapping(value = "/deletebyday", method = RequestMethod.POST)
	public  Boolean deleteBYday(Model model,@RequestBody Date d ) {
		Programme programmetoDelete = null  ; 
		List<Programme> p = new ArrayList<Programme>();
		p = this.progrepo.findByDatedejour(d);
		if(p.size()==0)
		{
			return false ;
		}
		else {
		for ( int i =0;i<p.size();i++)
		{
			programmetoDelete = p.get(i);
			if (programmetoDelete!= null)
			{
			
			progrepo.delete(programmetoDelete);
			
			}
		}
		return true;
		}
	}
}
