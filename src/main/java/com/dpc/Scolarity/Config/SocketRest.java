package com.dpc.Scolarity.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.NotificationPenalitesDTO;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.EtablissementRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.PenaliteRepository;
import com.dpc.Scolarity.service.implementation.PenalitesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/socket",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class SocketRest {
    @Autowired

    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    PenalitesService servicespenalites;
    @Autowired 
    IUtilisateur iUtilisateur;
    @Autowired
	EtablissementRepository etabrepo;
    @Autowired
    PenaliteRepository penrepo ;
    @Autowired
    EleveRepository eleverepo ;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> useSimpleRest(@RequestBody  Map<String, String> message){
        if(message.containsKey("message")){
        	//if the toId is present the message will be sent privately else broadcast it to all users
            if(message.containsKey("toId") && message.get("toId")!=null && !message.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+message.get("toId"),message);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+message.get("fromId"),message);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",message);
            }
            return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/getnotif",method = RequestMethod.GET)
    public ResponseEntity<?> getnotif( String username){
    	Utilisateur u = this.iUtilisateur.findByUsername(username);
    	Penalites penalites = new Penalites();
    	List<NotificationPenalitesDTO> listpenaites = null ; 
    	listpenaites = new  ArrayList<NotificationPenalitesDTO>();
    	listpenaites= this.servicespenalites.findallpenalites();
    	List<NotificationPenalitesDTO> notiflist = new  ArrayList<NotificationPenalitesDTO>(); 
    	notiflist = listpenaites.stream().filter((n-> n.getEleves().getEtablissement()
    			.getLibelle()==u.getEtablissement().getLibelle())).collect(Collectors.toList());
    	
    	Map<String, String> message = null ;
       
        	//System.out.println("request mapping in if1 "+notiflist.toString());
        	//if the toId is present the message will be sent privately else broadcast it to all users
            
            	System.out.println("request mapping in if2 ");
               // this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+notiflist,notiflist);
               
               // this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+notiflist,message);
           
               // this.simpMessagingTemplate.convertAndSend("/socket-publisher",notiflist);
            
            return new ResponseEntity<>(notiflist, new HttpHeaders(), HttpStatus.OK);
        
      //  return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @MessageMapping("/send/message")
    public Map<String, String> useSocketCommunication(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
        	System.out.println("try ");
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
        	System.out.println("request mapping in if 2222");
          
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("fromId"),message);
          
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
            
        }
        return messageConverted;
    }
    @MessageMapping("/send/messageretenu")
    public Map<String, String> useSocketCommunicationretenu(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
        	System.out.println("try ");
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
  
        	System.out.println("request mapping in if 2222");
            
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("fromId"),message);
        
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
          
        
        return messageConverted;
    }
    @MessageMapping("/send/NotificationExclus")
    public Map<String, String> NotificationExclus(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
        	System.out.println("try ");
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
        	System.out.println("request mapping in if 2222");
            if(messageConverted.containsKey("toId") && messageConverted.get("toId")!=null && !messageConverted.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("fromId"),message);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
            }
        }
        return messageConverted;
    }
}
