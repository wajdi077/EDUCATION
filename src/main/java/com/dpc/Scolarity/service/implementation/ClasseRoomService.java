package com.dpc.Scolarity.service.implementation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.IUtilisateur;
@Service
public class ClasseRoomService extends MainController  {

	@Autowired
	ClassesRepository classesrepository ;
	ClasseRoomDTO classeDTO = null ; 
	@Autowired
	IUtilisateur userrepo;
	public ClasseRoomDTO save(ClasseRoomDTO classeRoomDTO) {
		Classes c = dtoToClasses(classeRoomDTO);
		Utilisateur u = this.userrepo.findByUsername(classeRoomDTO.getUsername());
		c.setEtablissement(u.getEtablissement());
		c.setArchiver(false);
		Classes classeCreated = this.classesrepository.save(c);
		ClasseRoomDTO  Classdto = convertClasseToDto(classeCreated);
		return Classdto;
	}
public ClasseRoomDTO update(Classes classeRoomDTO) {
		Classes classeCreated = this.classesrepository.save(classeRoomDTO);
		return convertClasseToDto(classeCreated);
	}

	public ClasseRoomDTO findOne(Long id) {
		Classes c = this.classesrepository.findOne(id);
		ClasseRoomDTO cl = convertClasseToDto(c);
		return cl;
	}


	public void delete(Classes obj ) {
	this.classesrepository.delete(obj);
	}
	
	public Collection<ClasseRoomDTO> findAll() {

		return this.classesrepository.findAll().stream().map(entity -> convertClasseToDto(entity)).collect(Collectors.toList());
	
	}
	public Collection<ClasseRoomDTO> findAll(String username) {
		Utilisateur u = this.userrepo.findByUsername(username);
		return this.classesrepository.findByEtablissement(u.getEtablissement()).stream().map(entity -> convertClasseToDto(entity)).collect(Collectors.toList());
	
	}
	//*************** recherche Classes par son nom et retourne l'objet  trouvé ******
	public Classes findByNomClasses(String nomclasses){
	Classes s = this.classesrepository.findByNomclasse(nomclasses);
	return s;
	}
	//*************** recherche Classes par son nom et retourne l'objet DTO trouvé ******
	public ClasseRoomDTO findByNomClasse(String nomclasses){

		System.out.println("test: "+ nomclasses);
		Classes s = this.classesrepository.findByNomclasse(nomclasses);
		System.out.println("classe:"+ s.getNomclasse());
		ClasseRoomDTO c = convertClasseToDto(s);
		return c;
	}
	public ClasseRoomDTO findById(Long id){

		Classes s = this.classesrepository.getOne(id);
		
		ClasseRoomDTO c = convertClasseToDto(s);
		return c;
	}
public List<Classes> findbyniveau(String niveau){
	
	return this.classesrepository.findByNiveau(niveau);
	
}
}
