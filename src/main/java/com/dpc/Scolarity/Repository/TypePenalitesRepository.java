package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Type_Penalites;

public interface TypePenalitesRepository extends JpaRepository<Type_Penalites, Long> {

	
	
	Type_Penalites   findByNom(String nom );
	
}
