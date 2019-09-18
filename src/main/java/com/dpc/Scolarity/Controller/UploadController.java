package com.dpc.Scolarity.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Repository.EtablissementRepository;
import com.dpc.Scolarity.service.StorageService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "image")
public class UploadController {
 
  @Autowired
  StorageService storageService;
  @Autowired
  EtablissementRepository etabrepo;
 
  List<String> files = new ArrayList<String>();
 
  @PostMapping("/post")
  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);
      files.add(file.getOriginalFilename());
 
      message = "You successfully uploaded " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "FAIL to upload " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
 /* @RequestMapping(value = "/addstore", method = RequestMethod.POST)
  public ResponseEntity<String> saveStore(@ModelAttribute("store") Etablissement etab, 
          @RequestParam(value = "file") MultipartFile image){
	  String message = "";
	  try {
	  storageService.store(image);
	  etabrepo.save(etab);
	  return ResponseEntity.status(HttpStatus.OK).body(message);
  } catch (Exception e) {

    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);}
}*/
 
  @GetMapping("/getallfiles")
  public ResponseEntity<List<String>> getListFiles(@RequestParam String libelle, Model model) {
    List<String> fileNames =this.etabrepo.filenamesactivites(libelle) 
        .stream().map(fileName -> MvcUriComponentsBuilder
            .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
        .collect(Collectors.toList());
 
    return ResponseEntity.ok().body(fileNames);
  }
 
  @RequestMapping(value="/files/{filename:.+}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable String filename) throws IOException {
		Resource file = storageService.loadFile(filename);
		byte[] bytes = StreamUtils.copyToByteArray(file.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
	}
}
