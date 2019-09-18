/**
 * 
 */
package com.dpc.Scolarity.Controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.management.relation.Role;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Authority;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.HistoriqueEtab;
import com.dpc.Scolarity.Domain.User_Etablissements;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.EtablissementDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Dto.UserEtablissementDTO;
import com.dpc.Scolarity.Repository.EtablissementRepository;
import com.dpc.Scolarity.Repository.HistoriqueEtabRepository;
import com.dpc.Scolarity.Repository.IAuthority;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.UserEtablissementRepository;
import com.dpc.Scolarity.service.implementation.MailServiceImp;

/**
 * @author hamza
 *
 */
@Controller
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/etablissement")
public class EtablissementController {
	
	@Autowired
	EtablissementRepository etabrepo;
	@Autowired
	UserEtablissementRepository usetrepo;
	@Autowired
	IAuthority iAuthority;
	
	 @Autowired 
	    IUtilisateur iUtilisateur;
	 @Autowired
		MailServiceImp mailservice ;
	 @Autowired
	 HistoriqueEtabRepository hisrepo;
	
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
	 
	 //***************Ajout etablissement***************
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Boolean> addsalle(Model model,String userConnect, @RequestBody Etablissement e)
	 {
		Etablissement Et = this.etabrepo.save(e);
		Authority role =new Authority();
		role.setEtablissement(Et);
		role.setName("ROLE_root");
		role.setDescription("root");
		Authority r =this.iAuthority.save(role);
		Utilisateur usr =new Utilisateur();
		usr.setAuthorities(r);
		usr.setEmail(Et.getEmail());
		usr.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
		usr.setEnabled(true);
		usr.setNom("root");
		usr.setPrenom("root");
		usr.setUsername(Et.getEmail());
		usr.setPassword(getAlphaNumericString(10));
		usr.setEtablissement(Et);
		
		iUtilisateur.save(usr);
		this.mailservice.EnvoyerEmailEtablissement(usr, Et);
		
		HistoriqueEtab his = new HistoriqueEtab();
		his.setDate(new Date(System.currentTimeMillis()));
		his.setDescription("a ajouté");
		his.setUserConnect(userConnect);
		his.setEtab(Et);
		his.setArchiver(false);
		hisrepo.save(his);
		
		
	
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		
		
		return success;
	 }
	
	//*******************afficher etablissement*************
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<Etablissement> getAll(  ) {
	 // etabrepo.findByArchiverIsFalse() ;
		return this.etabrepo.findByArchiverIsFalse() ;
		

}
	//*********************afficher etablissement par id******************
	@RequestMapping(value = "/all{id}", method = RequestMethod.GET)
	public Etablissement getAllEtabsById(@PathVariable  Long id){
		return etabrepo.findOne(id);
	}
	
	//******************Supprimé etablissement***************
	@RequestMapping(value = "/all{id}", method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable  Long id){
		Etablissement etab= etabrepo.findOne(id);
		etab.setArchiver(true);
		etabrepo.save(etab);
		HistoriqueEtab his = new HistoriqueEtab();
		his.setDate(new Date(System.currentTimeMillis()));
		his.setDescription("a ajouté");
		his.setUserConnect("root");
		his.setEtab(etab);
		his.setArchiver(false);
		hisrepo.save(his);
	return true;
	}
	
	//********************update etablissement*****************
	@RequestMapping(value="/all{id}",method=RequestMethod.PUT)
	public Etablissement addsalle(@PathVariable Long id,@Valid @RequestBody Etablissement etab) {
		etab.setId(id);
		HistoriqueEtab his = new HistoriqueEtab();
		his.setDate(new Date(System.currentTimeMillis()));
		his.setDescription("a modifié");
		his.setUserConnect("root");
		his.setEtab(etab);
		his.setArchiver(false);
		hisrepo.save(his);
		
		
		return etabrepo.save(etab);
		
		
	
	}
	
	//**************Afficher user avec profil enseignant******
	
		@RequestMapping(value = "/profs/getallnonaffect", method = RequestMethod.GET)
		public @ResponseBody List<Utilisateur> getalluser(Model model) {
			String profil ="Enseignant";
			return iUtilisateur.findByProfilAndArchiverIsFalse(profil);
		}
		
		//**************Ajout Prof ***************
		@RequestMapping(value = "/addprof", method = RequestMethod.POST)
		//@PreAuthorize("hasRole('ADMIN')")
		public  @ResponseBody Map<String, Boolean> addprof(Model model ,String email, @RequestBody Utilisateur user ) {
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
              this.mailservice.EnvoyerEmailAddUser(user);

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
		@RequestMapping(value = "/allprof{id}", method = RequestMethod.GET)
		public Utilisateur getAllProfById(@PathVariable  Long id){
			return iUtilisateur.findOne(id);
		}
		//******************Affecter prof*************
		@RequestMapping(value = "/affecteretabs", method = RequestMethod.POST ,consumes = "application/json")
		public   @ResponseBody String affecterusertoetabs(Model model ,Long idUser,@RequestBody List<String> email) {
		Etablissement etab ;
			User_Etablissements us ;
			System.out.println("email");
			System.out.println(email);
			Utilisateur user = this.iUtilisateur.findOne(idUser);
			System.out.println(user);
					for(int i=0; i<email.size();i++) {
						etab  = null ;
						
						us =new User_Etablissements();
						System.out.println(email.get(i));
						String mail =email.get(i);
					if (mail!=null) {
						
						etab = this.etabrepo.findByEmail(email.get(i));
								
						us.setArchiver(false);
						us.setEnseignant(user);
						us.setDateAffectation(new Date(System.currentTimeMillis()));
						us.setEtablissement(etab);
						//if(this.usetrepo.findByEnseignantAndEtablissement(user, etab).equals(null) );	
						us = this.usetrepo.save(us);
						
						user.setEtablissement( etab);	
						
						 iUtilisateur.save(user);
					}
			
		}
					Map<String, Boolean> success = new TreeMap<String, Boolean>();
					success.put("response", true);
					return "ok";
		}
		//***********************Désaffecter etab************
		@RequestMapping(value = "/desafectetabs", method = RequestMethod.POST ,consumes = "application/json")
		public   @ResponseBody String deleteEtabsFromUser(Model model ,Long idUser,@RequestBody List<String> email) {
		Etablissement etab ;
			User_Etablissements us ;	
			System.out.println(email);	
			System.out.println("idUser:"+idUser);
			Utilisateur user = this.iUtilisateur.findOne(idUser);
			System.out.println(user);
					for(int i=0; i<email.size();i++) {
						etab  = null ;
						
						System.out.println(email.get(i));
						String mail =email.get(i);
					if (mail!=null) {
						
						etab = this.etabrepo.findByEmail(email.get(i));
								
		                us = this.usetrepo.findByEnseignantAndEtablissement(user, etab);
						 this.usetrepo.delete(us);
					}
			
			
		}
					Map<String, Boolean> success = new TreeMap<String, Boolean>();
					success.put("response", true);
					return "ok";
		}
		//********Afficher UserEtab par etablissement******
		@RequestMapping(value = "/allUserEtab", method = RequestMethod.GET)
		
		public Collection<User_Etablissements> getAllProfsByEtabs(Long id) {
			Utilisateur user = iUtilisateur.findOne(id);
			return usetrepo.findByEnseignant(user);
			}

		
		
		}
		
		

		
