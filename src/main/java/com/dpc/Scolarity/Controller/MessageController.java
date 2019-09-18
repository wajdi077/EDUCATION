package com.dpc.Scolarity.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Message;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.MessageConvert;
import com.dpc.Scolarity.Dto.MessageDTO;
import com.dpc.Scolarity.Repository.IMessage;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.param.MessageParam;
import com.dpc.Scolarity.service.implementation.MessageService;



@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/data/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
	@Autowired
	IMessage iMessage;

	@Autowired
	IUtilisateur iUtilisateur;
	@Autowired
	MessageService messageservice ;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	// @PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Map<String, Boolean> sendMessage(Model model, @RequestBody MessageDTO message) {
		Utilisateur sender = iUtilisateur.findByUsername(message.getUsername());
	Message messageEnvoyee = new Message();
	System.out.println(message.getUsername());
	System.out.println(message.getSujet());
	System.out.println(message.getReciever());
	Utilisateur reciever = iUtilisateur.findByEmail(message.getReciever());
	messageEnvoyee.setDate_message(new Date(System.currentTimeMillis()));
	messageEnvoyee.setMessage(message.getMessage());
	messageEnvoyee.setSender(sender);
	messageEnvoyee.setSujet(message.getSujet());
	messageEnvoyee.setReciever(reciever);
	iMessage.save(messageEnvoyee);	
	Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
	
	}

	
	@RequestMapping(value = "/recue", method = RequestMethod.GET)
	public @ResponseBody List<MessageConvert> getMessageRecue(Model model, String username) {
		
		
		Utilisateur utilisateur = iUtilisateur.findByUsername(username);
		List<?> mc = this.messageservice.messageenvoyer(username);
		//return (List<Message>) utilisateur.getMessj_recus();
		return (List<MessageConvert>) mc;
	}
	
	@RequestMapping(value = "/envoyer", method = RequestMethod.GET)
	public @ResponseBody List<Message> getMessageEnvoyer(Model model,String username) {
		Utilisateur utilisateur = iUtilisateur.findByUsername(username);
		return (List<Message>) utilisateur.getMessj_envoyees();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public   @ResponseBody Map<String, Boolean> removeuser(Model model, @RequestBody List<Long> id) {
		Message matieretodelete ;
		System.out.println("listedebut");
		System.out.println(id);
		System.out.println("listefin");
		try {
		
			for(int i=0; i<id.size();i++) {
				matieretodelete = null;
				System.out.println(id.get(i)+"slimyabhim1");
				System.out.println(id.get(i));
				
				matieretodelete = iMessage.findOne(id.get(i));
				System.out.println(id.get(i)+"slimyabhim111");
				if(matieretodelete!=null) 
				
					iMessage.delete(matieretodelete);
		
			}
			
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
	@RequestMapping(value = "/removeenvoyer", method = RequestMethod.POST)
	public   @ResponseBody Map<String, Boolean> removemessagenvoyer(Model model, @RequestBody List<Long> id) {
		Message matieretodelete ;
		System.out.println("listedebut");
		System.out.println(id);
		System.out.println("listefin");
		try {
		
			for(int i=0; i<id.size();i++) {
				matieretodelete = null;
				System.out.println(id.get(i)+"slimyabhim1");
				System.out.println(id.get(i));
				
				matieretodelete = iMessage.findOne(id.get(i));
				System.out.println(id.get(i)+"slimyabhim111");
				if(matieretodelete!=null) 
				
					iMessage.delete(matieretodelete);
		
			}
			
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
}
