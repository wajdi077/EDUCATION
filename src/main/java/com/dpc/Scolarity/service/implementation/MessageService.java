/**
 * 
 */
package com.dpc.Scolarity.service.implementation;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Message;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.MessageConvert;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.MessageRepository;



/**
 * @author slim
 *
 */
@Service
public class MessageService {

	@Autowired
	MessageRepository messagerepo ; 
	@Autowired
	IUtilisateur iUtilisateur;
	
	
	
	public List<MessageConvert>getmessagerecu(String username ) {
		
		Utilisateur utilisateur = iUtilisateur.findByUsername(username);
		

	List<Message> messages =	(List<Message>) utilisateur.getMessj_recus();

	if (messages.size()>0) {
		for ( int j = 0;j<messages.size();j++) {
	for(int i=0; i<messages.size();i++) {
		List<MessageConvert> list = new ArrayList<>();
		
		Message m = messages.get(i);
		System.out.println("la date du message"+m.getDate_message());
		
		String  date =m.getDate_message().toString();
	
		MessageConvert mc = new MessageConvert() ;
		mc.setDate_message(date);
		mc.setMessage(m.getMessage());
		mc.setSender(m.getSender());
		mc.setReciever(m.getReciever());
		String duree ="";
// calcul de duree de message 
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC+01:00"));
		Date d ;
		   d = (Date) c.getTime();
		   c.setTime(d);
	
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   
Long diff = d.getTime() -m.getDate_message().getTime();
		   System.out.println("duree est "+diff);
		
		float nbheure = diff/3600000;
		Long nbh =diff/3600000;
		
		float nbminute = diff/360000;
		Long nbm = diff/360000;
		String dures ="";
	Long 	rest =(long) (( nbheure-nbh )*60/3600);
	if (nbheure <1) {
		
		dures = nbm+"minute";
		mc.setDuree(dures);
		System.out.println("dureee est "+dures);
		
	}
		if ( nbheure>1)
		{
dures = nbh +"heure"+"et" +rest+"minute";
			mc.setDuree(dures);
			
}
		

		System.out.println("la date du message"+date);
		int k = 0 ;
		list.set(k, mc);
		k++;
		return list ;
		
	}}
	return null ;
	}
		
	return null ;	
		
		
		
		
		
	}
	
	
	
	public List<?> messageenvoyer(String username ) {
		//MessageConvert mc = new MessageConvert() ;
		
		Utilisateur utilisateur = iUtilisateur.findByUsername(username);
		List<Message> messages =	(List<Message>) utilisateur.getMessj_recus();
		List<Message> messages1 =	(List<Message>) utilisateur.getMessj_recus();
		List<MessageConvert> list = new ArrayList<MessageConvert>();
	
		for ( Message me :messages ) {
			
			MessageConvert mc = new MessageConvert() ;
			mc.setMessage(me.getMessage());
			mc.setReciever(me.getReciever());
			mc.setSender(me.getSender());
			mc.setSujet(me.getSujet());
			mc.setDate_message(me.getDate_message().toString());
			list.add(mc);
			System.out.println("message:"+mc);
			// calcul de duree 
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC+01:00"));
			Date d ;
			   d = (Date) c.getTime();
			   c.setTime(d);
			Float diff = (float) (d.getTime() -me.getDate_message().getTime());
			float nbheure = diff/3600000;
			Long nbh =(long) (diff/3600000);
			
			Long nbminute = (long) (diff/360000);
			float nbm = diff*60/360000;
			String dures ="";
		Long 	rest =(long) (( -nbheure+nbh )*60/3600);
		
		
		
		if (nbheure <1) {
			
			dures = nbminute+"minute";
			mc.setDuree(dures);
			System.out.println("dureee est "+dures);
			
		}
			if ( nbheure>1)
			{
				Long heuress = (long) nbh;
				dures = heuress +"heure"+"et" +rest+"minute";
	
	System.out.println("dureee est "+dures);
	if(heuress>23) {
		Float jfloat = (float) (nbh/24) ;
		Long jourslong =heuress/24;
		System.out.println("heuresfloat "+jfloat);
		System.out.println("heurelong "+jourslong);
		
		System.out.println("dureee est "+dures);
		float hj = (long) ((jfloat-jourslong)*24) ;
		dures = jourslong+"jour(s)" ;
		System.out.println("dureee est "+dures);
		mc.setDuree(dures);
	}
	else {
		
		dures = heuress +"heure(s)";
		System.out.println("dureee est "+dures);
		mc.setDuree(dures);
		
	}
				
				
	}
			list.add(mc);
			//messages1.set(i, me);
		}
		System.out.println("la liste est :"+list);
		return list;
		
		
		
		
		
	}

}
