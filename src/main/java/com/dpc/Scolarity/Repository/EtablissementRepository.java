/**
 * 
 */
package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Etablissement;

/**
 * @author slim
 *
 */
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
List<Etablissement> findByArchiverIsFalse();
Etablissement findByEmail(String email);
@Query("SELECT photo FROM Etablissement  a where a.libelle=:photoparam ")
public  List<String> filenamesactivites(@Param(value = "photoparam") String photoparam );

}
