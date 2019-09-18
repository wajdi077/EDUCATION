package com.dpc.Scolarity.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpc.Scolarity.Domain.Penalites;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class GeneratePdfReport {

	 private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

	    public static ByteArrayInputStream penalitesReport( Penalites p) {

	   // Utilisateur user1=new Utilisateur();
	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	      
			try {
				PdfWriter.getInstance(document, out);
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			document.open();
			try {
				try {
					String s1 = new String(p.getEleves().getNom().getBytes("UTF-8"), "UTF-8");
			
				 document.add(new Paragraph("Commissariat régional à l'éducation "+s1+
						 "\n l'école préparatoire / institut                                                 N° poste2" +
						 "\n\n                                                   Avertissement "+
						 "\n\n\n Nom et Prénom d'éleve:  "+p.getEleves().getNom()+""+p.getEleves().getPrenom()+
						 "\n Classe:  " +p.getProgramme().getClasse()+
						 "\n Cause:  "+	p.getMotif()+
						 "\n Décision du responsable de l'école préparatoire /Lycée:  "+p.getMotif()+"           "+  
						 "\n\n\n                            le:  "+p.getDate_Penalites()+
						 "\n                            Nom Prénom: "+p.getProgramme().getProf()+
						 "\n                                   Poste: Enseignant"+
						 "\n                                      Signature:"+
						"\n______________________________________________________________________________"+
						"Commissariat régional à l'éducation "+
						 "\n l'école préparatoire / institut                                                 N° poste2" +
						 "\n\n                                                   Avertissement "+
						 "\n\n\n Nom et Prénom d'éleve:  "+p.getEleves().getNom()+""+p.getEleves().getPrenom()+
						 "\n Classe:  " +p.getProgramme().getClasse()+
						 "\n Cause:  "+	p.getMotif()+
						 "\n Décision du responsable de l'école préparatoire /Lycée:  "+p.getRemarque()+"           "+  
						 "\n\n\n                            le:  "+p.getDate_Penalites()+
						 "\n                            Nom Prénom: "+p.getProgramme().getProf()+
						 "\n                                   Poste: Enseignant"+
						 "\n                                      Signature:"
					));
				//document.add(table);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	    //*****************retenu report*********************
	    public static ByteArrayInputStream retenuReport(Penalites p) {

		    // Utilisateur user1=new Utilisateur();
		        Document document = new Document();
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		      
				try {
					try {
						String s1 = new String(p.getEleves().getNom().getBytes("UTF-8"), "UTF-8");
					
					PdfWriter.getInstance(document, out);
					document.open();
					 document.add(new Paragraph("Commissariat régional à l'éducation "+s1+
							 "\n l'école préparatoire / institut                                                 N° poste1" +
							 "\n\n                                                   Retenue "+
							 "\n\n\n Nom et Prénom d'éleve:  "+p.getEleves().getNom()+" "+p.getEleves().getPrenom()+
							 "\n Classe:  " +p.getProgramme().getClasse().getNomclasse()+
							 "\n Cause:  "+	p.getMotif()+
							 "\n Nombre D'heures suggéré:  deux heures           "+  
							 "\n Travail Demandé:  "+p.getRemarque()+
							 "\n Décision du responsable de l'école préparatoire /Lycée:  "+p.getRemarque()+
							 "\n\n\n                            le:  "+p.getDate_Penalites()+
							 "\n                            Nom d'enseignant:: "+p.getProgramme().getProf().getPrenom()+
							
							 "\n                                      Signature:"+
							"\n______________________________________________________________________________"+
							"Commissariat régional à l'éducation "+
							 "\n l'école préparatoire / institut                                                 N° poste1" +
							 "\n\n                                                   Retenue "+
							 "\n\n\n Nom et Prénom d'éleve:  "+p.getEleves().getNom()+" "+p.getEleves().getPrenom()+
							 "\n Classe:  " +p.getProgramme().getClasse().getNomclasse()+
							 "\n Cause:  "+	p.getMotif()+
							 "\n Nombre D'heures suggéré:  deux heures           "+  
							 "\n Travail Demandé:  "+p.getRemarque()+
							 "\n Décision du responsable de l'école préparatoire /Lycée:  "+p.getRemarque()+
							 "\n\n\n                            le:  "+p.getDate_Penalites()+
							 "\n                            Nom d'enseignant:: "+p.getProgramme().getProf().getPrenom()+
							
							 "\n                                      Signature:"
						));
					 document.close();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (com.itextpdf.text.DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		        return new ByteArrayInputStream(out.toByteArray());
		    }
}