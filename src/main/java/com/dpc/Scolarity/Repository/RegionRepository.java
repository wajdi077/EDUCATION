package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpc.Scolarity.Domain.Region;
@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{

	Region findByNom(String nom);

}
