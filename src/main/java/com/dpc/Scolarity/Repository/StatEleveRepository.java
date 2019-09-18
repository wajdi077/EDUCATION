package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dpc.Scolarity.Domain.Eleve;

public interface StatEleveRepository extends JpaRepository<Eleve, Long>{

	@Query(nativeQuery=true,value="SELECT r.nom ,count(e) FROM Eleve e INNER JOIN Etablissement et ON e.etablissement_id=et.id INNER JOIN code_postale c ON et.codepostale_id=c.id  INNER JOIN Ville v ON c.ville_id=v.id  INNER JOIN Region r ON v.region_id=r.id GROUP BY r.nom  ORDER BY r.nom ASC")
	List<Object> countElevesParRegion( );
	
}
