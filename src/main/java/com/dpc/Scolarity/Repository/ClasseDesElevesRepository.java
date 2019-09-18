package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Anneescolaire;
import com.dpc.Scolarity.Domain.Classeleves;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;

public interface ClasseDesElevesRepository extends JpaRepository<Classeleves, Long>

{

	public List<Classeleves> findByMyclassAndAnneeScolaire(Classes classe,Anneescolaire anneescolaire);
	 public  Classeleves findByEleveAndAnneeScolaire(Eleve eleve ,Anneescolaire anneescolaire );
	 public  List<Classeleves> findByEleve(Eleve eleve ) ;
	 public List<Classeleves> findByAnneeScolaire(Anneescolaire anneescolaire) ;
	
}
