/**
 * 
 */
package com.dpc.Scolarity.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.SemaineAnneeScolaire;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.CalendarEmploisDTO;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;
import com.dpc.Scolarity.Dto.EmploisDTO;
import com.dpc.Scolarity.Dto.HeuresDTO;
import com.dpc.Scolarity.Dto.JoursDTO;
import com.dpc.Scolarity.Dto.MatiereDTO;
import com.dpc.Scolarity.Dto.ProgrammeDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Dto.UserDTO;
import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.HeuresRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.JoursRepository;
import com.dpc.Scolarity.Repository.MatierRepository;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.Repository.SalleRepository;
import com.dpc.Scolarity.Repository.SemAnneeScolaireRepository;
import com.dpc.Scolarity.Repository.SemaineRepository;




/**
 * @author slim
 *
 */
@Service
public class EmploisServcie extends MainController {
	@Autowired
	ProgrammeRepository programmeRespository ;
	@Autowired
	ClassesRepository classesrepository ;
	@Autowired
	SalleRepository sallerepository ;
	@Autowired
	JoursRepository jourrepository ; 
	@Autowired
	IUtilisateur userrepository ; 
	@Autowired
	MatierRepository matiererepo ;
	@Autowired
	SemAnneeScolaireRepository semaineanneerepo ;
	@Autowired
	HeuresRepository heurerepo ; 
	@Autowired
	SemaineRepository semainerepo ;
	public void injectProgramme(String username )throws ParseException 
	{
		Utilisateur u =new Utilisateur();
		u = this.userrepository.findByUsername(username);
		Etablissement e = u.getEtablissement();
		List<Utilisateur> lisprof = this.userrepository.findByEtablissementAndArchiverIsFalseAndProfil(e, "Enseignant");
		List<Programme> programmes = new ArrayList<>();
		List<Semaine> listsemaine = this.semainerepo.findAll();
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		    String madate = null ;
		    String dateout = "2019-07-20 23:00:00";
		    		Date dateparse = null ;
		    		Date	datefin =null;
		for (Utilisateur utilisateur : lisprof) {
			List<Programme> proprof = new ArrayList<>();
			
			proprof=this.programmeRespository.findByProf(utilisateur );
			for(Programme p:proprof) {
				//Date dateprog = p.getDateprog();
				if(p!=null) {
					Programme p1 = new Programme();
				
				p1.setDatefinprog(p.getDatefinprog());
				p1.setDateprog(p.getDateprog());
				p1.setClasse(p.getClasse());
				p1.setJour(p.getJour());
				p1.setHeure(p.getHeure());
				p1.setMatiere(p.getMatiere());
				p1.setProf(p.getProf());
				p1.setSalle(p.getSalle());
				p1.setStatut(p.getStatut());
				
				
		
			
				
				
				for (Semaine s : listsemaine)
					if(s.getSemaine()!="semaine0"){
				{
					Date d = s.getDatedebut();
	Calendar cal = Calendar.getInstance() ;
		    		p1.setSemaine(s);
		    		cal.setTime(d);
		    		int years = cal.get(Calendar.YEAR);
	    			int month = 	cal.get(Calendar.MONTH)+1;
	    		
	    			int day = cal.get(Calendar.DAY_OF_MONTH);
	    		    if ( p1.getJour().getJour().equals("Lundi")) {
	    		    	madate= years+"-"+month+"-"+day;
	    		    	dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" );
	    		    	datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		    	p1.setDateprog(dateparse);
	    		    	p1.setDatefinprog(datefin);
	    		    	p1.setDatedejour( formats.parse (madate));
	    		    }

	     if ( p1.getJour().getJour().equals("Mardi")) {
	    	 int mardi =day+1;
	    		madate= years+"-"+month+"-"+mardi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" ); 
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    		p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Mercredi")) {
	    	 int mercredi = day+2;
	    		madate= years+"-"+month+"-"+mercredi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" );
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Jeudi")) {
	    	 int jeudi = day+3;
	    		madate= years+"-"+month+"-"+jeudi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" ); 
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Vendredi")) {
	    	 int vendredi = day+4;
	    		madate= years+"-"+month+"-"+vendredi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" ); 
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Samedi")) {
	    	 int samedi =day+5;
	    		madate= years+"-"+month+"-"+samedi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" );
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
					
				}
				
				p1.setArchiver(false);
				this.programmeRespository.save(p1);
				}
				
			}
			
		
			}
		}
		
		
		
	} 
	public void AjouterProgramme (ProgrammeDTO p) throws ParseException 
	
	{
		
		Salle s = new Salle ();
				Classes c = new Classes();
		Utilisateur u = new Utilisateur () ;
		Jours  j = new Jours ();
		
		
		System.out.println("nomdu classe est "+p.getNomclasse());
		
		u = this.userrepository.findByUsername(p.getProf());
		System.out.println("nomdu prof est "+p.getProf());
		s =  this.sallerepository.findByNomSalle(p.getNomsalle());
Semaine semaine = this.semainerepo.findBySemaine(p.getSemaine());
SemaineAnneeScolaire semAnnee = this.semaineanneerepo.findByS(semaine);
		System.out.println("classedto"+c.getNomclasse());
		c = this.classesrepository.findByNomclasse(p.getNomclasse());
		System.out.println("classedto"+c.getNomclasse());
		j = this.jourrepository.findByJour(p.getJour());
		System.out.println("le jour est "+j.getJour());
		
		Matiere m = this.matiererepo.findByNommatiereFR(p.getNommatiere());
		System.out.println("classheeeeuuuuredto"+p.getHeure().getHeureDebut());
		Programme prog = new Programme();
		prog.setHeure(p.getHeure());
		prog.setClasse(c);
		System.out.println("salle"+s.getNomSalle());	
		System.out.println("classe"+c.getNomclasse());	
		System.out.println("user"+u.getNom());	
		prog.setClasse(c);
		prog.setSalle(s);;
		prog.setJour(j);

		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String madate = null ;
		    
		    		Date dateparse = null ;
		    		Date	datefin =null;
		    		//parsing date de la semaine 
		    		
		    		Date d = semaine.getDatedebut();
	
		    		
		    		Calendar cal = Calendar.getInstance() ;
		    		
		    		cal.setTime(d);
		    		int years = cal.get(Calendar.YEAR);
		    			int month = 	cal.get(Calendar.MONTH);
		    			System.out.println("le mois est   :"+month);
		    			int day = cal.get(Calendar.DAY_OF_MONTH);
		    
		    		System.out.println("le mois est :"+month);
		    		month = month +1;
		    // on va fixer la date d'insertion dans une semaine specifique du 8 emme mois
		    if ( prog.getJour().getJour().equals("Lundi")) {
		    	madate= years+"-"+month+"-"+day;
		    	dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" );
		    	datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );
		    }

 if ( prog.getJour().getJour().equals("Mardi")) {
	 int mardi =day+1;
		madate= years+"-"+month+"-"+mardi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Mercredi")) {
	 int mercredi = day+2;
		madate= years+"-"+month+"-"+mercredi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" );
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Jeudi")) {
	 int jeudi = day+3;
		madate= years+"-"+month+"-"+jeudi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Vendredi")) {
	 int vendredi = day+4;
		madate= years+"-"+month+"-"+vendredi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Samedi")) {
	 int samedi =day+5;
		madate= years+"-"+month+"-"+samedi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" );
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
		//Date dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
		SemaineAnneeScolaire semaineAnneeScolaire = new SemaineAnneeScolaire();
		semaineAnneeScolaire = this.semaineanneerepo.findOne((long) 51);
	prog.setSemaineAnneeScolaire(semAnnee);
	prog.setStatut("en attente du prof");
	prog.setDateprog(dateparse);
	prog.setDatefinprog(datefin);
		prog.setMatiere(m);
		prog.setTypeprog(p.getTypeprog());
		prog.setProf(u);
		
		prog = this.programmeRespository.save(prog);
		
		
		
		
	}
	
	
	public Collection<EmploisDTO> findByProfAndDay(String username , String jour) {
		
		
		Utilisateur u = new Utilisateur();
		u = this.userrepository.findByUsername(username);
		Jours day = new Jours();
		day = this.jourrepository.findByJour(jour);
		
		
		
		return this.programmeRespository.findByProfAndJour(u, day).stream().map(entity -> convertEmploisToDto(entity)).collect(Collectors.toList());
		
		
		
	}
	public Collection<EmploisDTO> findbyProf(String username){
		
		
		List <EmploisDTO> list = new ArrayList<>();
		List <CalendarEmploisDTO> listemplois = new ArrayList<>();
		Utilisateur u = new Utilisateur();
		System.out.println("1");
		u = this.userrepository.findByUsername(username);
		System.out.println("2");
		System.out.println("le professeur est "+ u.getNom());
		System.out.println("3");
		List<Programme> programmes = new ArrayList<>();
		programmes=	this.programmeRespository.findByProfOrderByDateprogAsc(u);
		System.out.println("4");
		List<Programme> sorted ; 
	
		list = this.programmeRespository.findByProf(u).stream().map(entity -> convertEmploisToDto(entity)).collect(Collectors.toList());
		for (int i =0;i<list.size();i++) {
			System.out.println("test1");
			System.out.println(list.get(i));
			System.out.println("test11");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));  
			System.out.println("test111");
			//sdf.setTimeZone(TimeZone.getTimeZone("GMT"));  
			System.out.println("test1111");
			System.out.println(sdf.format(list.get(i).getDatefinprog()));
			System.out.println("test11111");
			list.get(i).setStart(list.get(i).getDateprog().toString());
			list.get(i).setEnd(list.get(i).getDatefinprog().toString());
			System.out.println("toooostring");
			System.out.println(list.get(i).getStart());
			String b = sdf.format(list.get(i).getDatefinprog());
		
			String a =sdf.format(list.get(i).getDateprog());
			
			System.out.println(a);
			list.get(i).setStart(a);
			list.get(i).setEnd(b);
			
		}
	//	return this.programmeRespository.findByProf(u).stream().map(entity -> convertEmploisToDto(entity)).collect(Collectors.toList());
		
