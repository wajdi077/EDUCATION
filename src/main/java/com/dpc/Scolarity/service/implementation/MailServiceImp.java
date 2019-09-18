package com.dpc.Scolarity.service.implementation;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.IUtilisateur;

@Service

public class MailServiceImp  {
	 @Autowired
	    JavaMailSender mailSender;

	 @Autowired
	    private IUtilisateur userrepo;

String logo ="";
		
		public void EnvoyerEmail(Utilisateur user) {
			   MimeMessage mimeMessage = mailSender.createMimeMessage();  
		        try {
		
		            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		            mimeMessageHelper.setSubject("Compte parent ");
		            mimeMessageHelper.setFrom("slim.chaanbi@gmail.com");
		            mimeMessageHelper.setTo(user.getEmail());
	          String url ="scolarity.dpc.com.tn";
	                String content = "Bonjour Mr ( Mmme), " + user.getNom()+" + " + user.getPrenom()
	               + ", votre nom d'utilisateur par la platforme scolarity est : \n " + user.getEmail() +" \n"+"et votre mot de passe est : \n"+ user.getPassword()+"\n"+"vous pouvez accéder au espace parain via l'adresse suivante : \n"+url+"\n"+" \n Cordialement.";
	                System.out.println(content);
	 
	                mimeMessageHelper.setText(content);
	                // Add a resource as an attachment
	                mimeMessageHelper.setText("<html><body><p>" + content + "</p> <img src=\"http://207.180.211.158:/logodpc.bmp\" width=\"50\" alt=\"Apen\"></body></html>", true);
	             //   mimeMessageHelper.addInline("company-logo", new ClassPathResource("scolaryty"));
	              
	 
		 
		          //  mimeMessageHelper.setSubject());
		          //  mimeMessageHelper.setFrom(mail.getMailFrom());
		           // mimeMessageHelper.setTo(art.getEmail());
		           // mimeMessageHelper.setText(mail.getMailContent());
		           // for (Object attachment: mail.getAttachments()) {
		                //File file = ((ClassPathResource) attachment).getFile();
		              //  mimeMessageHelper.addAttachment(file.getName(), file);
		            
		 
		            mailSender.send(mimeMessageHelper.getMimeMessage());
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
			
		}
		public void EnvoyerEmailAbsence(Utilisateur user,Programme p,Eleve e ) {
			   MimeMessage mimeMessage = mailSender.createMimeMessage();  
		        try {
		
		            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		            mimeMessageHelper.setSubject("Compte parent ");
		            mimeMessageHelper.setFrom("scolarity.dpc@gmail.com");
		            mimeMessageHelper.setTo(user.getEmail());
	          
	                String content = "Bonjour Mr ( Mmme), " + user.getNom()+" + " + user.getPrenom()
	               + ", VoTRE eleve: <br> " + e.getNom()+"<br>"+e.getPrenom()+" <br>"+"est absent dans la seance de  : <br>"+ p.getMatiere().getNommatiereFR()+"\n"+"<br> --> dans la date \n"+p.getDateprog().toString()+"\n"+" <br> Cordialement.";
	                System.out.println(content);
	 
	                mimeMessageHelper.setText(content);
	                // Add a resource as an attachment
	                mimeMessageHelper.setText("<html><body><p>" + content + "</p><img src='cid:http://207.180.211.156/logodpc.bmp'></body></html>", true);
	              // mimeMessageHelper.addInline("company-logo", new ClassPathResource("http://207.180.211.158/logodpc.bmp"));
	             
	              
	 
		 
		          //  mimeMessageHelper.setSubject());
		          //  mimeMessageHelper.setFrom(mail.getMailFrom());
		           // mimeMessageHelper.setTo(art.getEmail());
		           // mimeMessageHelper.setText(mail.getMailContent());
		           // for (Object attachment: mail.getAttachments()) {
		                //File file = ((ClassPathResource) attachment).getFile();
		              //  mimeMessageHelper.addAttachment(file.getName(), file);
		            
		 
		            mailSender.send(mimeMessageHelper.getMimeMessage());
		        } catch (MessagingException x) {
		            x.printStackTrace();
		        }
			
		}

		public void EnvoyerEmailExclus(Utilisateur user,Programme p,Eleve e ) {
			   MimeMessage mimeMessage = mailSender.createMimeMessage();  
		        try {
		Date dateexclus =new Date(System.currentTimeMillis());
		            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		            try {
		            mimeMessageHelper.setSubject("Alerte d'exclus ");
		            mimeMessage.setSubject("Alerte d'exclus", "UTF-8");
		           
		            mimeMessageHelper.setFrom("scolarity.dpc@gmail.com");
		            // mimeMessageHelper.setTo(user.getEmail());
	          // String style ="style='background-color:powderblue;'";
	      	// String str1 = new String("مرحبا سيدي".getBytes(), "UTF-8");
	      	// String str2 = new String(user.getNom().getBytes(), "UTF-8");
	      	// String str3 = new String(user.getPrenom().getBytes(), "UTF-8");
	      	// String str4 = new String("يأسفني إبلاغكم أن إبنكم".getBytes(), "UTF-8");
	      	// String str6 = new String(e.getPrenom().getBytes(), "UTF-8");
	      	// String str5 = new String(e.getNom().getBytes(), "UTF-8");
	      	// String str7 = new String("طرد من حصة".getBytes(), "UTF-8");
	      	// String str8 = new String(p.getMatiere().getNommatiereFR().getBytes(), "UTF-8");
	      	// String str9 = new String("اليوم على الساعة".getBytes(), "UTF-8");
	      	String str10 = new String(dateexclus.toGMTString().getBytes(), "UTF-8");
	      	// String str11 = new String("تحياتي".getBytes(), "UTF-8
			
			            mimeMessageHelper.setTo(user.getEmail());
                  String style ="style='background-color:powderblue;'";
                String str1 = new String("ﻡﺮﺤﺑﺍ ﺲﻳﺪﻳ".getBytes("UTF-8"), "UTF-8");
                String str2 = new String(user.getNom().getBytes("UTF-8"), "UTF-8");
                String str3 = new String(user.getPrenom().getBytes("UTF-8"), "UTF-8");
                String str4 = new String("ﻱﺄﺴﻔﻨﻳ ﺈﺑﻼﻐﻜﻣ ﺄﻧ ﺈﺒﻨﻜﻣ".getBytes("UTF-8"), "UTF-8");
                String str6 = new String(e.getPrenom().getBytes("UTF-8"), "UTF-8");
                String str5 = new String(e.getNom().getBytes("UTF-8"), "UTF-8");
                String str7 = new String("ﻁﺭﺩ ﻢﻧ ﺢﺻﺓ".getBytes("UTF-8"), "UTF-8");
                String str8 = new String(p.getMatiere().getNommatiereFR().getBytes("UTF-8"), "UTF-8");
                String str9 = new String("ﺎﻠﻳﻮﻣ ﻊﻟﻯ ﺎﻠﺳﺎﻋﺓ".getBytes("UTF-8"), "UTF-8");
                //String str10 = new String(dateexclus.toGMTString().getBytes("UTF-8"), "UTF-8");
                String str11 = new String("ﺖﺤﻳﺎﺘﻳ".getBytes("UTF-8"), "UTF-8");


	      	
				/*
				 * String content = "Bonjour Mr ( Mmme), " + user.getNom()+ str1 +
				 * user.getPrenom() + ", Votre eleve: <br>" +
				 * e.getNom()+"<br>"+e.getPrenom()+" <br>"
				 * +"est exclus dans la seance de  : <br>"+
				 * p.getMatiere().getNommatiereFR()+"\n"+"<br> --> dans la date \n"+dateexclus.
				 * toGMTString()+"\n pour plus d'information veuilez acceder a votre espace"
				 * +" <br> Cordialement."; System.out.println(content);
				 */
	             String content = str1 +" " +str2+" "+str3+" "+str4+ " "+ str6 +" "+ str5 +" "+ str7 +" "+str8+ " "+str9+" "+dateexclus+ " "+"\n"+ str11;
	                mimeMessage.setContent(content, "text/plain; charset=UTF_8");
				
	 
	                mimeMessageHelper.setText(content);
	                // Add a resource as an attachment
	                mimeMessageHelper.setText("<html><body"+style+" ><p>" + content + "</p><img src='cid:http://207.180.211.156/logodpc.bmp'></body></html>", true);
	              // mimeMessageHelper.addInline("company-logo", new ClassPathResource("http://207.180.211.158/logodpc.bmp"));
	             
	              
	 
		 
		          //  mimeMessageHelper.setSubject());
		          //  mimeMessageHelper.setFrom(mail.getMailFrom());
		           // mimeMessageHelper.setTo(art.getEmail());
		           // mimeMessageHelper.setText(mail.getMailContent());
		           // for (Object attachment: mail.getAttachments()) {
		                //File file = ((ClassPathResource) attachment).getFile();
		              //  mimeMessageHelper.addAttachment(file.getName(), file);
		            
		        	} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            
		            mailSender.send(mimeMessageHelper.getMimeMessage());
		        } catch (MessagingException x) {
		            x.printStackTrace();
		        }
			
		}
		
	
	
	
		  public void sendEmail(Object object) {
			  
			  Utilisateur user = (Utilisateur) object;
		 
		 
		        MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(user);
		 
		        try {
		            mailSender.send(preparator);
		            System.out.println("Message With Attachement has been sent.............................");
		            preparator = getContentAsInlineResourceMessagePreparator(user);
		            mailSender.send(preparator);
		            System.out.println("Message With Inline Resource has been sent.........................");
		        } catch (MailException ex) {
		            System.err.println(ex.getMessage());
		        }
		    }
		 
		  private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final Utilisateur user) {
			  
		        MimeMessagePreparator preparator = new MimeMessagePreparator() {
		 
		            public void prepare(MimeMessage mimeMessage) throws Exception {
		                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
		 
		                helper.setSubject("Votre demander été bien enregistré ");
		                helper.setFrom("scolarity.dpc@gmail.com");
		                helper.setTo(user.getEmail());
		                System.out.println(user.getEmail());
		                String content = "Dear " + user.getNom()+" + " + user.getPrenom()
		               + ", thank you for placing order. Your order id is " + user.getEmail() +" + "+ user.getPassword();
		                System.out.println(content);
		 
		                helper.setText(content);
		                
		 
		                // Add a resource as an attachment
		                helper.setText("<html><body><p>" + content + "</p><img src='cid:company-logo'></body></html>", true);
		                helper.addInline("company-logo", new ClassPathResource("immo devis.bmp"));
		              
		 
		            }
		        };
		        return preparator;
		    }
		 
