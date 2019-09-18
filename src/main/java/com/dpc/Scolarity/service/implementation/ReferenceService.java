package com.dpc.Scolarity.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.CodePostale;
import com.dpc.Scolarity.Domain.Region;
import com.dpc.Scolarity.Domain.Ville;
import com.dpc.Scolarity.Repository.CodePostaleRepository;
import com.dpc.Scolarity.Repository.RegionRepository;
import com.dpc.Scolarity.Repository.VilleRepository;

@Service

public class ReferenceService extends MainController {
	
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CodePostaleRepository codepostaleRepository;
	
	
	
	
	
	public void   ajouterRegion(String nameRegion) {
		Region region = new Region();
		region.setNom(nameRegion);
		 this.regionRepository.save(region);
	}

	public void createVille(String regionName, String villeName) {
		Region region = regionRepository.findByNom(regionName);
		Ville ville = new Ville();
		ville.setNom(villeName);
		ville.setRegion(region);
		 this.villeRepository.save(ville);

	}

	public void createCodePostale(String codePostaleName, Double codePostaleCode, String villeName) {
		Ville ville = villeRepository.findByNom(villeName);
		CodePostale cpostale = new CodePostale();
		cpostale.setCode(codePostaleCode);
		cpostale.setNom(codePostaleName);
		cpostale.setVille(ville);
		 this.codepostaleRepository.save(cpostale);

	}

}
