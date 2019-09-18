package com.dpc.Scolarity.Controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Authority;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.HistoriqueUser;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.NiveauUser;
import com.dpc.Scolarity.Domain.User_Etablissements;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.EnseignantRepository;
import com.dpc.Scolarity.Repository.IAuthority;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.NiveauRepository;
import com.dpc.Scolarity.Repository.NiveauUserRepository;
import com.dpc.Scolarity.service.EnseignantService;
import com.dpc.Scolarity.service.UserService;
import com.dpc.Scolarity.service.implementation.MailServiceImp;
import com.dpc.Scolarity.service.implementation.UserServiceImpl;

import lombok.experimental.UtilityClass;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/enseignants", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnseignantController {
@Autowired
private IUtilisateur iUtilisateur ; 
@Autowired
private UserService userservice ;
@Autowired
private UserServiceImpl userserviceimpl ;

@Autowired
private EnseignantRepository enseignantrepository ; 
@Autowired
NiveauRepository niveauRepository ; 

 @Autowired 
 NiveauUserRepository repos ;
 
 @Autowired
 IUtilisateur userRepository;

 @Autowired 
 EnseignantService enseignantservice ; 
 @Autowired
 IAuthority iAuthority;
 @Autowired
	MailServiceImp mailservice ;
//afficher enseignant par etablissement 

@RequestMapping(value = "/allbyetab", method = RequestMethod.GET)


	public  @ResponseBody List<Utilisateur> getprofByEtab(Model model, String username) {
	
	String profil="Enseignant";
	
	Utilisateur userConnected = this.iUtilisateur.findByUsername(username);
	
	Etablissement etablissement = userConnected.getEtablissement();
	
	 return this.enseignantrepository.findByProfilAndEtablissementAndArchiverIsFalse(profil, etablissement) ;
			
	}

//afficher plus test
@RequestMapping(value = "/allbyetabtest", method = RequestMethod.GET)


public  @ResponseBody List<Utilisateur> getprofBy(Model model, String username) {
	
	

Utilisateur userConnected = this.iUtilisateur.findByUsername(username);
System.out.println("Utilisateur connecter est"+userConnected.getUsername());
Etablissement etablissement = userConnected.getEtablissement();
System.out.println("L'etablissement est"+etablissement);
String profil = null;
 return this.enseignantrepository.findByProfilNotLike(profil, profil) ;
		
}
 
@RequestMapping(value = "/getEnseignantNotlike", method = RequestMethod.GET )
public @ResponseBody List<Utilisateur> getEnseignant(Model model , String username) throws ParseException {

Utilisateur userConnected = this.iUtilisateur.findByUsername(username);
System.out.println("Utilisateur connecter est"+userConnected.getUsername());
Etablissement etablissement = userConnected.getEtablissement();
	return	this.enseignantrepository.afficherlesutilisateurNotLikeEnseignantetparain(etablissement.getId());
}

//afficher user par id 
@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
public  Utilisateur getalluser(Model model, long id) {
	return userRepository.findById(id);
}


//fct pour modifier le contenue de l'entité utilisateur
  @PostMapping(value = "/updateUtilisateur")
  public String updateUtilisateur(@RequestBody Utilisateur utilisateur) { //Cette annotation demande à Spring que le JSON contenu dans la partie body de la requête HTTP soit converti en objet Java
	  enseignantservice.update(utilisateur);
      return "ok" ; 
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
		@RequestMapping(value = "/addpEnseignant", method = RequestMethod.POST)
		//@PreAuthorize("hasRole('ADMIN')")
		public  @ResponseBody Map<String, Boolean> addenseignant(Model model ,String email, @RequestBody Utilisateur user ) {
			Boolean response;
			try {
				//Utilisateur u = this.iUtilisateur.findByEmail(email);
				
				Utilisateur u = this.iUtilisateur.save(user);
				user.setArchiver(false);
				user.setProfil("Enseignant");
				user.setNbrheures((long) 18);
				user.setEmail(u.getEmail());
				user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
                user.setUsername(u.getEmail());
				//user.setUtilisateur(u) ;
				user.setEnabled(true);
				user.setPassword(getAlphaNumericString(10));
				
                iUtilisateur.save(user);
              this.mailservice.EnvoyerEmailAddEnseignant(user);

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
		//*********************afficher Prof par id******************  
  

}
	





