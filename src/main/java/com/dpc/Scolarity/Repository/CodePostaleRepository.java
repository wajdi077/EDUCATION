package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpc.Scolarity.Domain.CodePostale;
@Repository
public interface CodePostaleRepository extends JpaRepository<CodePostale, Long> {

}
