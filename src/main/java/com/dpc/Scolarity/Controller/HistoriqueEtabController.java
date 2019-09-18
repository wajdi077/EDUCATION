package com.dpc.Scolarity.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.HistoriqueClasse;
import com.dpc.Scolarity.Domain.HistoriqueEtab;
import com.dpc.Scolarity.Domain.HistoriqueUser;
import com.dpc.Scolarity.Repository.HistoriqueClasseRepository;
import com.dpc.Scolarity.Repository.HistoriqueEtabRepository;
import com.dpc.Scolarity.Repository.HistoriqueUserRepository;

@Controller
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/historique")
public class HistoriqueEtabController {
	
	@Autowired
	HistoriqueEtabRepository hisrepo;
	@Autowired
	HistoriqueClasseRepository hisClssRepo;
	@Autowired
	HistoriqueUserRepository hisUserRepo;
	
	@RequestMapping(value = "/allHisEtab", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<HistoriqueEtab> getAllHisEtab(  ) {
	
		return this.hisrepo.findByArchiverIsFalse() ;
		
}

	@RequestMapping(value = "/allHisClass", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<HistoriqueClasse> getAllHisClass(  ) {
	
		return this.hisClssRepo.findByArchiverIsFalse() ;
		
}
	@RequestMapping(value = "/allHisUsers", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_Admin')")
	public Collection<HistoriqueUser> getAllHisUser(  ) {
	
		return this.hisUserRepo.findByArchiverIsFalse() ;
		

}


	
}
