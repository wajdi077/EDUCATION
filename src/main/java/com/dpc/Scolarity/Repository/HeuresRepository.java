package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Heures;

public interface HeuresRepository extends JpaRepository<Heures, Long> {
	
	
	Heures findByHeureDebut(String heureDebut);

}
