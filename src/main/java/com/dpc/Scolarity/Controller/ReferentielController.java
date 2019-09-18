package com.dpc.Scolarity.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.SalleRepository;
import com.dpc.Scolarity.service.implementation.MailServiceImp;
import com.dpc.Scolarity.service.implementation.ReferenceService;
import com.dpc.Scolarity.service.implementation.ReferentielService;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author slim
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/referentiel")
public class ReferentielController {
	static final int ZERO = 0;
	static final int UN = 1;
	static final int DEUX = 2;
	static final int TROIS = 3;
	static final int QUATRE = 4;
	static final int CINQ = 5;
	static final int SIX = 6;
	static final int SEPT = 7;
	static final int HUIT = 8;
	static final int NOEUF = 9;

	@Autowired
	ReferentielService referentielService;
	@Autowired
	SalleRepository sallerepo;
	@Autowired
	MailServiceImp mailServiceImp;
	
	@Autowired
	ReferenceService referenceService;
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initReferentiel(@RequestParam("file") MultipartFile file) throws IOException {
		Map<String, Boolean> success = new TreeMap<String, Boolean>();
		success.put("response", true);
		String semainename;
		String joursName;
		String heuresdebut;
		String heuresFin;
		File referentielFile;
		try {
			referentielFile = convertFromMultiPart(file);

			FileInputStream excelFile;

			excelFile = new FileInputStream(referentielFile);

			Workbook workbook;

			workbook = new XSSFWorkbook(excelFile);

			Sheet semaine = workbook.getSheetAt(ZERO);
			Sheet jours = workbook.getSheetAt(UN);
			Sheet heures = workbook.getSheetAt(DEUX);
			Iterator<Row> itLigneSemaine = semaine.iterator();
			Iterator<Row> itLigneJours = jours.iterator();
			Iterator<Row> itLigneHeures = heures.iterator();
			Row currentLigneSemaine = itLigneSemaine.next();
			Row currentLigneJours = itLigneJours.next();
			Row currentLigneHeures = itLigneHeures.next();
			/* insérer les semaine du referentiel */
			while (itLigneSemaine.hasNext()) {
				currentLigneSemaine = itLigneSemaine.next();
				if ((currentLigneSemaine.getCell(0) != null)
						&& !(currentLigneSemaine.getCell(0).toString().equalsIgnoreCase(""))) {
					semainename = currentLigneSemaine.getCell(ZERO).getStringCellValue();

					this.referentielService.saveSemaine(semainename);
				}
			}
			// insérer les Heures du referentiel
			while (itLigneHeures.hasNext()) {
				currentLigneHeures = itLigneHeures.next();
				if ((currentLigneHeures.getCell(0) != null)
						&& !(currentLigneHeures.getCell(0).toString().equalsIgnoreCase(""))) {
					heuresdebut = currentLigneHeures.getCell(ZERO).getStringCellValue();
					heuresFin = currentLigneHeures.getCell(UN).getStringCellValue();
					this.referentielService.saveHeures(heuresdebut, heuresFin);
				}
			}
			// insérer les jours du referentiel
			while (itLigneJours.hasNext()) {
				currentLigneJours = itLigneJours.next();
				if ((currentLigneJours.getCell(0) != null)
						&& !(currentLigneJours.getCell(0).toString().equalsIgnoreCase(""))) {
					joursName = currentLigneJours.getCell(ZERO).getStringCellValue();

					this.referentielService.saveJours(joursName);

					return "suecces";
				}
			}
			return "The referetiel service was passed successfully  have been " + "initialized.";
		} catch (IOException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}

	public File convertFromMultiPart(MultipartFile multipartFile) throws IllegalStateException, IOException {
		File convFile = new File(FilenameUtils.getName(multipartFile.getOriginalFilename()));
		multipartFile.transferTo(convFile);
		return convFile;
	}

	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init Salle from an excel " + "file", response = String.class)

	@RequestMapping(value = "/initReferentielsalle", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initReferentiels(@RequestParam("file") MultipartFile file) throws IOException {

		File referentielFile = convertFromMultiPart(file);

		String sallename;
		String salletype;
		String salleremarque;
		String nomclasse;
		String typeclasse;
		String remarqueclasse;
		String activiteName;
		String categorieName;

		try {
			FileInputStream excelFile = new FileInputStream(referentielFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet salle = workbook.getSheetAt(ZERO);

			Iterator<Row> itLigneSalle = salle.iterator();

			Row currentLigneSalle = itLigneSalle.next();

			// insérer les salle du referentiel
			while (itLigneSalle.hasNext()) {
				currentLigneSalle = itLigneSalle.next();
				if ((currentLigneSalle.getCell(0) != null)
						&& !(currentLigneSalle.getCell(0).toString().equalsIgnoreCase(""))) {
					sallename = currentLigneSalle.getCell(ZERO).getStringCellValue();
					salleremarque = currentLigneSalle.getCell(UN).getStringCellValue();
					salletype = currentLigneSalle.getCell(DEUX).getStringCellValue();
					this.referentielService.addSalle(sallename, salletype, salleremarque);
					// this.referentielService.createRegion(regionName);
				}
			}

			// insérer les salle du referentiel

			return "The referetiel service was passed successfully : Salle have been " + "initialized.";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}

	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init classe from an excel " + "file", response = String.class)

	@RequestMapping(value = "/initReferentielclasse", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initReferentielcLASSE(@RequestParam("file") MultipartFile file) throws IOException {

		File referentielFile = convertFromMultiPart(file);

		String nomclasse;
		String typeclasse;
		String remarqueclasse;

		try {
			FileInputStream excelFile = new FileInputStream(referentielFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet classe = workbook.getSheetAt(ZERO);

			Iterator<Row> itLigneClasse = classe.iterator();

			Row currentLigneClasse = itLigneClasse.next();

			// insérer les classe du referentiel

			while (itLigneClasse.hasNext()) {
				currentLigneClasse = itLigneClasse.next();
				if ((currentLigneClasse.getCell(0) != null)
						&& !(currentLigneClasse.getCell(0).toString().equalsIgnoreCase(""))) {
					nomclasse = currentLigneClasse.getCell(ZERO).getStringCellValue();
					remarqueclasse = currentLigneClasse.getCell(UN).getStringCellValue();
					typeclasse = currentLigneClasse.getCell(DEUX).getStringCellValue();
					this.referentielService.addclasse(nomclasse, typeclasse, remarqueclasse);

				}
			}

			return "The referetiel service was passed successfully : Classe have been " + "initialized.";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}

	// insérer les eleves du referentiel

	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init eleve from an excel " + "file", response = String.class)

	@RequestMapping(value = "/initReferentieleleve", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initReferentieleleve(@RequestParam("file") MultipartFile file) throws IOException {

		File referentielFile = convertFromMultiPart(file);

		String nom;
		String prenom;
		String sexe;
		String situation;
		Date datenaissonce;
		String niveau;
		String classeactulle;
		String remarque;
		String etat;
		String numinsc;

		try {
			FileInputStream excelFile = new FileInputStream(referentielFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet classe = workbook.getSheetAt(ZERO);
			Iterator<Row> itLigneeleve = classe.iterator();
			Row currentLigneEleve = itLigneeleve.next();

			while (itLigneeleve.hasNext()) {
				currentLigneEleve = itLigneeleve.next();
				if ((currentLigneEleve.getCell(0) != null)
						&& !(currentLigneEleve.getCell(0).toString().equalsIgnoreCase(""))) {
					nom = currentLigneEleve.getCell(ZERO).getStringCellValue();
					prenom = currentLigneEleve.getCell(UN).getStringCellValue();
					etat = currentLigneEleve.getCell(DEUX).getStringCellValue();
					sexe = currentLigneEleve.getCell(TROIS).getStringCellValue();
					datenaissonce = currentLigneEleve.getCell(QUATRE).getDateCellValue();
					classeactulle = currentLigneEleve.getCell(CINQ).getStringCellValue();
					niveau = currentLigneEleve.getCell(SIX).getStringCellValue();
					remarque = currentLigneEleve.getCell(SEPT).getStringCellValue();
					situation = currentLigneEleve.getCell(HUIT).getStringCellValue();
					numinsc = currentLigneEleve.getCell(NOEUF).getStringCellValue();
					this.referentielService.addeleve(nom, prenom, etat, sexe, datenaissonce, classeactulle, niveau,
							remarque, situation, numinsc);

				}
			}

			return "Le service de referetiel a été passé avec succès : les eleves ont été initialisé";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}
	
	
	/* fonction d'import  des suveillants d'un ficheir excel
	 * developed by : sara naifar
	 * date: 18/07/2019
	 */
	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init surveillant from an excel " + "file", response = String.class)
	@RequestMapping(value = "/initReferentielSurveillant", method = RequestMethod.POST, consumes = { "multipart/*" })
	public String initReferentielSurveillant(String login,@RequestParam("file") MultipartFile file) throws IOException {

		File referentielFile = convertFromMultiPart(file);
		String nom;
		String prenom;
		String email;
		String username;
		String password;
		String profil;
		try {
			FileInputStream excelFile = new FileInputStream(referentielFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet classe = workbook.getSheetAt(ZERO);
			Iterator<Row> itLignesurv = classe.iterator();
			Row currentLigneSurv = itLignesurv.next();
			// insérer les classe du referentiel
			while (itLignesurv.hasNext()) {
				currentLigneSurv = itLignesurv.next();
				if ((currentLigneSurv.getCell(0) != null)
						&& !(currentLigneSurv.getCell(0).toString().equalsIgnoreCase(""))) {
					profil =currentLigneSurv.getCell(ZERO).getStringCellValue();
					nom =currentLigneSurv.getCell(UN).getStringCellValue();
					prenom=currentLigneSurv.getCell(DEUX).getStringCellValue();
					email=currentLigneSurv.getCell(TROIS).getStringCellValue();
					username=currentLigneSurv.getCell(QUATRE).getStringCellValue();
					password=currentLigneSurv .getCell(CINQ).getStringCellValue();
					
			Utilisateur s = this.referentielService.addSurveillant(nom, prenom, email, username, password, profil, login);
			System.out.println("hello sarra!!!!!!!!!!");
			System.out.println(s);
			this.mailServiceImp.EnvoyerEmailSurveillant(s);
				//	this.referentielService.createRegion(regionName);
				}
			}
			return "The referetiel service was passed successfully : eleves have been "
					+ "initialized.";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}
	}
	
	
		
	

	/* fonction d'import  des éleves et reaffectation des nouveaux classes et niveau à partir d'un ficheir excel
	 * developed by : sara naifar
	 * date: 06/08/2019
	 */

	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init classes eleve from an excel " + "file", response = String.class)

	@RequestMapping(value = "/initReferentielAffectationEleve", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initReferentielAffectationEleve(@RequestParam("file") MultipartFile file) throws IOException {

		File referentielFile = convertFromMultiPart(file);
		
		String numinsc;
		String classeactulle;
		String ancien_classe;
		String niveau;
		

		try {
			FileInputStream excelFile = new FileInputStream(referentielFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet classe = workbook.getSheetAt(ZERO);
			Iterator<Row> itLigneeleve = classe.iterator();
			Row currentLigneEleve = itLigneeleve.next();

			while (itLigneeleve.hasNext()) {
				currentLigneEleve = itLigneeleve.next();
				if ((currentLigneEleve.getCell(0) != null)
						&& !(currentLigneEleve.getCell(0).toString().equalsIgnoreCase(""))) {
					
					niveau =  currentLigneEleve.getCell(ZERO).getStringCellValue();
					classeactulle = currentLigneEleve.getCell(UN).getStringCellValue();
					ancien_classe = currentLigneEleve.getCell(DEUX).getStringCellValue();
					numinsc = currentLigneEleve.getCell(SIX).getStringCellValue();
					this.referentielService.affectationEleve(niveau, classeactulle,ancien_classe,numinsc );

				}
			}

			return "Le service de referetiel a été passé avec succès : les eleves ont été initialisé";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}
	/*
	 * developer: sarra naifar
	 * date:26/08/2019
	 * fonction: insérer les nombre d'heur par niveau par matiere du referentiel
	*/
		@SuppressWarnings("resource")
		@ApiOperation(value = "initReferentiel", notes = "Init nombre heur from an excel " + "file", response = String.class)

		@RequestMapping(value = "/initReferentielnombreheur", method = RequestMethod.POST, consumes = { "multipart/*" })

		public String initReferentielnombreheur(@RequestParam("file") MultipartFile file) throws IOException {

			File referentielFile = convertFromMultiPart(file);

			Long nbheures;
			String matiere;
			String niveau;
			ArrayList niveauxEtude = new ArrayList();
			ArrayList matieres = new ArrayList();
			ArrayList nombreHeurParMatier = new ArrayList();
			ArrayList nombreHeur = new ArrayList();
			try {
				FileInputStream excelFile = new FileInputStream(referentielFile);
				Workbook workbook = new XSSFWorkbook(excelFile);
				Sheet classe = workbook.getSheetAt(ZERO);
				Iterator<Row> itLigneeleve = classe.iterator();
				Row currentLigneEleve = itLigneeleve.next();
				Iterator<Cell> cellIterator = currentLigneEleve.cellIterator();
				 /* for (Row row : classe) {
					  if(currentLigneEleve.getRowNum()==2) {
						  for (Cell cell : row) { 
							  System.out.print(cell);
							  niveauxEtude.add(cell);
						    }
					  }				   
					  }*/

				while (itLigneeleve.hasNext()) {
					currentLigneEleve = itLigneeleve.next();
				 if ((currentLigneEleve.getCell(0) != null) && !(currentLigneEleve.getCell(0).toString().equalsIgnoreCase(""))) {	
					int rowNum = currentLigneEleve.getRowNum();
					  if(currentLigneEleve.getRowNum()==2) {
						  for (Cell cell : currentLigneEleve) { 				 
							  String cellule =(cell).toString();
							  if(cellule!= "") {
								  niveauxEtude.add(cellule);
							  }					
						    }
					  }		
				 if(rowNum>3) {
						matieres.add(currentLigneEleve.getCell((currentLigneEleve.getLastCellNum())-1).getStringCellValue());
						int numCell=0;
						while(numCell < ((currentLigneEleve.getLastCellNum())-1)) {						
							nombreHeurParMatier.add(currentLigneEleve.getCell(numCell).getNumericCellValue());
						
							numCell+=2;	
						}
						nombreHeur.add(nombreHeurParMatier);
						nombreHeurParMatier= new ArrayList();

					}
				 }				
				}  
			
				
				this.referentielService.ajoutNombreHeurParMatiereParNiveau(matieres, nombreHeur, niveauxEtude);
				return "Le service de referetiel a été passé avec succès : les eleves ont été initialisé";
			} catch (FileNotFoundException e) { 
				e.printStackTrace();
				return "Can not load reference.xls";
			}

		}
		
		
	
	
	

}
