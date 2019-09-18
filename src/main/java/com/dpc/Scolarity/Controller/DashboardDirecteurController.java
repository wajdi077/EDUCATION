package com.dpc.Scolarity.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.bouncycastle.math.ec.custom.djb.Curve25519FieldElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.DashboardDirecteurDTO;
import com.dpc.Scolarity.Dto.NotificationPenalitesDTO;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.PenaliteRepository;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.Repository.AbsenceRepository;
import com.dpc.Scolarity.Repository.DashboardDirecteurrepository;
import com.dpc.Scolarity.Repository.Userrepos;
import com.dpc.Scolarity.service.implementation.AbsencesService;
import com.dpc.Scolarity.service.implementation.MainController;
import com.dpc.Scolarity.service.implementation.PenalitesService;
import com.dpc.Scolarity.service.implementation.boardService;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;

import java.util.function.Predicate;




@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/dashboard")
public class DashboardDirecteurController extends MainController {
	
	static LocalDate localdate = LocalDate.now();

	public static Predicate<Programme> isEquals()
	{
	    return p-> p.getDatedejour().toString().equals(localdate.toString());
	}
	
	
	
	@Autowired
	boardService boardservice ;
	
	@Autowired
	IUtilisateur userrepos ;
	
	@Autowired
	Userrepos userrepository ;
	@Autowired
	ProgrammeRepository programmerepos ;
	
	@Autowired
	DashboardDirecteurrepository progrepos ;
	
	
	@Autowired
	AbsenceRepository absencerepos ;
	
	@Autowired
	AbsencesService absenceservice ;
	
	@Autowired
	PenaliteRepository penalitiesrepos ;
	
	
	@Autowired
	PenalitesService penalityservice ;
	

	
	@RequestMapping(value = "/etat", method = RequestMethod.GET )
	public    Collection<DashboardDirecteurDTO> getbyetat(String username ) throws ParseException  {
		
	Utilisateur u = this.userrepos.findByUsername(username);
	Etablissement e = u.getEtablissement();
	System.out.println("user"+u.getUsername());
	System.out.println("etablisementnom"+e.getLibelle());
	
	System.out.println("etablissment "+e);
	List<Utilisateur> profs = this.userrepos.findByEtablissementAndArchiverIsFalseAndProfil(e, "Enseignant");
	List<Programme> proprof1 = new ArrayList<>();
   // Date madate = new Date() ;

    List<DashboardDirecteurDTO> listedtos = new ArrayList<DashboardDirecteurDTO>();




	LocalDate localdate = LocalDate.now();
	LocalDateTime time = LocalDateTime.now() ;
	 
    String heure = time.toString().substring(11, 13);
    		
    
	Predicate<Programme> equalsjour = p-> p.getDatedejour().toString().equals(localdate.toString());
    		 
	
	
     Predicate<Programme> afterheure = p-> p.getHeure().getHeureDebut().toString().equals(heure);



     
	for (Utilisateur utilisateur : profs) {
		List<Programme> proprof = new ArrayList<>();
		
	  proprof=this.progrepos.findByProf(utilisateur);
	    
	   proprof1=proprof.stream().filter(
			   isEquals().and(afterheure)).collect(Collectors.toList());
	   
	   

	   
	   
		for(Programme p:proprof1) {
			//Date dateprog = p.getDateprog();
			if(p!=null) {
				System.out.println("date actuel"+p.getDatedejour());


		List<AbsencesEleve> absences = this.absencerepos.findByProgramme(p);
		List<Penalites> penalitiesretenus = this.penalitiesrepos.findByProgrammeAndMotif(p, "retenu");
		List<Penalites> penalitiesavertisements = this.penalitiesrepos.findByProgrammeAndMotif(p, "avertisement");

		
		List<Penalites> penalitiesexclus =this.penalitiesrepos.findByProgrammeAndMotif(p, "exclus");
			
		
		
		//List<Penalites> penalitiesexclus1  = new  ArrayList<Penalites>(); 
			//penalitiesexclus1	=penalitiesexclus.stream().filter(n-> n.getDate_Penalites());
					//penalitiesexclus.stream().filter((n-> n.getEleves().getEtablissement().getLibelle()==u.getEtablissement().getLibelle())).collect(Collectors.toList());

		
		int exclus = penalitiesexclus.size();
		int abs = absences.size();
		int retenu = penalitiesretenus.size();
		int avert = penalitiesavertisements.size();
		int count = exclus+abs+retenu+avert;
		
		DashboardDirecteurDTO pdto = convertdashboardToDTO(p);
		pdto.setAbsence(abs);
		pdto.setExclu(exclus);
		pdto.setAvertisement(avert);
		pdto.setRetenu(retenu);
		pdto.setPenalites(count);
		pdto.setProgrammeverou(p.getVerou());
		
		
		listedtos.add(pdto);		
		
		
		
		System.out.println(abs);
		System.out.println(exclus);

		System.out.println(retenu);
		System.out.println(avert);
		
		
		



		
			//int retenu = this.absenceservice.countAbsenceByDay();
		//	arrayListabesences.add(retenu);
				
			
				/*for (AbsencesEleve absence: absences)
				{
					int abs = this.absenceservice.countAbsenceByDay();
					arrayListabesences.add(abs);
					
				} 
				for (Penalites retenus: penalitiesretenus)
				{
					int abs = this.penalityservice.countRetenuByDay();
					arrayListretenus.add(abs);
					
				} 
				for (Penalites exclu: penalitiesexclus)
				{
					int abs = this.penalityservice.countExcclusbyday();
					arrayListexclus.add(abs);
					
				} */
		
	
		
			//programmes.add(p);	
			
			
			}
			
		}
	}
	

	//System.out.println(programmes);

		        
	/*   List<DashboardDirecteurDTO> programmeDTOs = programmes.stream()
				 
	              .map(programme -> convertdashboardToDTO(programme))
	              .collect(Collectors.toList());*/
	
		     
              return  listedtos;
  
  

	
	}

	

}
