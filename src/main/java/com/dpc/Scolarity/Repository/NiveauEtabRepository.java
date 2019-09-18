package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Niveau_etablissement;

public interface NiveauEtabRepository extends JpaRepository<Niveau_etablissement, Long> {



	List<Niveau_etablissement> findAllNiveauByEtablissement(Etablissement e);

}
