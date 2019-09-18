package com.dpc.Scolarity.service.implementation;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Dto.HeuresDTO;
import com.dpc.Scolarity.Dto.JoursDTO;
import com.dpc.Scolarity.Repository.HeuresRepository;
/**
 * @author slim
 *
 */
@Service
public class HeuresService extends MainController {
	
	@Autowired
	HeuresRepository heuresrepository ;
	
	public HeuresDTO save(HeuresDTO heuresDTO) {
		Heures h = dtoToHeures(heuresDTO);
		Heures he = this.heuresrepository.save(h);
		HeuresDTO heurescreated = convertHeuresToDto(he);
		
		return heurescreated;
	}

	public HeuresDTO findOne(Long id) {
		Heures h = this.heuresrepository.findOne(id);
		HeuresDTO heuredto = convertHeuresToDto(h);
		return heuredto;
	}


	public void delete(Long id) {
this.heuresrepository.delete(id);
	}
	public Collection<Heures> findAll() {

		return this.heuresrepository.findAll().stream().map(entity->entity).collect(Collectors.toList());
	
	}
}
