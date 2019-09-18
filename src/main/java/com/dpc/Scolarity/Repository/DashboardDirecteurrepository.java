package com.dpc.Scolarity.Repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Utilisateur;

public interface DashboardDirecteurrepository   extends JpaRepository<Programme, Long> {
List<Programme> findByProf(Utilisateur prof);

List<Programme> findByProfAndDatedejour(Utilisateur prof,Date date)	;
	

}
