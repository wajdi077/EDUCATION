package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Matiere;

public interface MatierRepository extends JpaRepository<Matiere, Long> {
Matiere findByNommatiereFR(String nommatiereFR);
List<Matiere> findByEtablissement(Etablissement e);
Matiere findByNommatiereFRAndEtablissement(String nommatiereFR,Etablissement e);
List<Matiere> findByEtablissementAndArchiverIsFalse(Etablissement e);
List<Matiere> findByEtablissementAndArchiverIsTrue(Etablissement e);

}
