package com.dpc.Scolarity.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dpc.Scolarity.Domain.HistoriqueUser;

public interface HistoriqueUserRepository extends JpaRepository<HistoriqueUser, Long>{

	Collection<HistoriqueUser> findByArchiverIsFalse();

}
