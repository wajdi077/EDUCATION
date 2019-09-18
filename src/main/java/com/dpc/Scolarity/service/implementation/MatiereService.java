package com.dpc.Scolarity.service.implementation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.MatiereDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.MatierRepository;
@Service
public class MatiereService  extends MainController{
	
	@Autowired
	MatierRepository matiererepository ;
	@Autowired
	IUtilisateur userrepo;
	public MatiereDTO save(MatiereDTO matiereDTO) {
		
		Matiere m = dtoToMatiere(matiereDTO);
		Utilisateur u = this.userrepo.findByUsername(matiereDTO.getUsername());
		m.setEtablissement(u.getEtablissement());
	//	m.setArchiver(false);
		Matiere matiere = this.matiererepository.saveAndFlush(m);
		MatiereDTO mdto = convertMatiereToDto(matiere);
		return mdto;
	}

	public Matiere findOne(Long id) {
		Matiere m = this.matiererepository.findOne(id);
		MatiereDTO dto = convertMatiereToDto(m);
		return m;
	}
	public MatiereDTO  update(Matiere matiere) {

		Matiere matier = this.matiererepository.saveAndFlush(matiere);
		MatiereDTO mdto = convertMatiereToDto(matier);
		return mdto;
}


	public void delete(Matiere m) {
	
		this.matiererepository.delete(m);
	}

	
	
	public List<Matiere> findAll() {

		return this.matiererepository.findAll();
	
	}

	public List<Matiere> findAll(String username) {
		Utilisateur u = this.userrepo.findByUsername(username);
		return this.matiererepository.findByEtablissementAndArchiverIsFalse(u.getEtablissement());
	
	}
	public List<Matiere> findAllMatiereArchiver(String username) {
		Utilisateur u = this.userrepo.findByUsername(username);
		return this.matiererepository.findByEtablissementAndArchiverIsTrue(u.getEtablissement());
	
	}
 public Matiere findBynomatiereEtablissement(String nommatierFR,Etablissement e ) {
	 
	 
	 return this.matiererepository.findByNommatiereFRAndEtablissement(nommatierFR, e);
 }
}
