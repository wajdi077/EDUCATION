package com.dpc.Scolarity.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Type_Penalites;

public interface MotifRepository extends JpaRepository<Penalites, Long> {
	
@Query(nativeQuery=true,value="SELECT e.niveau_etude , motif , COUNT(p) from penalites p INNER JOIN eleve e ON p.eleves_id=e.id INNER JOIN etablissement etab ON e.etablissement_id = etab.id WHERE (CURRENT_DATE=datejour)   GROUP BY e.niveau_etude, motif")
List<Object> countPenalitesByNiveauByEtablissement();







//SELECT e.niveau_etude , motif , COUNT(p) from penalites p INNER JOIN eleve e ON p.eleves_id=e.id INNER JOIN etablissement etab ON e.etablissement_id = etab.id WHERE (CURRENT_DATE=datejour)   GROUP BY e.niveau_etude, motif


}
