/**
 * 
 */
package com.dpc.Scolarity.service.implementation;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Classeleves;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AbsenceEleveDTO;
import com.dpc.Scolarity.Dto.EmploisDTO;
import com.dpc.Scolarity.Repository.AbsenceRepository;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.GenerationTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author slim
 *
 */
@Service
public class AbsencesService extends MainController{
	@Autowired
	EleveRepository eleverepository ; 
	@Autowired
	ProgrammeRepository seancerepository ;
	@Autowired
	AbsenceRepository absencerepo ; 
	@Autowired 
	IUtilisateur parentrepo;
	@Autowired
	MailServiceImp mailservice ;
	public void AddAbsence (Programme seancedto,List<String> elevedto) {
	
		Eleve eleveAjouter ;
	Programme p ;
	p = this.seancerepository.findOne(seancedto.getId());
		String a = "absent";
		
		Utilisateur parent = new Utilisateur();
		for(int i=0; i<elevedto.size();i++) {
			eleveAjouter  = null ;
			parent=null;
		if (elevedto.get(i)!=null) {
			AbsencesEleve absence = new AbsencesEleve();
		
			eleveAjouter = this.eleverepository.findByNumInscription(elevedto.get(i));
			if( eleveAjouter.getParrain()!=null) {
			parent = eleveAjouter.getParrain();
			String email =eleveAjouter.getParrain().getEmail();
			this.mailservice.EnvoyerEmailAbsence(parent, p, eleveAjouter);
			
			}
			absence.setE(eleveAjouter);
absence.setDate_absence(new Date(System.currentTimeMillis()));
		absence.setDescription("absent");
		absence.setProgramme(p);
	
		
	AbsencesEleve ab = this.absencerepo.save(absence);
			
		
			

		}
		
			
		
		}
	
	
	
	}

public int countAbsenceByDay() throws ParseException {
		
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC+01:00"));
		Date d ;
		   d = (Date) c.getTime();
		   c.setTime(d);
		   int y = c.get(Calendar.YEAR);
		   int m = c.get(Calendar.MONTH);
		   int day =c.get(Calendar.DAY_OF_MONTH);
		   LocalDateTime current = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formatDateTime = current.format(formatter);
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String madate = null ;
			Date dateparse = null ;
			madate= y+"-"+m+"-"+day;
			System.out.println("years"+y);
		
			System.out.println("mo,nth"+m);
			System.out.println("day"+day);
			
			dateparse =     format.parse (madate+" "+"00:00:00" );
			System.out.println("datecompletre"+dateparse);
			int absence = 0;
		 absence =this.absencerepo.CountAbsenceByDate(dateparse);
		
		
		 
		return this.absencerepo.CountAbsenceByDate(dateparse) ;
		
		
	}
public List<AbsenceEleveDTO> findAbsenceByEleve(String numInscription)
{
	
	 List<AbsenceEleveDTO> listabsence =new ArrayList<>();
	Eleve e = this.eleverepository.findByNumInscription(numInscription);
	if(e!=null) {
	List<AbsencesEleve> list=this.absencerepo.findByE(e);
	
	if(list!=null) {
	for (int i=0;i<list.size();i++) {
		
		AbsenceEleveDTO a= new AbsenceEleveDTO();
		a.setMatiere(list.get(i).getProgramme().getMatiere().getNommatiereFR());
		a.setNomClasse(list.get(i).getProgramme().getClasse().getNomclasse());
		a.setPrenomEleve(list.get(i).getE().getPrenom()+","+list.get(i).getE().getNom());
		a.setDateAbsence(list.get(i).getDate_absence().toString()+ " , de :  "
		+list.get(i).getProgramme().getHeure().getHeureDebut()
				+" H  à :"+list.get(i).getProgramme().getHeure().getHeureFin()+"  H");
		listabsence.add(a);
		}
	return listabsence;	
	}
	}
	return listabsence;	
}

	
}
