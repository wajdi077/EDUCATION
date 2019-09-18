package com.dpc.Scolarity.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Dto.DashboardDirecteurDTO;
import com.dpc.Scolarity.Repository.Seancescolaire;
import com.dpc.Scolarity.service.Dashboarddirecteur;

@Service
public class boardService  extends MainController implements Dashboarddirecteur{
	
	@Autowired
	Seancescolaire seancescolaire ;

	@Override
	public List<DashboardDirecteurDTO> afficher() {
		
 
		List<Programme> programmes = this.seancescolaire.findAll();
		
		
        List<DashboardDirecteurDTO> programmeDTOs = programmes.stream()
                .map(programme -> convertdashboardToDTO(programme))
                .collect(Collectors.toList());

    return  programmeDTOs;
    
	}

	@Override
	public List<DashboardDirecteurDTO> afficher(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
