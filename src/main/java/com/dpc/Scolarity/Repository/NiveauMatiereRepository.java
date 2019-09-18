package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Niveau_Matiere;


public interface NiveauMatiereRepository extends JpaRepository<Niveau_Matiere, Long> {
		Matiere findByMatiere(Matiere matiere);
		
}
