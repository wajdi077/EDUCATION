package com.dpc.Scolarity.Controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dpc.Scolarity.service.implementation.ReferenceService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@RestController
//@RequestMapping(value = "api/referentiel")


public class AdresseController {
	@Autowired
	ReferenceService referenceService;
	
	
	
	static final int ZERO = 0;
	static final int UN = 1;
	static final int DEUX = 2;
	static final int TROIS = 3;
	static final int QUATRE = 4;
	static final int CINQ = 5;
	static final int SIX = 6;
	static final int SEPT = 7;
	static final int HUIT = 8;
	
	@SuppressWarnings("resource")
	@ApiOperation(value = "initAdresse", notes = "Init Region,Ville et Codepostale from an excel "+ "file", response = String.class)

	@RequestMapping(value = "/initAdresse", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initAdresse(@RequestParam("file") MultipartFile file) throws IOException {

		File referenceFile = convertFromMultiPart(file);

		String regionName;
		String villeName;
		String codePostaleName;
		Double codePostaleCode;
		
		try {
			FileInputStream excelFile = new FileInputStream(referenceFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet region = workbook.getSheetAt(ZERO);
			Sheet ville = workbook.getSheetAt(UN);
			Sheet codePostale = workbook.getSheetAt(DEUX);
			

			Iterator<Row> itLigneRegion = region.iterator();
			Iterator<Row> itLigneVille = ville.iterator();
			Iterator<Row> itLigneCodePostale = codePostale.iterator();
			Row currentLigneRegion = itLigneRegion.next();
			Row currentLigneVille = itLigneVille.next();
			Row currentLigneCodePostale = itLigneCodePostale.next();
			
			

			// insérer les regions du referentiel
			while (itLigneRegion.hasNext()) {
				currentLigneRegion = itLigneRegion.next();
				if ((currentLigneRegion.getCell(0) != null)
						&& !(currentLigneRegion.getCell(0).toString().equalsIgnoreCase(""))) {
					regionName = currentLigneRegion.getCell(ZERO).getStringCellValue();

					this.referenceService.ajouterRegion(regionName);
				}
			}
			/* insérer les villes du referentiel*/
			while (itLigneVille.hasNext()) {
				currentLigneVille = itLigneVille.next();
				if ((currentLigneVille.getCell(0) != null)
						&& !(currentLigneVille.getCell(0).toString().equalsIgnoreCase(""))) {
					villeName = currentLigneVille.getCell(ZERO).getStringCellValue();
					regionName = currentLigneVille.getCell(UN).getStringCellValue();
					this.referenceService.createVille(regionName, villeName);
				}
			}
			// insérer les codePostel du referentiel
			while (itLigneCodePostale.hasNext()) {
				currentLigneCodePostale = itLigneCodePostale.next();
				if ((currentLigneCodePostale.getCell(0) != null)
						&& !(currentLigneCodePostale.getCell(0).toString().equalsIgnoreCase(""))) {
					codePostaleName = currentLigneCodePostale.getCell(ZERO).getStringCellValue();
					codePostaleCode = currentLigneCodePostale.getCell(UN).getNumericCellValue();					villeName = currentLigneCodePostale.getCell(DEUX).getStringCellValue();
					this.referenceService.createCodePostale(codePostaleName, codePostaleCode, villeName);
				}
			}
			
		
			return "The referetiel service was passed successfully : Region,Ville et Codepostale have been "
					+ "initialized.";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}

	public File convertFromMultiPart(MultipartFile multipartFile) throws IllegalStateException, IOException {
		File convFile = new File(FilenameUtils.getName(multipartFile.getOriginalFilename()));
		multipartFile.transferTo(convFile);
		return convFile;
	}


}
