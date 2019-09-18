/**
 * 
 */
package com.dpc.Scolarity.Controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Niveau_etablissement;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AbsenceEleveDTO;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Repository.AbsenceRepository;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.PenaliteRepository;
import com.dpc.Scolarity.service.implementation.AbsencesService;
import com.dpc.Scolarity.service.implementation.EleveService;
import com.dpc.Scolarity.service.implementation.MailServiceImp;
import com.dpc.Scolarity.service.implementation.PenalitesService;

/**
 * @author slim
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/Parrain", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParrainController {
	@Autowired
EleveService eleveservice ;
	@Autowired
	IUtilisateur iUtilisateur;
	@Autowired
	AbsenceRepository absencerepo;
	@Autowired
	PenaliteRepository penalitesrepo;
	@Autowired
	EleveRepository eleverepo;
	@Autowired
	AbsencesService absenceservice;
	@Autowired
	PenalitesService penservice;
	@Autowired
	MailServiceImp mailservice ;
	 @Autowired private IUtilisateur iutilisateur ; 

	@RequestMapping(value = "/allbyParrain", method = RequestMethod.GET)

	public Collection<EleveDTO> getallelevesbyParrain(  String username) {
		return eleveservice.findAllByParrain(username);

}
	
	@RequestMapping(value = "/allParrain", method = RequestMethod.GET)

	public Collection<Utilisateur> getallParrain() {
		String profil = "Parrain";
		return iUtilisateur.findByProfil(profil);

}
	@RequestMapping(value = "/allabsencebyeleve", method = RequestMethod.GET)

	public Collection<AbsenceEleveDTO> getallabsencebyEleve(  String numInscription) {
		Collection<AbsenceEleveDTO> l =this.absenceservice.findAbsenceByEleve(numInscription);
		return l;

}
	@RequestMapping(value = "/allPenalitesByEleves", method = RequestMethod.GET)

	public Collection<AbsenceEleveDTO> getPenalitesByEleves(  String numInscription) {
		Collection<AbsenceEleveDTO> l =this.penservice.findPenalitesByEleve(numInscription);
		return l;

}
	
	 //***********Generated random password****************
	 static String getAlphaNumericString(int n) 
	    { 
	  
	        // chose a Character random from this String 
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz"; 
	  
	        // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(n); 
	  
	        for (int i = 0; i < n; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(AlphaNumericString.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(AlphaNumericString 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 
	    } 
	//**************Ajout Prof ***************
			@RequestMapping(value = "/addpParrain/{username}", method = RequestMethod.POST)
			//@PreAuthorize("hasRole('ADMIN')")
			public  @ResponseBody Map<String, Boolean> addenseignant(Model model ,@PathVariable String username, @RequestBody Utilisateur user ) {
				Boolean response;
				try {
					//Utilisateur u = this.iUtilisateur.findByEmail(email);
					
					Utilisateur u = this.iUtilisateur.save(user);
					user.setArchiver(false);
					user.setProfil("Parrain");
					user.setNbrheures((long) 18);
					user.setEmail(u.getEmail());
					user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
	                user.setUsername(u.getEmail());
					//user.setUtilisateur(u) ;
					user.setEnabled(true);
					user.setPassword(getAlphaNumericString(10));
					
	                iUtilisateur.save(user);
	              this.mailservice.EnvoyerEmailAddParrain(user);

	                Map<String, Boolean> success = new TreeMap<String, Boolean>();
					success.put("response", true);
					//email=user.getEmail();
					//this.mailservice.EnvoyerEmailAddUser(user);
					return success;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					e.printStackTrace();
					Map<String, Boolean> echec = new TreeMap<String, Boolean>();
					echec.put("response", false);
					return echec;
				}}
 
}
