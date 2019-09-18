package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AnneeScolaireDTO;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;
import com.dpc.Scolarity.Dto.ClassesDesEleveDTO;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Dto.EleveDetailsDTO;
import com.dpc.Scolarity.Dto.EleveParrainDTO;
import com.dpc.Scolarity.Dto.EmploisDTO;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.PenaliteRepository;
import com.dpc.Scolarity.service.implementation.EleveService;
import com.dpc.Scolarity.service.implementation.PenalitesService;
/**
 * @author slim
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/eleve", produces = MediaType.APPLICATION_JSON_VALUE)
public class EleveController {
	@Autowired
EleveService eleveservice ;
	@Autowired
	IUtilisateur iUtilisateur;
	@Autowired
	EleveRepository eleverepo ; 
	@Autowired 
	PenalitesService penalitesService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Boolean> addeleve(Model model, @RequestBody EleveDTO elevedto )
	 {
	
		boolean valid  = eleveservice.save(elevedto);
	System.out.println("reponse pour l'email"+valid);
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", valid);
		return success;
	 }
	@RequestMapping(value = "/allbyniveau", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleveByniveau( String niveau) {
		return eleveservice.findAllByNiveau(niveau);

}
	@RequestMapping(value = "/allbyParrain", method = RequestMethod.GET)

	public Collection<EleveDTO> getallbyParrain(  String username) {
		return eleveservice.findAllByParrain(username);

}
	@RequestMapping(value = "/allbyclass/", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleveByclasse(  String nomclasse) {
		System.out.println("les eleves son "+this.eleveservice.findAllByclasse(nomclasse));
		return this.eleveservice.findAllByclasse(nomclasse);

}
	@RequestMapping(value = "/all", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleve( ) {
		return eleveservice.findAll();

}
	
	
	
	@RequestMapping(value = "/allbyetablissement", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleve( String username) {
		return eleveservice.findAll(username);

}
	@RequestMapping(value = "/allarchiver", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleveArchiver( String username) {
		return eleveservice.findAllarchiver(username);

}
	@RequestMapping(value = "/allbyclassnow", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleveByclassenow(  String username) {
		System.out.println("les eleves son "+this.eleveservice.findAllByclassenow(username));
		return this.eleveservice.findAllByclassenow(username);

}
	@RequestMapping(value = "/allbyclassencours", method = RequestMethod.GET)

	public Collection<EleveDTO> getAllEleveByclassEnCours( Long id ) {
		 Collection<EleveDTO> list ;
		 list=null;
		 if (id!=null) {
		 list =  this.eleveservice.findClasseEncours(id);
		 }
		 
		return list;

}
	@RequestMapping(value = "/bynumInscription", method = RequestMethod.GET)

	public EleveDTO getbynumInscriptions( String numInscription ) {
		EleveDTO eleve =new EleveDTO();
		
		if(numInscription!=null) {
		eleve = this.eleveservice.findBynumInscription(numInscription);
		}

		return eleve;

}
	
	// affecter eleve au parrain 
	@RequestMapping(value = "/affecter", method = RequestMethod.POST ,consumes = "application/json")
	public   @ResponseBody String AffecterEleveToParrain(Model model ,@RequestBody List<String> numinscription,String username) {
	
		
		Utilisateur user = this.iUtilisateur.findByUsername(username);
		this.eleveservice.AffecterEleveAuParrain(user, numinscription);
		
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return "ok";
	}
	
	
	@RequestMapping(value = "/archiver", method = RequestMethod.POST)
	public   @ResponseBody Map<String, Boolean> archiver(Model model, @RequestBody List<String> numinscription) {
		Eleve elevetoarchive ;
		try {
			//System.out.println(usernames.size());
			for(int i=0; i<numinscription.size();i++) {
				elevetoarchive = null;
				elevetoarchive = eleverepo.findByNumInscription(numinscription.get(i));
			
				if(elevetoarchive!=null) 
					elevetoarchive.setArchiver(true);
				eleverepo.save(elevetoarchive);
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
	@RequestMapping(value = "/addelevewithparrain", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Boolean> addeleveWithParrain(Model model ,@RequestBody EleveParrainDTO eleveparain)
	 {
	EleveDTO elevedto= eleveparain.getElevedto();
	Utilisateur user = eleveparain.getUser();
	
		boolean valid  = eleveservice.saveEleveParent(elevedto, user);
	System.out.println("reponse pour l'email"+valid);
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", valid);
		return success;
	 }
	
	/*Developpe par : Sarra NAIFAR 
	 * Date: 23/07/2019
	 * Fonction: donner le nombre des pinalités par eleve
	 */
	
	@RequestMapping(value = "/eleveDetails", method = RequestMethod.GET)

	public EleveDetailsDTO getEleveByMotif( @RequestParam String numInscription ) {
		Eleve  eleve = new Eleve();
		EleveDetailsDTO eleveDetailsDTO = new EleveDetailsDTO();
		if(numInscription!=null) {
			eleve = this.eleverepo.findByNumInscription(numInscription);
			System.out.println(/******************SARRA NAIFAR****************/);
			System.out.println(this.penalitesService.getEleveByMotif(numInscription));
			//eleveDetailsDTO= this.penalitesService.getEleveByMotif(numInscription);
		}

		return eleveDetailsDTO;

}
	/*Developpe par : Sarra NAIFAR 
	 * Date: 08/08/2019
	 * Fonction: donner le parrain d'un eleve choisis
	 */
	
	@RequestMapping(value = "/parraindetailsforeleve", method = RequestMethod.GET)
	public Map<String, String> getParrainByNumInscription(String numInscription) {
		
		return this.eleveservice.getParrainOfEleve(numInscription);
	
	
		
		
	}
	
	/*Developpe par : Sarra NAIFAR 
	 * Date: 08/08/2019
	 * Fonction: donner le parrain d'un eleve choisis
	 */
	
	@RequestMapping(value = "/elevewithclassnull", method = RequestMethod.GET)
	public Collection<EleveDTO> getNonAffectedEleve( String username) {
		return eleveservice.getNonAffectedEleve(username);

}
	

	//fct qui affiche un type a travers son id
    /*@GetMapping(value="/getEleveparparrain/{parrainNom}")
    public List<Eleve> getEleveNom(@PathVariable("parrainNom" )String parrainNom) {
    	Utilisateur parrain=this.iUtilisateur.findByNom(parrainNom);
    	List<Eleve> eleve=this.eleverepo.findByParrain(parrain);
	     return eleve ;
    }
*/
}
