package com.dpc.Scolarity.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.MatiereReferenciel;
import com.dpc.Scolarity.service.implementation.MatiereReferentielService;
import com.dpc.Scolarity.Domain.Utilisateur;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/referentiel")

public class MatiereReferentielController {
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
	MatiereReferentielService matiereref ; 
/*	
	public File convertFromMultiPart(MultipartFile multipartFile) throws IllegalStateException, IOException {
		File convFile = new File(FilenameUtils.getName(multipartFile.getOriginalFilename()));
		multipartFile.transferTo(convFile);
		return convFile;
	}


	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init eleve from an excel " + "file", response = String.class)
	@RequestMapping(value = "/nombred'heure", method = RequestMethod.POST, consumes = { "multipart/*" })
	public String nombreheure(@RequestParam("file") MultipartFile file) throws IOException {
		File referentielFile = convertFromMultiPart(file);
Date datecreation ;
Number nbheures ;  
String remarque ;
Long matiere_idmatiere ;  
Long niveau_idniveau ;  
 try {
	 FileInputStream excelFile = new FileInputStream(referentielFile);
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet classe = workbook.getSheetAt(ZERO);
		Iterator<Row> itLignenbheure = classe.iterator();
		Row currentLignenbheure = itLignenbheure.next();
		while (itLignenbheure.hasNext()) {
			currentLignenbheure = itLignenbheure.next();
			if ((currentLignenbheure.getCell(0) != null)
					&& !(currentLignenbheure.getCell(0).toString().equalsIgnoreCase(""))) {
				datecreation=currentLignenbheure.getCell(ZERO).getDateCellValue();
				
				nbheures=currentLignenbheure.getCell(UN).getNumberCellValue();
				remarque=currentLignenbheure.getCell(DEUX).getStringCellValue();
				matiere_idmatiere=currentLignenbheure.getCell(TROIS).getLongCellValue();
				niveau_idniveau=currentLignenbheure.getCell(QUATRE).getLongCellValue();
			this.matiereref.addnbheure(datecreation,nbheures,remarque,matiere_idmatiere,niveau_idniveau);
			}
		}

		return "Le service de referetiel a été passé avec succès : les eleves ont été initialisé";
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		return "Can not load reference.xls";
	}

}
*/
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public List<MatiereReferenciel> getAllMatiereReferenciel() {
		return this.matiereref.findAll();

}
	//fct affecter
	/*  
	  @PostMapping(value = "/ajouter profparid/{id}")
	  
	  public Utilisateur saveProf(@RequestBody Utilisateur utilisateur, long idmatiere) {
		  return this.matiereref.AddProfTomatiere(utilisateur,idmatiere); 
	  
	  
	  }
	*/
}
 
