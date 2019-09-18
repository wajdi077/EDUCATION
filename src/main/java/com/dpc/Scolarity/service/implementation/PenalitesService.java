/**
 * 
 */
package com.dpc.Scolarity.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Type_Penalites;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AbsenceEleveDTO;
import com.dpc.Scolarity.Dto.EleveDetailsDTO;
import com.dpc.Scolarity.Dto.NotificationPenalitesDTO;
import com.dpc.Scolarity.Dto.PenalitesDTO;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.PenaliteRepository;
import com.dpc.Scolarity.Repository.TypePenalitesRepository;

/**
 * @author slim
 *
 */

@Service
public class PenalitesService extends MainController{
	@Autowired
	PenaliteRepository penalitesrepo ;
	@Autowired
	TypePenalitesRepository typerepo ;
	@Autowired
	EleveRepository elrepo ;
	@Autowired
	MailServiceImp mailservice ;
    @Autowired 
    IUtilisateur iUtilisateur;
public void save(PenalitesDTO pen) {
	
	Penalites penalites = dtoToPenalites(pen);
	
			Penalites p = this.penalitesrepo.save(penalites);
		
	}
public NotificationPenalitesDTO exclus(Programme programme,PenalitesDTO pen) {
	Utilisateur parent = new Utilisateur();

	Penalites penalites = new Penalites();
	String motif ="exclus";
	Type_Penalites type = new Type_Penalites();
	System.out.println("leleve est "+pen.getNumInscription());
	type = this.typerepo.findByNom(motif);
	penalites.setMotif("exclus") ;
	penalites.setProgramme(programme);
	penalites.setRemarque(pen.getRemarque());
	penalites.setType(type);
	Eleve e = new Eleve();
	System.out.println("****************************************************");
	e=this.elrepo.findByNumInscription(pen.getNumInscription());
	System.out.println("****************************************************");
	System.out.println("leleve est "+e.getNumInscription());

		
	
		
		if( e.getParrain()!=null) {
		parent = e.getParrain();
		String email =e.getParrain().getEmail();
		this.mailservice.EnvoyerEmailExclus(parent, programme, e);
		penalites.setDate_Penalites(new Date(System.currentTimeMillis()));
		penalites.setEleves(e);
		System.out.println("****************************************************");
		Penalites p = this.penalitesrepo.save(penalites);
		
		}
	penalites.setDate_Penalites(new Date(System.currentTimeMillis()));
	penalites.setEleves(e);
	System.out.println("****************************************************");
	Penalites p = this.penalitesrepo.save(penalites);
	return convertPenalitesToNotificationPenalitesDTO(p) ;
	
}
public List<NotificationPenalitesDTO> findallpenalites()
{
	return this.penalitesrepo.findAll().stream().map(entity -> convertPenalitesToNotificationPenalitesDTO(entity)).collect(Collectors.toList());
	}



public List<NotificationPenalitesDTO> findallexclus(String username)
{List<NotificationPenalitesDTO> listpenaites = null ; 
listpenaites = new  ArrayList<NotificationPenalitesDTO>();
listpenaites =this.penalitesrepo.findByMotif("exclus").stream().map(entity -> convertPenalitesToNotificationPenalitesDTO(entity)).collect(Collectors.toList());
Utilisateur u = this.iUtilisateur.findByUsername(username);
List<NotificationPenalitesDTO> notiflist = new  ArrayList<NotificationPenalitesDTO>(); 
notiflist = listpenaites.stream().filter((n-> n.getEleves().getEtablissement().getLibelle()==u.getEtablissement().getLibelle())).collect(Collectors.toList());
	return notiflist;
	}

public List<NotificationPenalitesDTO> findallretenu(String username)
{List<NotificationPenalitesDTO> listpenaites = null ; 
listpenaites = new  ArrayList<NotificationPenalitesDTO>();
listpenaites =this.penalitesrepo.findByMotif("retenu").stream().map(entity -> convertPenalitesToNotificationPenalitesDTO(entity)).collect(Collectors.toList());
Utilisateur u = this.iUtilisateur.findByUsername(username);
List<NotificationPenalitesDTO> notiflist = new  ArrayList<NotificationPenalitesDTO>(); 
notiflist = listpenaites.stream().filter((n-> n.getEleves().getEtablissement().getLibelle()==u.getEtablissement().getLibelle())).collect(Collectors.toList());
	return notiflist;
	}
public List<NotificationPenalitesDTO> findallavertissement(String username)
{List<NotificationPenalitesDTO> listpenaites = null ; 
listpenaites = new  ArrayList<NotificationPenalitesDTO>();
listpenaites =this.penalitesrepo.findByMotif("avertissement").stream().map(entity -> convertPenalitesToNotificationPenalitesDTO(entity)).collect(Collectors.toList());
Utilisateur u = this.iUtilisateur.findByUsername(username);
List<NotificationPenalitesDTO> notiflist = new  ArrayList<NotificationPenalitesDTO>(); 
notiflist = listpenaites.stream().filter((n-> n.getEleves().getEtablissement().getLibelle()==u.getEtablissement().getLibelle())).collect(Collectors.toList());
	return notiflist;
	}


public void retenu(Programme programme,PenalitesDTO pen) {
	Penalites penalites = new Penalites();
	String motif ="retenu";
	Type_Penalites type = new Type_Penalites();
	System.out.println("leleve est "+pen.getNumInscription());
	type = this.typerepo.findByNom(motif);
	penalites.setMotif("retenu") ;
	penalites.setProgramme(programme);
	penalites.setRemarque(pen.getRemarque());
	penalites.setType(type);
	Eleve e = new Eleve();
	System.out.println("****************************************************");
	e=this.elrepo.findByNumInscription(pen.getNumInscription());
	System.out.println("****************************************************");
	System.out.println("leleve est "+e.getNumInscription());
	penalites.setDate_Penalites(new Date(System.currentTimeMillis()));
	penalites.setEleves(e);
	System.out.println("****************************************************");
	Penalites p = this.penalitesrepo.save(penalites);
	
}
public void AddAverssement(Programme programme,PenalitesDTO pen) {
	Penalites penalites = new Penalites();
	String motif ="avertissement";
	Type_Penalites type = new Type_Penalites();
	System.out.println("leleve est "+pen.getNumInscription());
	type = this.typerepo.findByNom(motif);
	penalites.setMotif("avertissement") ;
	penalites.setProgramme(programme);
	penalites.setRemarque(pen.getRemarque());
	penalites.setType(type);
	Eleve e = new Eleve();
	System.out.println("****************************************************");
	e=this.elrepo.findByNumInscription(pen.getNumInscription());
	System.out.println("****************************************************");
	System.out.println("leleve est "+e.getNumInscription());
	penalites.setDate_Penalites(new Date(System.currentTimeMillis()));
	penalites.setEleves(e);
	System.out.println("****************************************************");
	Penalites p = this.penalitesrepo.save(penalites);
	
}
public int countExcclusbyday() throws ParseException {
	
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
		
		
	Type_Penalites type =new Type_Penalites();
	type=	this.typerepo.findByNom("exclus");
	int a ;
	 a=this.penalitesrepo.countByPenalitesBydateAndType(dateparse, type);
	 System.out.println("exclus "+a);
	 return a;
	
	
}
public int countRetenuByDay() throws ParseException {
	
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
		
		
	Type_Penalites type =new Type_Penalites();
	type=	this.typerepo.findByNom("retenu");
	int a ;
	a =this.penalitesrepo.countByPenalitesBydateAndType(dateparse, type);
	 System.out.println("retenu "+a);
	return a;
	
	
	
}

public List<AbsenceEleveDTO> findPenalitesByEleve(String numInscription)
{
	
	 List<AbsenceEleveDTO> listabsence =new ArrayList<>();
	Eleve e = this.elrepo.findByNumInscription(numInscription);
	if(e!=null) {
	List<Penalites> list=this.penalitesrepo.findByEleves(e);
	
	if(list!=null) {
	for (int i=0;i<list.size();i++) {
		
		AbsenceEleveDTO a= new AbsenceEleveDTO();
		a.setMatiere(list.get(i).getProgramme().getMatiere().getNommatiereFR());
		a.setNomClasse(list.get(i).getProgramme().getClasse().getNomclasse());
		a.setPrenomEleve(list.get(i).getEleves().getPrenom()+","+list.get(i).getEleves().getNom());
		a.setDateAbsence(list.get(i).getDate_Penalites().toString());
		a.setType(list.get(i).getMotif());
		a.setRemarque(list.get(i).getRemarque());
		listabsence.add(a);
		}
	return listabsence;	
	}
	}
	return listabsence;	
}

public List<Object>  getEleveByMotif(String numInscription){
	List<Object>  eleveDetailsDTO = new ArrayList<>();
	Eleve eleve = this.elrepo.findByNumInscription(numInscription);
	eleveDetailsDTO = this.penalitesrepo.getEleveByMotif(eleve);
	return eleveDetailsDTO;
}

}
