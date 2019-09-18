package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Utilisateur;

public interface EnseignantRepository extends JpaRepository<Utilisateur, Long> {

	
	public List<Utilisateur> findByProfilAndEtablissementAndArchiverIsFalse(String profil, Etablissement etablissement);

	List<Utilisateur> findById(long id);
	List<Utilisateur>findByProfilNotLike(String Enseignant , String Parain );
	@Query(nativeQuery=true,value="SELECT * FROM public.utilisateur	where profil != 'Enseignant' And profil != 'Parrain' And etablissement_id=:etablissement ")
	List<Utilisateur> afficherlesutilisateurNotLikeEnseignantetparain(@Param("etablissement") Long id );

	//List<Utilisateur> findByProfilNotContainingAndNotContaining(String a,String b);
	List<Utilisateur> findByProfilNotContaining(String Enseignant);
}
