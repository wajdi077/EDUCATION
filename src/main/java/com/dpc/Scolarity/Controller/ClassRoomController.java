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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.HistoriqueClasse;
import com.dpc.Scolarity.Domain.HistoriqueEtab;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;

import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.HistoriqueClasseRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.service.implementation.ClasseRoomService;
import com.dpc.Scolarity.service.implementation.ProgrammeService;
/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/class", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassRoomController {
	@Autowired
	ClasseRoomService classservice ;
	@Autowired
	ClassesRepository classesrepository ;
	@Autowired
	ProgrammeService progservice;
	@Autowired
	IUtilisateur userrepo ; 
	@Autowired
	HistoriqueClasseRepository hisrepo;
	//*******************Create newClasses ******************* 
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Boolean> addClasses(Model model, @RequestBody ClasseRoomDTO classedto )
	 {
		ClasseRoomDTO a = classservice.save(classedto);
		Classes c ;
		c = classesrepository.findByNomclasse(classedto.getNomclasse());
		HistoriqueClasse his = new HistoriqueClasse();
		his.setDate(new Date(System.currentTimeMillis()));
		his.setDescription("a ajouté");
		his.setUserConnect(classedto.getUsername());
		his.setClasse(c);
	
		his.setArchiver(false);
		hisrepo.save(his);
	System.out.println(a);
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		return success;
	 }
	//********************** get all classesroom *************************
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Collection<ClasseRoomDTO> getAllclass() {
		return this.classservice.findAll();

}
	
	@RequestMapping(value = "/alls", method = RequestMethod.GET)
	public Collection<ClasseRoomDTO> getAllclass(String username) {
		return this.classservice.findAll(username);

}
	//*************************** remove Classes ****************************
		@RequestMapping(value = "/remove", method = RequestMethod.POST)
		public   @ResponseBody Map<String, Boolean> removeuser(Model model, String username,@RequestBody List<String> comclasses) {
			Classes Classestodelete ;
			Utilisateur u = this.userrepo.findByUsername(username);
			try {
			
				for(int i=0; i<comclasses.size();i++) {
					Classestodelete = null;
					Classestodelete = classesrepository.findByNomclasseAndEtablissement(comclasses.get(i),u.getEtablissement());
					
					if(Classestodelete!=null) 
					{
						
						classservice.delete(Classestodelete);
						
						
					}
					
					HistoriqueClasse his = new HistoriqueClasse();
					his.setDate(new Date(System.currentTimeMillis()));
					his.setDescription("a supprimé");
					his.setUserConnect(username);
					his.setClasse(Classestodelete);
				
					his.setArchiver(false);
					hisrepo.save(his);
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
		public   @ResponseBody Map<String, Boolean> archiver(Model model,String username, @RequestBody List<String> comclasses) {
			Classes classetoarchive ;
			Utilisateur u = this.userrepo.findByUsername(username);
			
			try {
			
				for(int i=0; i<comclasses.size();i++) {
					classetoarchive = null;
					classetoarchive = classesrepository.findByNomclasseAndEtablissement(comclasses.get(i),u.getEtablissement());
					
					if(classetoarchive!=null) 
					{
						classetoarchive.setArchiver(true);
						classesrepository.save(classetoarchive);
						
						
					}
					HistoriqueClasse his = new HistoriqueClasse();
					his.setDate(new Date(System.currentTimeMillis()));
					his.setDescription("a archivé");
					his.setUserConnect(username);
					his.setClasse(classetoarchive);
				
					his.setArchiver(false);
					hisrepo.save(his);
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
		//**********************update Classes ******************************************
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public @ResponseBody Map<String, Boolean> updateClasses(Model model,String username, @RequestBody ClasseRoomDTO c) {
			Utilisateur u = this.userrepo.findByUsername(username);
			Classes s = classesrepository.findByNomclasseAndEtablissement(c.getNomclasse(), u.getEtablissement());
			
			if(s!=null) {
				s.setNiveau(c.getNiveau());
				s.setRemarque(c.getRemarque());	
			 try {
				 this.classservice.update(s);
				 
				 HistoriqueClasse his = new HistoriqueClasse();
					his.setDate(new Date(System.currentTimeMillis()));
					his.setDescription("a modifié");
					his.setUserConnect(username);
					his.setClasse(s);
				
					his.setArchiver(false);
					System.out.println("hamzaaaaaaaaa!!!!");
					hisrepo.save(his);
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
		
		@RequestMapping(value = "/bynomclasse", method = RequestMethod.GET)
		public ClasseRoomDTO getclassebynom(Model model,  String nomclasses) {
			return this.classservice.findByNomClasse(nomclasses);
	}
	
		@RequestMapping(value = "/allbyniveau", method = RequestMethod.GET)
		public Collection<Classes> getAllbyniveau(Model model,  String niveau) {
			return this.classservice.findbyniveau(niveau);

	}
		
		@RequestMapping(value = "/byid", method = RequestMethod.GET)
		public ClasseRoomDTO getclassebyid(Model model,  Long id ) {
			return this.classservice.findById(id);

	}
		//*************************Afficher classe archivé*********************
		
		@RequestMapping(value = "/GetAllClassesArchiver", method = RequestMethod.GET)
		public   Collection<Classes> GetAllClassesArchiver(Model model,String username) {
			
			Utilisateur u = this.userrepo.findByUsername(username);
					
		return this.classesrepository.findByEtablissementAndArchiverIsTrue(u.getEtablissement());
				}
				
		}

	

