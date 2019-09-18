package com.dpc.Scolarity.Controller;

import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Dto.ProgrammeDTO;
import com.dpc.Scolarity.Dto.ProgrammeEmploiDTO;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.service.implementation.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping(value = "api/programmes")
public class ProgrammeController {

    @Autowired
    ProgrammeService programmeService;
 
    @GetMapping("/programme/byinscription")
    public List<ProgrammeEmploiDTO> programmesByNumInscription(@RequestParam(value = "Num_Inscription", required = true) String numInscription ){

        return programmeService.emploiByEleve(numInscription);
    } 
    @GetMapping("/programme/classe")
    public List<ProgrammeEmploiDTO> programmesByClasse(@RequestParam(value = "Num_Inscription", required = true) Long id ){

        return programmeService.emploisbyclasse(id);
    }
    
    @GetMapping("/programme/enseignant")
    public List<ProgrammeEmploiDTO> programmesByEnseignant(@RequestParam(value = "username", required = true) String username ){

        return programmeService.emploisbyEnseignant(username);
    }  
}
