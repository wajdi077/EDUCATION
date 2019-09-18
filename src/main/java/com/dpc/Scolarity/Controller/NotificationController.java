package com.dpc.Scolarity.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Note_enseignant;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Dto.DashboardDirecteurDTO;
import com.dpc.Scolarity.Dto.NoteProfDTO;
import com.dpc.Scolarity.Repository.NoteEnseignantRepository;
import com.dpc.Scolarity.service.implementation.MainController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/note")
public class NotificationController extends MainController {
	
	@Autowired
	NoteEnseignantRepository noterepos ;
	
	 @Autowired
	 private SimpMessagingTemplate template;

	    // Initialize Notifications
	
	  LocalDate localDate = LocalDate.now();
		LocalTime time = LocalTime.now();
		 String s = time.toString().substring(0, 5);


       
	
	 public  Predicate<Note_enseignant> isequaldatenow() {
         return p -> p.getDatenotification().toString().equals(localDate.toString()) ;
     }
	 public  Predicate<Note_enseignant> isequaltimenow() {
         return p -> p.getTime().toString().equals(s) ;
     }
	
	@RequestMapping(value = "/allnotesbynow", method = RequestMethod.GET)
    public Collection<NoteProfDTO> getallnotes() {
		System.out.println("timee"+s);
		
	     List<NoteProfDTO> listedtos = new ArrayList<NoteProfDTO>();

	     listedtos= this.noterepos.findAll().stream().filter(isequaldatenow().and(isequaltimenow())).
	    		 map(note ->convertNoteToNoteDTO(note))
				.collect(Collectors.toList());
		 	Notifications notifications = new Notifications(listedtos.size());
			System.out.println("time de jours "+s);

	     
	        template.convertAndSend("/topic/notification", notifications);

	         return listedtos ;

}
	
	
	
	
	
	
	
	
	
	
	

}
