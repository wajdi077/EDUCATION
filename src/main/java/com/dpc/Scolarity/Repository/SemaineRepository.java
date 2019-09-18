package com.dpc.Scolarity.Repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dpc.Scolarity.Domain.Semaine;

public interface SemaineRepository  extends JpaRepository<Semaine,Long>{
Semaine findBySemaine(String semaine);
@Query("SELECT u FROM Semaine u WHERE u.datefin >= now()")
Collection<Semaine> findSemaineafterlocaldate();

Collection<Semaine> findByDatefinGreaterThanEqual(Date d);
}
