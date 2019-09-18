package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.HistoriqueClasse;
import java.util.Collection;

public interface HistoriqueClasseRepository extends JpaRepository<HistoriqueClasse, Long>{
	
	Collection<HistoriqueClasse> findByArchiverIsFalse();

}
