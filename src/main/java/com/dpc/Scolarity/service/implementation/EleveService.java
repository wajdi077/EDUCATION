package com.dpc.Scolarity.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Authority;
import com.dpc.Scolarity.Domain.Classeleves;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.IAuthority;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.ProgrammeRepository;


import ch.qos.logback.core.net.SyslogOutputStream;
@Service
public class EleveService 	extends MainController {
	
	@Autowired
	EleveRepository eleverepository ;
	@Autowired
	ProgrammeRepository progrepo ; 
	@Autowired
	IUtilisateur userepo ; 
	@Autowired
	ClassesRepository classerepo ; 
	@Autowired
	IAuthority rolerepo ;
	@Autowired
	MailServiceImp mailservice ;
	public boolean save(EleveDTO eleveDTO) {
		Authority role = this.rolerepo.findByDescription("Parrain");
		Utilisateur u = this.userepo.findByUsername(eleveDTO.getUsername());
		//user.setAuthorities(role);
		//boolean valid = this.mailservice.emailValidator(user.getEmail());
		//	if (valid == true ) {
		//user.setProfil("Parrain");
		//Utilisateur u = this.userepo.save(user);
	Eleve e =dtoToEleve(eleveDTO);
	e.setEtablissement(u.getEtablissement());
	e.setArchiver(false);
	String a = e.getSexe().substring(0,1); 
	
	//e.setParrain(u);
	Eleve el = this.eleverepository.save(e);
	Long id = el.getId();
	String ids = id.toString();
	el.setDateInscription(new Date(System.currentTimeMillis()));
	el.setNumInscription(a+ids);
	
	Eleve eleve  = this.eleverepository.save(el);
	EleveDTO elevedto = convertEleveToDto(eleve);
	//mailservice.EnvoyerEmail(u);
		return true;
		//}
	//return false ;
	}
	public boolean saveEleveParent(EleveDTO eleveDTO,Utilisateur user) {
		Authority role = this.rolerepo.findByDescription("Parrain");
		user.setAuthorities(role);
		user.setParain(true);
		Utilisateur us = this.userepo.findByUsername(eleveDTO.getUsername());
		boolean valid = this.mailservice.emailValidator(user.getEmail());
	if (valid == true ) {
	user.setProfil("Parrain");
	user.setArchiver(false);
	user.setUsername(user.getEmail());
	user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
	user.setEnabled(true);
	user.setPassword(user.getNom()+user.getPrenom()+"123456");
	Utilisateur u = this.userepo.save(user);
	Eleve e =dtoToEleve(eleveDTO);
	String a = e.getSexe().substring(0,1); 
	e.setArchiver(false);
	e.setEtablissement(us.getEtablissement());
	//e.setParrain(u);
	Eleve el = this.eleverepository.save(e);
	Long id = el.getId();
	String ids = id.toString();
	el.setDateInscription(new Date(System.currentTimeMillis()));
	el.setNumInscription(a+ids);
	el.setParrain(u);
	Eleve eleve  = this.eleverepository.save(el);
	EleveDTO elevedto = convertEleveToDto(eleve);
	mailservice.EnvoyerEmail(u);
		return true;
		}
	return false ;
	}

	
	public boolean affecterEleveToParrain(Eleve e , Utilisateur user) {
		
		
		
		e.setParrain(user);
		this.eleverepository.save(e);
		return true;
		
		
		
		
	}
	
	public EleveDTO findOne(Long id) {
		Eleve e = this.eleverepository.findOne(id);
		EleveDTO elevedto = convertEleveToDto(e);
		return elevedto;
	}
	public EleveDTO findBynumInscription(String  numInscription) {
		Eleve e = this.eleverepository.findByNumInscription(numInscription);
		EleveDTO elevedto = convertEleveToDto(e);
		
		return elevedto;
	
		
	}
	