		  private MimeMessagePreparator getContentAsInlineResourceMessagePreparator(final Utilisateur user) {
			  
		        MimeMessagePreparator preparator = new MimeMessagePreparator() {
		 
		            public void prepare(MimeMessage mimeMessage) throws Exception {
		                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		                helper.setSubject("Votre demander été bien enregistré ");
		                helper.setFrom("scolarity.dpc@gmail.com");
		                helper.setTo(user.getEmail());
		                System.out.println(user.getEmail());
		            
		                String content = "Dear " + user.getNom()+" + " + user.getPrenom()
		               + ", thank you for placing order. Your order id is " + user.getEmail() +" + "+ user.getPassword();
		 
		                System.out.println(content);
		 
		                // Add an inline resource.
		                // use the true flag to indicate you need a multipart message
		                helper.setText("<html><body><p>" + content + "</p><img src='cid:company-logo'></body></html>", true);
		                helper.addInline("company-logo", new ClassPathResource("immo devis.bmp"));
		            }
		        };
		        return preparator;
		        
		    }

	
		  @Autowired
		  public void EmailService(JavaMailSender mailSender) {
		    this.mailSender = mailSender;
		  }
		  
		  @Async
		  public void sendEmail(SimpleMailMessage email) {
		    mailSender.send(email);
		  }

		  
		  
