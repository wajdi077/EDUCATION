package com.dpc.Scolarity.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Repository.PenaliteRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "pdf")
public class PdfController {
	
	@Autowired
	PenaliteRepository penaliteRepo;

		 @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
		            produces = MediaType.APPLICATION_PDF_VALUE,consumes = MediaType.APPLICATION_PDF_VALUE)
		    public ResponseEntity<InputStreamResource> citiesReport(long id ) {

		    //    List<Utilisateur> users = (List<Utilisateur>) utilisateurService.findAll();
		        Penalites p =  penaliteRepo.getOne(id);
		        ByteArrayInputStream bis = com.dpc.Scolarity.Util.GeneratePdfReport.penalitesReport(p);

		         HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=usersreport.pdf");

		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
		    }
		 //*************************generer pdf retenu**************************
		    @RequestMapping(value = "/pdfretenu", method = RequestMethod.GET,
		    		 produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
		    public ResponseEntity<byte[]> retenuReport(long id ) throws IOException {

		       // List<Utilisateur> users = (List<Utilisateur>) utilisateurService.findAll();
		        Penalites p =  penaliteRepo.getOne(id);

		        ByteArrayInputStream bis = com.dpc.Scolarity.Util.GeneratePdfReport.retenuReport(p);
		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=retenu.pdf");

		        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(IOUtils.toByteArray(bis));
		    }
}
