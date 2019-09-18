package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.Utilisateur;

public interface NiveauRepository extends JpaRepository<Niveau, Long>{
	
	
	List <Niveau> findByEtablissement(Etablissement etablissement);	
	Niveau findBynomniveau(String nomniveau);	
//Niveau findByNiveau(String niveau) ; 
}
