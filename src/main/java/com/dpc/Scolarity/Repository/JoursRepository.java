package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Jours;

public interface JoursRepository extends JpaRepository<Jours, Long> {

	
	Jours findByJour(String jour);
}