		return list;
	
	}
	public Collection<EmploisDTO> findbyProfsORTEDbYdATE(String username){
		
		
		
		try {
		Utilisateur u = new Utilisateur();
		u = this.userrepository.findByUsername(username);
		System.out.println("le professeur est "+ u.getNom());
		List<Programme> programmes = this.programmeRespository.findByProfOrderByDateprogAsc(u);
		return this.programmeRespository.findByProf(u).stream().map(entity -> convertEmploisToDto(entity)).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
	public ProgrammeDTO findSeance(String username , String jour , String heure) {
		Utilisateur u = new Utilisateur();
		u = this.userrepository.findByUsername(username);
		UserDTO prof = convertUtilisateurToDto(u);
		Jours day = new Jours();
		day = this.jourrepository.findByJour(jour);
		Heures heures = new Heures();
		heures = this.heurerepo.findByHeureDebut(heure);
		MatiereDTO m  = new MatiereDTO();
		ClasseRoomDTO c = new ClasseRoomDTO();
		SalleDTO salle = new SalleDTO();
		JoursDTO j = new JoursDTO();
		HeuresDTO h = new HeuresDTO();
		
		m.setNommatiereFR("pas dematgiere pour cette seance maintenant");

		c.setNomclasse("pas de classe pour cette seance maintenant");
		c.setNiveau("******");
		c.setRemarque("******");
	
		ProgrammeDTO emploisdto = new ProgrammeDTO();
		emploisdto.setNommatiere(m.getNommatiereFR());
		
		emploisdto.setNomclasse(c.getNomclasse());
		emploisdto.setHeure(heures);
		emploisdto.setJour(jour);
		emploisdto.setProf(u.getPrenom());
		emploisdto.setTypeprog("repos");
		Programme emplois = this.programmeRespository.findByProfAndJourAndHeure(u, day, heures);
		ProgrammeDTO pdto =new ProgrammeDTO();
	
		EmploisDTO emp = new EmploisDTO() ;
	
		if (emplois!=null) {
			pdto.setHeure(heures);
			pdto.setJour(jour);
			pdto.setNomclasse(emplois.getClasse().getNomclasse());
			pdto.setNommatiere(emplois.getMatiere().getNommatiereFR());
			pdto.setNomsalle(emplois.getSalle().getNomSalle());
			pdto.setProf(u.getPrenom());
			pdto.setTypeprog(emplois.getTypeprog());
			pdto.setRemarque("sans remarque ");
			return pdto;
			
		}
		
			
			return emploisdto ;  
		
	
		
	
		
		
		
		
	}
	
	
	// emplois 2 emme version 
	
	public EmploisDTO  getEmploisdetempEnseignant(String username , String jour , String heure) {
		Utilisateur u = new Utilisateur();
		u = this.userrepository.findByUsername(username);
		UserDTO prof = convertUtilisateurToDto(u);
		Jours day = new Jours();
		day = this.jourrepository.findByJour(jour);
		Heures heures = new Heures();
		heures = this.heurerepo.findByHeureDebut(heure);
		MatiereDTO m  = new MatiereDTO();
		ClasseRoomDTO c = new ClasseRoomDTO();
		SalleDTO salle = new SalleDTO();
		JoursDTO j = new JoursDTO();
		HeuresDTO h = new HeuresDTO();
		j=convertJoursToDto(day);
		h=convertHeuresToDto(heures);
		salle.setNomSalle("--");
		salle.setRemarque("--");
		salle.setType("--");
		m.setNommatiereFR("--");

		c.setNomclasse("--");
		c.setNiveau("null");
		c.setRemarque("******");
	
		EmploisDTO emploisdto = new EmploisDTO();
		emploisdto.setClasse(c);
		emploisdto.setSalle(salle);
emploisdto.setHeure(h);
emploisdto.setJour(j);
emploisdto.setMatiere(m);

emploisdto.setTypeprog("--");
		Programme emplois = this.programmeRespository.findByProfAndJourAndHeure(u, day, heures);
		EmploisDTO pdto =new EmploisDTO();
	
		EmploisDTO emp = new EmploisDTO() ;
	
		if (emplois!=null) {
		
		pdto=convertEmploisToDto(emplois);
			return pdto;
			
		}
		
			
			return emploisdto ;  
		
	
		
		
	}
	
						// update emplois ****************---------
	
	public void updateEmplois(EmploisDTO emplois) throws ParseException {
		 String madate = null ;
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		Date dateparse = null ;
 		Date	datefin =null;
		Matiere matiere = new Matiere() ;
		Classes myclass= new Classes();
		Utilisateur user = new Utilisateur();
		Jours jour = new Jours();
		Salle salle = new Salle();
		Heures heure = new Heures();
		Programme prog = new Programme();
	Semaine semaine = new Semaine();
	
		if (emplois !=null) {
			if(emplois.getMatiere().getNommatiereFR()!="--")
			{
				prog = this.programmeRespository.getOne(emplois.getId());
		matiere =this.matiererepo.findByNommatiereFR(emplois.getMatiere().getNommatiereFR());
			myclass = this.classesrepository.findByNomclasse(emplois.getClasse().getNomclasse());
			user=this.userrepository.findByNomAndPrenom(emplois.getUser().getNom(), emplois.getUser().getPrenom());
			jour =this.jourrepository.findByJour(emplois.getJour().getJour());
			heure=this.heurerepo.findByHeureDebut(emplois.getHeure().getHeureDebut());
			semaine=this.semainerepo.findBySemaine(emplois.getSemaine().getSemaine());
			
			salle=this.sallerepository.findByNomSalle(emplois.getSalle().getNomSalle());
			   prog.setClasse(myclass);
			   prog.setSalle(salle);
			   prog.setJour(jour);
			   prog.setHeure(heure);
			   prog.setProf(user);
			   prog.setMatiere(matiere);
			Date d = semaine.getDatedebut();
			Calendar cal = Calendar.getInstance();
    		cal.setTime(d);
    		int month = cal.get(Calendar.MONTH);
    		int years = cal.get(Calendar.YEAR);
    		int day = cal.get(Calendar.DAY_OF_MONTH);
    		//test lundi 
    		  // on va fixer la date d'insertion dans une semaine specifique du 8 emme mois
		    if ( prog.getJour().getJour().equals("Lundi")) {
		    	madate= years+"-"+month+"-"+day;
		    	dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" );
		    	datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );
		    }

 if ( prog.getJour().getJour().equals("Mardi")) {
	 int mardi =day+1;
		madate= years+"-"+month+"-"+mardi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Mercredi")) {
	 int mercredi = day+2;
		madate= years+"-"+month+"-"+mercredi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" );
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Jeudi")) {
	 int jeudi = day+3;
		madate= years+"-"+month+"-"+jeudi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Vendredi")) {
	 int vendredi = day+4;
		madate= years+"-"+month+"-"+vendredi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" ); 
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
 if ( prog.getJour().getJour().equals("Samedi")) {
	 int samedi =day+5;
		madate= years+"-"+month+"-"+samedi;
	  dateparse =     format.parse (madate+" "+ prog.getHeure().getHeureDebut()+":00:00" );
	  datefin =     format.parse (madate+" "+ prog.getHeure().getHeureFin()+":00:00" );

 }
   prog.setStatut("en attente du prof");
	prog.setDateprog(dateparse);
	prog.setDatefinprog(datefin);
	prog = this.programmeRespository.save(prog);
			
			}
		}
		
		
		
	}
	public void AnnulerEmplois() {
		
	}
	
	public void modifieremplois(ProgrammeDTO p)
	{
		Salle s = new Salle ();
		Classes c = new Classes();
Utilisateur u = new Utilisateur () ;
Jours  j = new Jours ();


System.out.println("nomdu classe est "+p.getNomclasse());

Programme prog = new Programme();
System.out.println("nomdu prof est "+p.getNomclasse());
System.out.println("nomdu prof est "+p.getNommatiere());
System.out.println("nomdu prof est "+p.getNomsalle());
System.out.println("nomdu prof est "+p.getId());




Matiere m = this.matiererepo.findByNommatiereFR(p.getNommatiere());
c =this.classesrepository.findByNomclasse(p.getNomclasse());
s =  this.sallerepository.findByNomSalle(p.getNomsalle());


prog = this.programmeRespository.findOne(p.getId());
prog.setClasse(c);

prog.setSalle(s);;

prog.setStatut("en attente du prof");

prog.setMatiere(m);
prog.setTypeprog("cours");

prog = this.programmeRespository.save(prog);

		
		
		
	}
	
	public EmploisDTO findProgById(long id ) {
		Programme p = this.programmeRespository.findOne(id);
		EmploisDTO emplois =convertEmploisToDto(p);
		String start = p.getDateprog().toLocaleString();
		String end = p.getDatefinprog().toString();
		emplois.setStart(start);
		emplois.setEnd(end);
		
		return  emplois;
		
		
		
	}
	
	public Boolean injectEmploisToWeak(String username , String semaine) throws ParseException
	{
		Semaine s = new Semaine();
		s=this.semainerepo.findBySemaine(semaine);
		Semaine semainezero=this.semainerepo.findBySemaine("semaine0");
		Utilisateur u =new Utilisateur();
		u = this.userrepository.findByUsername(username);
		Etablissement e = u.getEtablissement();
		List<Utilisateur> lisprof = this.userrepository.findByEtablissementAndArchiverIsFalseAndProfil(e, "Enseignant");
		List<Programme> programmes = new ArrayList<>();
	
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		    String madate = null ;
		    String dateout = "2019-07-20 23:00:00";
		    		Date dateparse = null ;
		    		Date	datefin =null;
		for (Utilisateur utilisateur : lisprof) {
			List<Programme> proprof = new ArrayList<>();
			
			proprof=this.programmeRespository.findByProfAndSemaine(utilisateur, semainezero);
			for(Programme p:proprof) {
				
				if(p!=null) {
					Programme p1 = new Programme();
				
				p1.setDatefinprog(p.getDatefinprog());
				p1.setDateprog(p.getDateprog());
				p1.setClasse(p.getClasse());
				p1.setJour(p.getJour());
				p1.setHeure(p.getHeure());
				p1.setMatiere(p.getMatiere());
				p1.setProf(p.getProf());
				p1.setSalle(p.getSalle());
				p1.setStatut(p.getStatut());
				
				p1.setArchiver(false);
		
	
				
				
				
					
				
					Date d = s.getDatedebut();
	Calendar cal = Calendar.getInstance() ;
		    		p1.setSemaine(s);
		    		cal.setTime(d);
		    		int years = cal.get(Calendar.YEAR);
	    			int month = 	cal.get(Calendar.MONTH)+1;
	    		
	    			int day = cal.get(Calendar.DAY_OF_MONTH);
	    		    if ( p1.getJour().getJour().equals("Lundi")) {
	    		    	madate= years+"-"+month+"-"+day;
	    		    	dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" );
	    		    	datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		    	p1.setDateprog(dateparse);
	    		    	p1.setDatefinprog(datefin);
	    		    	p1.setDatedejour( formats.parse (madate));
	    		    }

	     if ( p1.getJour().getJour().equals("Mardi")) {
	    	 int mardi =day+1;
	    		madate= years+"-"+month+"-"+mardi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" ); 
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    		p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Mercredi")) {
	    	 int mercredi = day+2;
	    		madate= years+"-"+month+"-"+mercredi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" );
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Jeudi")) {
	    	 int jeudi = day+3;
	    		madate= years+"-"+month+"-"+jeudi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" ); 
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Vendredi")) {
	    	 int vendredi = day+4;
	    		madate= years+"-"+month+"-"+vendredi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" ); 
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
	     if ( p1.getJour().getJour().equals("Samedi")) {
	    	 int samedi =day+5;
	    		madate= years+"-"+month+"-"+samedi;
	    	  dateparse =     format.parse (madate+" "+ p1.getHeure().getHeureDebut()+":00:00" );
	    	  datefin =     format.parse (madate+" "+ p1.getHeure().getHeureFin()+":00:00" );
	    		p1.setDateprog(dateparse);
		    	p1.setDatefinprog(datefin);
	    	  p1.setDatedejour( formats.parse (madate));

	     }
					
				
				
				p1.setArchiver(false);
				this.programmeRespository.save(p1);
				
				
			}
			
		
			}
		}
		return true ; 
	}
}
