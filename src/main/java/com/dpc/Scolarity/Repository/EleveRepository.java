package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.EleveDTO;
import com.dpc.Scolarity.Dto.EleveParrainDTO;

public interface EleveRepository extends JpaRepository<Eleve, Long> {
	Eleve findByNomAndPrenom(String nom , String prenom);
	List<Eleve> findByNom(String nom);

	List <Eleve> findByNiveauEtude(String niveau);
	List <Eleve> findByParrain(Utilisateur user);
	Eleve findByNumInscription(String numInscription); 
	List<Eleve> findByClasseActuel(String nomclasse);
	List<Eleve>	findByEtablissement(Etablissement e);
	List<Eleve> findByClasseActuelAndEtablissement(String nomclasse,Etablissement e);
	List<Eleve> findByClasseActuelAndEtablissementAndArchiverIsFalse(String nomclasse,Etablissement e);
	List<Eleve> findByClasseActuelAndEtablissementAndArchiverIsTrue(String nomclasse,Etablissement e);
	List<Eleve>	findByEtablissementAndArchiverIsTrue(Etablissement e);
	List<Eleve>	findByEtablissementAndArchiverIsFalse(Etablissement e);
	List<Eleve> findByEtablissementAndClasseActuelIsNull(Etablissement e);
	 @Query(nativeQuery=true,value="SELECT * FROM public.eleve where  parrain_id Is NULL  ")
	  List<Eleve> afficherleselevesnonaffecter() ;
	

}
