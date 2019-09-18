package com.dpc.Scolarity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dpc.Scolarity.Domain.Niveau_Matiere;

public interface NombreheureRepository extends JpaRepository <Niveau_Matiere,Long> {
	@Query(nativeQuery=true,value="SELECT m.nommatierefr , n.nomniveau , nm.nbheures  from niveau_matiere nm INNER JOIN matiere m ON nm.matiere_idmatiere=m.idmatiere INNER JOIN  niveau n ON nm.niveau_idniveau = n.idniveau  GROUP BY n.nomniveau, m.nommatierefr,nm.nbheures")
	List<Object> nombreheureparmatiereparniveau();


}