		  public boolean emailValidator(String email) {
			  
			  boolean isValid = false;
			  try {
				  
				  
				  InternetAddress internetadress = new InternetAddress(email);
				  
				  internetadress.validate();
				  isValid =true;
			  }
			  catch(AddressException e ) {
				  System.out.println("invalid email"+ email);
			  }
				  
				  
				  
				  
				  return isValid ;
				  
			  }
		  
			  ////*********************** hamza 
			public void EnvoyerEmailEtablissement(Utilisateur userroot,Etablissement e ) {
				  
				 MimeMessage mimeMessage = mailSender.createMimeMessage();  
			        try {
			
			            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			            mimeMessageHelper.setSubject("Etablissement Affecté ");
			            mimeMessageHelper.setFrom("scolarity.dpc@gmail.com");
			            mimeMessageHelper.setTo(userroot.getEmail());
		          
			            String url ="scolarity.dpc.com.tn";
		                String content = "Bonjour ADMIN, <br>" 
		               + " Vous trouvez çi joint vos coordonnées pour vous pouvez accéder a votre Etablissement <br> "+" Email: " + e.getEmail()+"<br>"+" Password:"+userroot.getPassword()+" <br>"+"Lien de platforme: "+url+"<br>Cordialement. <br>";
		                System.out.println(content);
		 
		                mimeMessageHelper.setText(content);
		                // Add a resource as an attachment
		                mimeMessageHelper.setText("<html><body><p>" + content + "</p><img src='cid:http://207.180.211.156/logodpc.bmp'></body></html>", true);
		              // mimeMessageHelper.addInline("company-logo", new ClassPathResource("http://207.180.211.158/logodpc.bmp"));
		             
		              
		 
			 
			          //  mimeMessageHelper.setSubject());
			          //  mimeMessageHelper.setFrom(mail.getMailFrom());
			           // mimeMessageHelper.setTo(art.getEmail());
			           // mimeMessageHelper.setText(mail.getMailContent());
			           // for (Object attachment: mail.getAttachments()) {
			                //File file = ((ClassPathResource) attachment).getFile();
			              //  mimeMessageHelper.addAttachment(file.getName(), file);
			            
			 
			            mailSender.send(mimeMessageHelper.getMimeMessage());
			        } catch (MessagingException x) {
			            x.printStackTrace();
			        }
			}
		  
		  
			public void EnvoyerEmailAddUser(Utilisateur user ) {
				   MimeMessage mimeMessage = mailSender.createMimeMessage();  
			        try {
			
			            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			            mimeMessageHelper.setSubject("Nouveau Compte Utilisateur SColarity ");
			            mimeMessageHelper.setFrom("scolarity.dpc@gmail.com");
			            mimeMessageHelper.setTo(user.getEmail());
		         
		                String content = "Bonjour Mr(Mme):" + user.getNom() +" "+ user.getPrenom()
		               +" "+ "Bienvenue Dans la platforme SColarity: \n "
					   +"<br><br>Votre Login est : " +user.getUsername() + "\n" 
					   +"<br><br>Votre Mot de passe est : " +user.getPassword()+ "\n"+" <br><br> Cordialement.";
					   System.out.println(content);
		 
		                mimeMessageHelper.setText(content);
		                // Add a resource as an attachment
		                mimeMessageHelper.setText("<html><body><p>" + content + "</p><img src='cid:http://207.180.211.156/logodpc.bmp'></body></html>", true);
		              
			            
			 
			            mailSender.send(mimeMessageHelper.getMimeMessage());
			        } catch (MessagingException x) {
			            x.printStackTrace();
			        }
				
			}
			
