package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.SemaineAnneeScolaire;

public interface SemAnneeScolaireRepository extends JpaRepository<SemaineAnneeScolaire, Long> 
{
	
	SemaineAnneeScolaire findByS(Semaine s );
	
	
	

}
