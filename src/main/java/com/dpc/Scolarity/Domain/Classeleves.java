package com.dpc.Scolarity.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Classeleves {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ; 
	@ManyToOne
	private Anneescolaire anneeScolaire  ;
	@ManyToOne
	private Classes myclass ;
	@ManyToOne
	private Eleve eleve  ;
	private String statutEleve ;
	private Boolean archiver ;
}
