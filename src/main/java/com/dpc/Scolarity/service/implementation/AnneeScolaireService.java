package com.dpc.Scolarity.service.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.GenerationTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Anneescolaire;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.SemaineAnneeScolaire;
import com.dpc.Scolarity.Dto.AnneeScolaireDTO;
import com.dpc.Scolarity.Repository.AnneeScolaireRepository;
import com.dpc.Scolarity.Repository.SemAnneeScolaireRepository;
import com.dpc.Scolarity.Repository.SemaineRepository;


@Service
public class AnneeScolaireService extends MainController   {
	
	
	@Autowired
	private AnneeScolaireRepository anneeScolaireRepository;
	@Autowired
	private SemaineRepository semainerepository  ;
	@Autowired
	SemAnneeScolaireRepository semaineannescolairerepository ;


	public AnneeScolaireDTO save(AnneeScolaireDTO anneeScolaire) {
		Anneescolaire anneesco = dtoToAnnee_scolaire(anneeScolaire);
		Anneescolaire anneeCreated = anneeScolaireRepository.save(anneesco);
		
		return convertAnneeScolaireToDto(anneeCreated);
	}
      
 
	public AnneeScolaireDTO findOne(Long id) {
		Anneescolaire a = this.anneeScolaireRepository.findOne(id);
		AnneeScolaireDTO anneeDTO = convertAnneeScolaireToDto(a);
		return anneeDTO;
	}


	public void delete(Long id) {
		this.anneeScolaireRepository.delete(id);
		
	}


	public Collection<AnneeScolaireDTO> findAll() {

		return this.anneeScolaireRepository.findAll().stream().map(anneescolaire -> 
				convertAnneeScolaireToDto(anneescolaire)).collect(Collectors.toList());
	
	}
// **********************************************
	  public Anneescolaire getAnnescolairebydate() {
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM");
      	Date s ;
      	
		  return null;
      	
      }
	
	 public  Anneescolaire verifier_date()  {
		  Calendar calendar = Calendar.getInstance();
		     Date date = new Date();
		    date = calendar.getTime();
		    String anneescolaire = null ; 
		     calendar.setTime(date);
		     int y = calendar.get(Calendar.YEAR);
		     int anneesuivante = y+1 ;
		     int annneeprecedante  = y-1 ;
		     System.out.println(calendar.get(Calendar.YEAR));
		   int m=  calendar.get(Calendar.MONTH);
		     System.out.println(calendar.get(Calendar.MONTH));
		     
		     if ( m>= 8) {
		    	anneescolaire = y+"-"+anneesuivante; 
		    	System.out.println(anneescolaire);
		     }
		     if(m<=7) {
		    	 anneescolaire = annneeprecedante +"-"+y;
		    		System.out.println(anneescolaire);
		     }
		     
		     Anneescolaire annee  = new Anneescolaire();
		     Anneescolaire a = new Anneescolaire();
		     a.setAnneeScolaire(anneescolaire);
		     annee = this.anneeScolaireRepository.findByAnneeScolaire(anneescolaire);
		     if (annee != null) {
		    	 return annee ;
		     }
		     else
		     {
		    	 annee=     this.anneeScolaireRepository.save(a) ; 
		    	   List<Semaine> listesdessemaine = this.semainerepository.findAll();
		    	   for ( int i =0 ;i<listesdessemaine.size();i++)
		    	   {
		    		   SemaineAnneeScolaire semaineannescolaire = new SemaineAnneeScolaire();
		    		   semaineannescolaire.setAnneeScolaire(annee);
		    		   semaineannescolaire.setS(listesdessemaine.get(i));
		    		   this.semaineannescolairerepository.save(semaineannescolaire);
		    	   }
		    		return annee;
		     }

		  
			
		

		}
}
