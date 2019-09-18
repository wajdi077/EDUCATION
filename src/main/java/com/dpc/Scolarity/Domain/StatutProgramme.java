package com.dpc.Scolarity.Domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author slim
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatutProgramme {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	private String statut;

	private Boolean archiver ;
}
