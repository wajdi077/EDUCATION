package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Utilisateur;

public interface SalleRepository extends JpaRepository<Salle,Long> {
	
	
	Salle findByNomSalle( String nomSalle );
	List<Salle> findByEtablissement(Etablissement etablissement);
	List<Salle> findByEtablissementAndArchiverIsFalse(Etablissement etablissement);
	List<Salle> findByEtablissementAndArchiverIsTrue(Etablissement etablissement);
}
