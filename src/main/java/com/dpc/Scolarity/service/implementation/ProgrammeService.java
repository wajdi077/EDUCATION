package com.dpc.Scolarity.service.implementation;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.*;
import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.ProgrammeRepository;
import com.dpc.Scolarity.Repository.SemaineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgrammeService extends MainController{

    @Autowired
    private EleveService eleveService;
    @Autowired
    private SemaineRepository semainerepo ; 
    @Autowired
    private  ClasseRoomService classeRoomService;

//    @Autowired
//    private ClassesRepository classesRepository;
//
    @Autowired
    private ProgrammeRepository programmeRepository;
    @Autowired
    ClassesRepository classerepo ;
    @Autowired
    IUtilisateur userrepo ; 
    public List<ProgrammeEmploiDTO> emploiByEleve(String numInscription){
        System.out.println("Bonjour: "+numInscription);
        EleveDTO eleve=this.eleveService.findBynumInscription(numInscription);

        System.out.println("getClasseActuel():"+eleve.getClasseActuel());
        ClasseRoomDTO class_eleve= classeRoomService.findByNomClasse(eleve.getClasseActuel());

  //      System.out.println("class_eleve:"+class_eleve);
        List<Programme> programmes = this.programmeRepository.findByClasse_Id(class_eleve.getId());
        List<ProgrammeEmploiDTO> programmeDTOs = programmes.stream()
                    .map(programme -> convertProgrammeToProgrammeEmploiDTO(programme))
                    .collect(Collectors.toList());

       // return programmes;
        return  programmeDTOs;
    }
    public List<ProgrammeEmploiDTO> emploisbyclasse(Long id){
     
        

     
        ClasseRoomDTO class_eleve= classeRoomService.findById(id);
//
  //      System.out.println("class_eleve:"+class_eleve);
        List<Programme> programmes = this.programmeRepository.findByClasse_Id(class_eleve.getId());
        List<ProgrammeEmploiDTO> programmeDTOs = programmes.stream()
                    .map(programme -> convertProgrammeToProgrammeEmploiDTO(programme))
                    .collect(Collectors.toList());

       // return programmes;
        return  programmeDTOs;
    }
    
    public List<ProgrammeEmploiDTO> emploisbyEnseignant(String username){
    	
        Semaine s = new Semaine ();
        List<Semaine> list  = new ArrayList<>();
        list= this.semainerepo.findAll();
Utilisateur enseignant = new Utilisateur();
enseignant= this.userrepo.findByUsername(username);
     
//
  //      System.out.println("class_eleve:"+class_eleve);
        List<Programme> programmes = this.programmeRepository.findByProf(enseignant);
        List<ProgrammeEmploiDTO> programmeDTOs = programmes.stream()
                    .map(programme -> convertProgrammeToProgrammeEmploiDTO(programme))
                    .collect(Collectors.toList());
        List<ProgrammeEmploiDTO> listfinal = new ArrayList<ProgrammeEmploiDTO>();
        Calendar now = Calendar.getInstance();
        
  
        Calendar dateprog = Calendar.getInstance();
        
       // return programmes;
     for (int i =0 ; i<programmeDTOs.size();i++)
     { 
     if(programmeDTOs.get(i).getDatedejour()!=null) {
    	 dateprog.setTime(programmeDTOs.get(i).getDatedejour());
	 System.out.println("date du jour "+programmeDTOs.get(i).getDateprog());
	 System.out.println("date now"+now);
    	 System.out.println("week of prog "+dateprog.get(Calendar.WEEK_OF_YEAR));
    	 System.out.println("week of now "+now.get(Calendar.WEEK_OF_YEAR));
    	
    	
    	if(dateprog.get(Calendar.WEEK_OF_YEAR)== now.get(Calendar.WEEK_OF_YEAR)) 
    	{
    	System.out.println("week of prog "+dateprog.get(Calendar.WEEK_OF_YEAR));	
    	System.out.println("week of now "+now.get(Calendar.WEEK_OF_YEAR));
    		listfinal.add(programmeDTOs.get(i));
    	}
     }}
        return  listfinal;
    }
}
