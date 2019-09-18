package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Note_enseignant;

public interface NoteEnseignantRepository extends JpaRepository< Note_enseignant, Long> {

}
