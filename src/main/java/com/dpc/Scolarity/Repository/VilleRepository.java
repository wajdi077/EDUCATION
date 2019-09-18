package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpc.Scolarity.Domain.Ville;
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long>{

	Ville findByNom(String nom);

}
