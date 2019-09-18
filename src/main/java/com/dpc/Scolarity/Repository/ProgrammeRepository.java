package com.dpc.Scolarity.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Programme;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.Utilisateur;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
	
	List<Programme> findByProf(Utilisateur prof);
	List<Programme> findByClasse(Classes classe);
	List<Programme> findByProfAndJour(Utilisateur prof , Jours jour);
	List<Programme> findByProfOrderByDateprogAsc(Utilisateur prof);

  	List<Programme> findByClasse_Id(Long id);
  	
  	List<Programme> findByProfAndSemaine(Utilisateur prof,Semaine semaine);
	
//SELECT * FROM table ORDER BY date ASC
	
	
	@Query("SELECT p FROM Programme p WHERE p.prof = :prof  ")
	List<Programme> getByProf(@Param("prof") Utilisateur prof);
	
	Programme findByProf(String prof );
	Programme findById(Long id);
	
	Programme findByProfAndJourAndHeure(Utilisateur prof , Jours jour, Heures heure );

	Programme findByProfAndDateprogLessThanEqualAndDatefinprogGreaterThanEqual(Utilisateur prof , Date datedebut,Date datefin);
	@Query("SELECT p FROM Programme p WHERE p.prof = :prof AND now() > p.dateprog and now() < p.datefinprog ")
	Programme getprognow(@Param("prof") Utilisateur prof);
	@Query("SELECT p FROM Programme p WHERE p.prof = :prof AND  p.dateprog <= :madate   ")
	List<Programme> findByProfAndDateprogs(@Param("prof") Utilisateur prof,@Param("madate") Date madate);
	
	
	List<Programme> findByDatedejour(Date d );
}
