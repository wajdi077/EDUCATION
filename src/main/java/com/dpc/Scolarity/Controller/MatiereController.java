package com.dpc.Scolarity.Controller;

import java.util.Collection;
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

import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.MatiereDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.MatierRepository;
import com.dpc.Scolarity.service.implementation.MatiereService;
/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/matiere")
public class MatiereController {
	@Autowired
	MatiereService matiereservice ;
	@Autowired 
	MatierRepository matiererepo ; 
	@Autowired
	IUtilisateur userrepo ;
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	 public @ResponseBody Map<String, Boolean> addMatiere(Model model, @RequestBody MatiereDTO matieredto )
	 {
		MatiereDTO a = matiereservice.save(matieredto);
	System.out.println(a.getNommatiereFR());
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		
		
		return success;
	 }
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public List<Matiere> getAllMatiere() {
		return this.matiereservice.findAll();

}
	@RequestMapping(value = "/alls", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public List<Matiere> getAllMatiere(String username) {
		return this.matiereservice.findAll(username);

}
	@RequestMapping(value = "/allarchiver", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public List<Matiere> getAllMatierearchiver(String username) {
		return this.matiereservice.findAllMatiereArchiver(username);

}
	//**********************update Matiere ******************************************
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public @ResponseBody Map<String, Boolean> update(Model model, @RequestBody Matiere matiere) {
		System.out.println("la mteire estr "+matiere.getIdmatiere());
			try {
				matiereservice.update(matiere);
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


		//*************************** remove ****************************
		@RequestMapping(value = "/remove", method = RequestMethod.POST)
		public   @ResponseBody Map<String, Boolean> removeuser(Model model,String username,  @RequestBody List<String> nommatiereFR ) {
			Matiere matieretodelete ;
			Utilisateur u = this.userrepo.findByUsername(username);
			try {
				//System.out.println(usernames.size());
				for(int i=0; i<nommatiereFR.size();i++) {
					matieretodelete = null;
					System.out.println(nommatiereFR.get(i)+"slimyabhim1");
					System.out.println(nommatiereFR.get(i));
					matieretodelete = matiereservice.findBynomatiereEtablissement(nommatiereFR.get(i), u.getEtablissement());
					System.out.println(nommatiereFR.get(i)+"slimyabhim111");
					if(matieretodelete!=null) 
					//	System.out.println(nomSalle.get(i)+"slimyabhim4141");
						matiereservice.delete(matieretodelete);
						//System.out.println(nomSalle.get(i)+"slimyab14552him1");
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
		public   @ResponseBody Map<String, Boolean> archiver(Model model,String username ,  @RequestBody List<String> nommatiereFR ) {
			Matiere matieretodelete ;
	Utilisateur u = this.userrepo.findByUsername(username);
			try {

				for(int i=0; i<nommatiereFR.size();i++) {
					matieretodelete = null;
		
				
					matieretodelete = matiereservice.findBynomatiereEtablissement(nommatiereFR.get(i), u.getEtablissement());
					if(matieretodelete!=null) 
						matieretodelete.setArchiver(true);
					this.matiererepo.save(matieretodelete);
				
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
		

}



