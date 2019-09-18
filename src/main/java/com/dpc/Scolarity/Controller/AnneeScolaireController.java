package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.Collection;
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

import com.dpc.Scolarity.Domain.Anneescolaire;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Dto.SemaineDTO;
import com.dpc.Scolarity.service.implementation.AnneeScolaireService;
import com.dpc.Scolarity.service.implementation.SemaineService;
/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/annee", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnneeScolaireController {
	@Autowired
	AnneeScolaireService anneescolaireservice ;
	
	@Autowired
	SemaineService semaineservice ;
	@RequestMapping(value = "/getanneeactuelle", method = RequestMethod.GET)

	public Anneescolaire getAneeactuelle( )  {
		Anneescolaire a  = anneescolaireservice.verifier_date();
		System.out.println("lanneeeeee est "+a.getAnneeScolaire());
		return a;

}

	@RequestMapping(value = "/allsemaine", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<Semaine> getAllSemaine() {
	
		
		return this.semaineservice.findAllSemaine();

}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	 public @ResponseBody Map<String, Boolean> addsalle(Model model, @RequestBody SemaineDTO semainedto )
	 {
	SemaineDTO a = semaineservice.saveInterval(semainedto);
	System.out.println(a);
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		
		
		return success;
	 }
}
