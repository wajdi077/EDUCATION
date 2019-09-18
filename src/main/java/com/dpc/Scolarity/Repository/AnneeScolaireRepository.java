package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Anneescolaire;

public interface AnneeScolaireRepository extends JpaRepository<Anneescolaire ,Long> {
	Anneescolaire findByAnneeScolaire(String annescolaire);

}
