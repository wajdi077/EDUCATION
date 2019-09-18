package com.dpc.Scolarity.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Programme;


@Repository
public interface Seancescolaire extends JpaRepository<Programme, Long>{

	List<Programme> findByClasse(Classes c);
	//List<Programme> charcherpardateconexion(Date date );
	
	
}
