package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Etablissement;

public interface ClassesRepository extends JpaRepository<Classes, Long> {

	Classes findByNomclasse(String nom_classe);
	List<Classes> findByNiveau(String niveau);
	List<Classes> findByEtablissement(Etablissement e);
	
	List<Classes> findByEtablissementAndArchiverIsTrue(Etablissement e);
	List<Classes> findByEtablissementAndArchiverIsFalse(Etablissement e);
	Classes findByNomclasseAndEtablissement(String nom_classe,Etablissement e);
	
	@Query(nativeQuery=true,value="SELECT r.nom ,count(cl) FROM Classes cl INNER JOIN Etablissement et ON cl.etablissement_id=et.id INNER JOIN code_postale c ON et.codepostale_id=c.id INNER JOIN Ville v ON c.ville_id=v.id INNER JOIN Region r ON v.region_id=r.id GROUP BY r.nom  ORDER BY r.nom ASC")
	List<Object> countClassesParRegion( );
	
	

}
