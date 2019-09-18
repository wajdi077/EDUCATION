package com.dpc.Scolarity.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Specialite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSpecialite ;
	private String nomSpecialite ;
}
