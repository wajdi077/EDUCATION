package com.dpc.Scolarity.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.SalleRepository;
import com.dpc.Scolarity.service.implementation.SalleService;
/**
 * @author slim
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/salle")
public class SalleController {
	
	@Autowired
	SalleService salleservice ; 
	@Autowired
	SalleRepository sallerepository ;
	@Autowired
	IUtilisateur userrepo;
	
	
	
	
	
	
	//*******************Create new Salle******************* 
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	 public @ResponseBody Map<String, Boolean> addsalle(Model model, @RequestBody SalleDTO salledto)
	 {

	
	SalleDTO a = salleservice.save(salledto);
	System.out.println(a);
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		
		
		return success;
	 }
	//********************** get all salle *************************
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<SalleDTO> getAllSalles(  String username ) {
		return this.salleservice.findAll(username);

}
	
	//*************************** remove Salle ****************************
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public   @ResponseBody Map<String, Boolean> removeuser(Model model, @RequestBody List<Long> idsalle) {
		Salle salletodelete ;
		System.out.println("listedebut");
		System.out.println(idsalle);
		System.out.println("listefin");
		try {
			//System.out.println(usernames.size());
			for(int i=0; i<idsalle.size();i++) {
				salletodelete = null;
				System.out.println(idsalle.get(i)+"slimyabhim1");
				System.out.println(idsalle.get(i));
				salletodelete = sallerepository.findOne(idsalle.get(i));
				System.out.println(idsalle.get(i)+"slimyabhim111");
				if(salletodelete!=null) 
			
					salleservice.delete(salletodelete);
			}
			
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
	@RequestMapping(value = "/archiver", method = RequestMethod.POST)
	public   @ResponseBody Map<String, Boolean> archiver(Model model, @RequestBody List<Long> idsalle) {
		Salle salletoarchive ;
		try {
			//System.out.println(usernames.size());
			for(int i=0; i<idsalle.size();i++) {
				salletoarchive = null;
				salletoarchive = sallerepository.findOne(idsalle.get(i));
			
				if(salletoarchive!=null) 
					salletoarchive.setArchiver(true);
					sallerepository.save(salletoarchive);
			}
			
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
	//**********************update Salle ******************************************
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> updatesalle(Model model, @RequestBody SalleDTO salle) {
	
		Salle s = salleservice.findByNomSalles(salle.getNomSalle());
		
		
		if(s!=null) {
			s.setType(salle.getType());
			s.setRemarque(salle.getRemarque());
			
			
		 try {
			 this.salleservice.update(s);
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
	/* ********************************************** */
	@RequestMapping(value = "/GetSallesArchiver", method = RequestMethod.GET)
	public   Collection<Salle> GetAllClassesArchiver(Model model,String username) {
		
		Utilisateur u = this.userrepo.findByUsername(username);
				
	return this.sallerepository.findByEtablissementAndArchiverIsTrue(u.getEtablissement());
			}

}