	public EleveDTO findBynomEtPrenom(String nom, String prenom) {
		
		Eleve e = this.eleverepository.findByNomAndPrenom(nom, prenom);
		EleveDTO elevedto = convertEleveToDto(e);
		return elevedto;
		
	}
	public Collection<EleveDTO> findAllByNiveau(String niveau) {

		return this.eleverepository.findByNiveauEtude(niveau).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	public Collection<EleveDTO> findAllByclasse(String nomclasse) {

		return this.eleverepository.findByClasseActuel(nomclasse).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	public void delete(Long id) {

		this.eleverepository.delete(id);
	}
	public Collection<EleveDTO> findAll() {

		return this.eleverepository.findAll().stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	public Collection<EleveDTO> findAll(String username) {
		Utilisateur u = this.userepo.findByUsername(username);
		return this.eleverepository.findByEtablissementAndArchiverIsFalse(u.getEtablissement()).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	public Collection<EleveDTO> findAllarchiver(String username) {
		Utilisateur u = this.userepo.findByUsername(username);
		return this.eleverepository.findByEtablissementAndArchiverIsTrue(u.getEtablissement()).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	
	
	public Collection<EleveDTO> findAllByclassenow(String username ) {
		
		Utilisateur prof = new Utilisateur();
		prof = this.userepo.findByUsername(username);
		
		  Calendar calendar = Calendar.getInstance();
	     Date date = new Date();
	    date = calendar.getTime();
	    calendar.setTime(date);
	    LocalDateTime current = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = current.format(formatter);
	    int day = current.getDayOfMonth();
	    int h =current.getHour();
	    int min = current.getMinute();
	    int seconde =current.getSecond();
	    int y = current.getYear();
	    int mois=  current.getMonthValue();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String madate = null ;
	    
		Date dateparse = null ;
	    madate = y+"-"+mois+"-"+day+" "+h+":"+min+":"+seconde;
    	try {
			dateparse =     format.parse (formatDateTime );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	System.out.println("la date local est*****************************  :"+dateparse);
	
	
Programme p = this.progrepo.getprognow(prof);
if (p==null) {
	System.out.println("le programme est null");
}
else {
	
	System.out.println("il n'est pas null");
}

System.out.println("le programme edaaatest :"+dateparse);
String classe =  "";
classe = p.getClasse().getNomclasse().toString();
Classes c = new Classes();
c = p.getClasse();
System.out.println("la classe est :"+c.getNomclasse());
System.out.println("le programme est :"+p.getClasse().getNomclasse());
		return this.eleverepository.findByClasseActuel(p.getClasse().getNomclasse()).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	
	public Collection<EleveDTO> findClasseEncours( Long id ){
		System.out.println("l'id est "+id);
		Programme p = new Programme();
	p = this.progrepo.findOne(id);
	System.out.println("l'id programme est "+p.getId());

	p.setVerou(true);
	System.out.println("l'id verou est "+p.getVerou());

	p = progrepo.save(p);
	//Classes c = this.classerepo.findOne(p.getClasse().getId());
	System.out.println("la classe est  "+p.getClasse().getNomclasse());
		return this.eleverepository.findByClasseActuel(p.getClasse().getNomclasse()).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
		
		
		
	}
	
	
	public void AffecterEleveAuParrain (Utilisateur user,List<String> elevedto) {
		
		Eleve eleveAjouter ;
		Utilisateur u ;
	
		for(int i=0; i<elevedto.size();i++) {
			eleveAjouter  = null ;
			
		if (elevedto.get(i)!=null) {
			Utilisateur parent = new Utilisateur();
			eleveAjouter = this.eleverepository.findByNumInscription(elevedto.get(i));
			eleveAjouter.setParrain(user);			
			
			eleveAjouter = eleverepository.save(eleveAjouter);
			
			

		}
		
			
		
		}
	
	
	
	}
	
	
	public Collection<EleveDTO> findAllByParrain(String username) {
		
		Utilisateur user = new Utilisateur();
		user=this.userepo.findByUsername(username);
		
		

		return this.eleverepository.findByParrain(user).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	
	}
	
	public Map<String, String> getParrainOfEleve(String  numInscription) {
		Eleve eleve = this.eleverepository.findByNumInscription(numInscription);
		Map<String, String> hm = new HashMap<>();
	    hm.put("Prenom", eleve.getParrain().getNom());
	    hm.put("Nom", eleve.getParrain().getPrenom());
	    hm.put("Telephone", eleve.getParrain().getTelephone());
	    if(eleve.getParrain().getCodepostale() != null) {
	    	hm.put("Addresse", eleve.getParrain().getCodepostale().getNom());
	    	hm.put("codePostal", (eleve.getParrain().getCodepostale().getCode()).toString());
	    	hm.put("ville", eleve.getParrain().getCodepostale().getVille().getNom());
	    }
	    System.out.println("********-------------------************");
	    System.out.println(hm);
	    
		return hm;
	
		
	}
	
	
	
	public Collection<EleveDTO> getNonAffectedEleve(String username) {
		Utilisateur u = this.userepo.findByUsername(username);
		return this.eleverepository.findByEtablissementAndClasseActuelIsNull(u.getEtablissement()).stream().map(entity -> convertEleveToDto(entity)).collect(Collectors.toList());
	}

	
}
