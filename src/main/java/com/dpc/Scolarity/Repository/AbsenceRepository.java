/**
 * 
 */
package com.dpc.Scolarity.Repository;





import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.AbsencesEleve;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Programme;


/**
 * @author slim
 *
 */
public interface AbsenceRepository  extends JpaRepository<AbsencesEleve,Long>{

	@Query("SELECT COUNT(u) FROM AbsencesEleve u WHERE u.date_absence >= :date_absence")
	int CountAbsenceByDate(@Param("date_absence") Date date_absence);
	
	List<AbsencesEleve> findByProgramme(Programme programme);
	List<AbsencesEleve> findByE(Eleve e);
	
	
	
	
	
}
