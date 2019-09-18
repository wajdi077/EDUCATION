/**
 * 
 */
package com.dpc.Scolarity.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Etablissement;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.User_Etablissements;
import com.dpc.Scolarity.Domain.Utilisateur;

/**
 * @author user
 *
 */
public interface UserEtablissementRepository  extends JpaRepository<User_Etablissements,Long> {

User_Etablissements findByEnseignantAndEtablissement(Utilisateur u,Etablissement e);
User_Etablissements findByEtablissement(Etablissement e);
Collection<User_Etablissements> findByEnseignant(Utilisateur u);

}