			public void EnvoyerEmailAddEnseignant(Utilisateur user ) {
				   MimeMessage mimeMessage = mailSender.createMimeMessage();  
			        try {
			
			            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			            mimeMessageHelper.setSubject("Nouveau Compte Enseignant SColarity ");
			            mimeMessageHelper.setFrom("scolarity.dpc@gmail.com");
			            mimeMessageHelper.setTo(user.getEmail());
		         
		                String content = "Bonjour Mr(Mme):" + user.getNom() +" "+ user.getPrenom()
		               +" "+ "Bienvenue Dans la platforme SColarity: \n "
					   +"<br><br>Votre Login est : " +user.getUsername() + "\n" 
					   +"<br><br>Votre Mot de passe est : " +user.getPassword()+ "\n"+" <br><br> Cordialement.";
					   System.out.println(content);
		 
		                mimeMessageHelper.setText(content);
		                // Add a resource as an attachment
		                mimeMessageHelper.setText("<html><body><p>" + content + "</p><img src='cid:http://207.180.211.156/logodpc.bmp'></body></html>", true);
		              
			            
			 
			            mailSender.send(mimeMessageHelper.getMimeMessage());
			        } catch (MessagingException x) {
			            x.printStackTrace();
			        }
				
			}
			

			public void EnvoyerEmailAddParrain(Utilisateur user ) {
				   MimeMessage mimeMessage = mailSender.createMimeMessage();  
			        try {
			
			            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			            mimeMessageHelper.setSubject("Nouveau Compte Parrain SColarity ");
			            mimeMessageHelper.setFrom("scolarity.dpc@gmail.com");
			            mimeMessageHelper.setTo(user.getEmail());
		         
		                String content = "Bonjour Mr(Mme):" + user.getNom() +" "+ user.getPrenom()
		               +" "+ "Bienvenue Dans la platforme SColarity: \n "
					   +"<br><br>Votre Login est : " +user.getUsername() + "\n" 
					   +"<br><br>Votre Mot de passe est : " +user.getPassword()+ "\n"+" <br><br> Cordialement.";
					   System.out.println(content);
		 
		                mimeMessageHelper.setText(content);
		                // Add a resource as an attachment
		                mimeMessageHelper.setText("<html><body><p>" + content + "</p><img src='cid:http://207.180.211.156/logodpc.bmp'></body></html>", true);
		              
			            
			 
			            mailSender.send(mimeMessageHelper.getMimeMessage());
			        } catch (MessagingException x) {
			            x.printStackTrace();
			        }
				
			}
			
