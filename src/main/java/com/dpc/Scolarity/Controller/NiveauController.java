
 package com.dpc.Scolarity.Controller;
 
  import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired; import
 org.springframework.http.MediaType; import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import
 org.springframework.web.bind.annotation.RequestMapping; import

 org.springframework.web.bind.annotation.RequestMethod; import

 org.springframework.web.bind.annotation.ResponseBody; import
 org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.NiveauUser;
import com.dpc.Scolarity.Domain.Niveau_etablissement;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.EtablissementRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.NiveauEtabRepository;
import com.dpc.Scolarity.Repository.NiveauRepository;
import com.dpc.Scolarity.Repository.NiveauUserRepository;
import com.dpc.Scolarity.service.NiveauService;
 
 @CrossOrigin("*")
 @RestController
 @RequestMapping(value = "api/niveau", produces = MediaType.APPLICATION_JSON_VALUE)

 public class NiveauController {
 @Autowired private NiveauEtabRepository niveauetabrepos ; 
 @Autowired private NiveauUserRepository niveauuserepos ; 
 @Autowired private NiveauRepository niveauRepository ;
 @Autowired private NiveauService niveauService; 
 @Autowired private EtablissementRepository etabrepos ;
 @Autowired private IUtilisateur iutilisateur ; 
 
 //afficher tous les niveau 
 
 
 @RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody List<Niveau> getalluser(Model model) {
		return niveauRepository.findAll();
	}  


 @RequestMapping(value = "/getallbyetablissement", method = RequestMethod.GET)
 public @ResponseBody List<Niveau_etablissement> getallnivauByEtab(String username) {
	 Niveau_etablissement nu ; 
	
		Utilisateur userConnected = this.iutilisateur.findByUsername(username);
		Etablissement e = userConnected.getEtablissement();
	    List<Niveau_etablissement> niveauuser =niveauetabrepos.findAllNiveauByEtablissement(e);

		return niveauuser; 
 
 }
 
 
 
 
	 
	// affecter niveau au enseignant 
		@RequestMapping(value = "/affecter", method = RequestMethod.POST ,consumes = "application/json")
		public   @ResponseBody String AffecterEleveToParrain(Model model ,@RequestBody List<String> nomniveau, Long id) {
			Utilisateur user = this.iutilisateur.findById(id);
			this.niveauService.Affecterniveau(id, nomniveau);
			
				Map<String, Boolean> success = new TreeMap<String, Boolean>();
				success.put("response", true);
				return "ok";
		}
	 

		//affecter niveau to etab
		  
		@RequestMapping(value = "/affecterniveautoetab", method = RequestMethod.POST ,consumes = "application/json")
		public   @ResponseBody String Affecterniveautoetablissement(Model model ,@RequestBody List<String> nomniveau, String username) {
			this.niveauService.Affecterniveautoetablissement(username, nomniveau);
			
				Map<String, Boolean> success = new TreeMap<String, Boolean>();
				success.put("response", true);
				return "ok";
		}
 }
 
 