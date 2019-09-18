package com.dpc.Scolarity.service.implementation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.Scolarity.Domain.Anneescolaire;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.AnneeScolaireDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.SalleRepository;

@Service

public class SalleService extends MainController   {
	

	SalleDTO salleDTO = null ;
	@Autowired
	SalleRepository sallerepository ;
	@Autowired
	IUtilisateur userrepo;
//*************** Adding Salle *******
	
	public SalleDTO save(SalleDTO salleDTO) {
		
		Salle s = dtoToSalle(salleDTO);
		s.setArchiver(false);
		Utilisateur u = this.userrepo.findByUsername(salleDTO.getUsername());
		s.setEtablissement(u.getEtablissement());
		Salle sallecreated = this.sallerepository.save(s);
		return convertSalleToDto(sallecreated);
	}
	//******* update salle **************************
	public SalleDTO update(Salle salleDTO) {
			Salle sallecreated = this.sallerepository.save(salleDTO);
		return convertSalleToDto(sallecreated);
	}

//******** find salle by id ********
	public SalleDTO findOne(Long id) {
	Salle s = this.sallerepository.findOne(id);
	SalleDTO salle = convertSalleToDto(s);
		return salle;
	}
	//*************** recherche salle par son nom et retourne l'objet DTO trouvé ******
	public SalleDTO findByNomSalle(String nomSalle){
	Salle s = this.sallerepository.findByNomSalle(nomSalle);
	SalleDTO salle = convertSalleToDto(s);
	
		return salle;
	}
	//*************** recherche salle par son nom et retourne l'objet  trouvé ******
	public Salle findByNomSalles(String nomSalle){
	Salle s = this.sallerepository.findByNomSalle(nomSalle);
	return s;
	}
// DELETE SALLE SELECTIONNEE
	
	public void delete( Salle salletodelete) {
		this.sallerepository.delete(salletodelete);
	}
	// FONCTION QUI RETOURNE UNE LISTE DES SALLES 
	public Collection<SalleDTO> findAll(String username) {
		System.out.println("avant username");
		System.out.println(username);
		Utilisateur u = this.userrepo.findByUsername(username);
		
		return this.sallerepository.findByEtablissementAndArchiverIsFalse(u.getEtablissement()).stream().map(entity -> convertSalleToDto(entity)).collect(Collectors.toList());
	
	}
	public Collection<SalleDTO> findAllarchiver(String username) {
		System.out.println("avant username");
		System.out.println(username);
		Utilisateur u = this.userrepo.findByUsername(username);
		
		return this.sallerepository.findByEtablissementAndArchiverIsTrue(u.getEtablissement()).stream().map(entity -> convertSalleToDto(entity)).collect(Collectors.toList());
	
	}

}
