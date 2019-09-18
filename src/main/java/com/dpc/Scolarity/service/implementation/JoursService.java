package com.dpc.Scolarity.service.implementation;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Dto.JoursDTO;
import com.dpc.Scolarity.Dto.SalleDTO;
import com.dpc.Scolarity.Repository.JoursRepository;
@Service
public class JoursService extends MainController {

	
	@Autowired
	JoursRepository joursrepository ;
	
	public JoursDTO save(JoursDTO joursDTO) {
Jours j = dtoToJours(joursDTO) ;
Jours Jo = this.joursrepository.save(j);
         JoursDTO jourdt = convertJoursToDto(Jo);

return jourdt;
	}


	public JoursDTO findOne(Long id) {
Jours j = this.joursrepository.findOne(id)	;
JoursDTO jo = convertJoursToDto(j);

	
	
	return jo;
	}

	
	public void delete(Long id) {
		this.joursrepository.delete(id);
		
		
	}
	public Collection<JoursDTO> findAll() {

		return this.joursrepository.findAll().stream().map(entity -> convertJoursToDto(entity)).collect(Collectors.toList());
	
	}

}
