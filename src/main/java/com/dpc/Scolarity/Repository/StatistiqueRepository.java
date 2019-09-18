package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dpc.Scolarity.Domain.Penalites;

public interface StatistiqueRepository extends JpaRepository<Penalites, Long>{
	
	@Query(nativeQuery=true,value="SELECT c.nomclasse ,motif  ,COUNT(u) FROM Penalites u	"
			+ "INNER JOIN Programme p ON u.programme_id = p.id	"
			+ "INNER JOIN Classes c ON c.id = p.classe_id  "
			+ "GROUP BY c.nomclasse,motif ORDER BY c.nomclasse ASC")
	List<Object> countPenalitesByClasse( );
	
	

 

}
