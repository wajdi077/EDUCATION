package com.dpc.Scolarity.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.HistoriqueEtab;

public interface HistoriqueEtabRepository extends JpaRepository<HistoriqueEtab, Long> {

	Collection<HistoriqueEtab> findByArchiverIsFalse();
	

	
	
}
