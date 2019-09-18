package com.dpc.Scolarity.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Penalites;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Type_Penalites;
import com.dpc.Scolarity.Dto.EleveDetailsDTO;

public interface PenaliteRepository extends JpaRepository<Penalites, Long> {
	
	
	@Query("SELECT COUNT(u) FROM Penalites u WHERE u.date_Penalites >= :date_Penalites and u.type = :type")
	
	int countByPenalitesBydateAndType(@Param("date_Penalites") Date date_Penalites,@Param("type") Type_Penalites type);
	List<Penalites> findByEleves(Eleve e );
	List<Penalites> findByMotif(String motif);
	
	@Query("SELECT motif, COUNT(u) as count FROM Penalites u WHERE u.eleves = :eleves GROUP BY motif")
	List<Object> getEleveByMotif(@Param("eleves") Eleve eleve);
List<Penalites> findByProgrammeAndMotif(Programme programme, String motif);


}