			/* fonction d'envoie des mails d'inscription des suveillants
			 * developed by : sara naifar
			 * date: 18/07/2019
			 */
			public void EnvoyerEmailSurveillant(Utilisateur user) {
				   MimeMessage mimeMessage = mailSender.createMimeMessage();  
			        try {
			            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			            mimeMessageHelper.setSubject("Compte Surveillant ");
			            mimeMessageHelper.setFrom("naifarsara@gmail.com");

			            mimeMessageHelper.setTo(user.getEmail());
			            String url ="scolarity.dpc.com.tn";
		                String content = "Bonjour Mr ( Mmme), " + user.getNom()+" + " + user.getPrenom()
		               + ", votre nom d'utilisateur par la platforme scolarity est : \n " + user.getUsername() +" \n"+"et votre mot de passe est : \n"+ user.getPassword()+"\n"+"vous pouvez accéder au espace surveillant via l'adresse suivante : \n"+url+"\n"+" \n Cordialement.";
		                System.out.println(content);

			            mimeMessageHelper.setTo(user.getEmail());
		                mimeMessageHelper.setText(content);
		                // Add a resource as an attachment
		                mimeMessageHelper.setText("<html><body><p>" + content + "</p> <img src=\"http://207.180.211.158:/logodpc.bmp\" width=\"50\" alt=\"Apen\"></body></html>", true);

			            
			 
			            mailSender.send(mimeMessageHelper.getMimeMessage());
			        } catch (MessagingException e) {
			            e.printStackTrace();
			        }
				
			}
}
