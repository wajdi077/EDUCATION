package com.dpc.Scolarity.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Dto.SemaineDTO;
import com.dpc.Scolarity.Repository.SemaineRepository;
@Service
public class SemaineService extends MainController {
	
	@Autowired
	SemaineRepository semainerepository ;
	

	public SemaineDTO saveInterval(SemaineDTO semaineDTO) {
		
		
		Semaine s = dtoToSemaine(semaineDTO);
		
		Semaine semaine = this.semainerepository.findBySemaine(s.getSemaine());
		semaine.setDatedebut(s.getDatedebut());
		semaine.setDatefin(s.getDatefin());
		Semaine semaincreated = this.semainerepository.save(semaine);
		SemaineDTO dto = convertSemaineToDto(semaincreated);
		
		return dto;
	}

	
	public SemaineDTO findOne(Long id) {
		Semaine s = this.semainerepository.findOne(id);
		SemaineDTO dto = convertSemaineToDto(s);
				return dto;
	}


	public void delete(Long id) {
		this.semainerepository.delete(id);
	}
public Semaine update(Semaine s ) {
	
	
	return this.semainerepository.save(s);
}
	
	public Collection<Semaine> findAllSemaine(){
		
		
		return this.semainerepository.findAll();
	}
public Collection<Semaine> findAllSemainebydate() throws ParseException{
		
	  Calendar calendar = Calendar.getInstance();
	     Date date = new Date();
	    date = calendar.getTime();
	    String anneescolaire = null ; 
	     calendar.setTime(date);
	     int y = calendar.get(Calendar.YEAR);
	     int m=  calendar.get(Calendar.MONTH);
	     int d =  calendar.get(Calendar.DAY_OF_MONTH);
	     System.out.println("le jour de la semaine est :"+d);
	     System.out.println("le mois de la semaine est :"+m);
	     System.out.println("le annes de la semaine est :"+y);
	     int day =d; 
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	     String madate = null ;
	     Date dateparse = null ;
	     madate= y+"-"+m+"-"+day;
	    	dateparse =     format.parse (madate );

		return this.semainerepository.findSemaineafterlocaldate();
	}
}
