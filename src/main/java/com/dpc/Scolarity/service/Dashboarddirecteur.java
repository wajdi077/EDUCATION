package com.dpc.Scolarity.service;

import java.util.Date;
import java.util.List;

import com.dpc.Scolarity.Dto.DashboardDirecteurDTO;

public interface Dashboarddirecteur {
	
	List<DashboardDirecteurDTO> afficher();
	List<DashboardDirecteurDTO> afficher(Date date);
	

}
