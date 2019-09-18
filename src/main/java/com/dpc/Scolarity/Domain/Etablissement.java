/**
 * 
 */
package com.dpc.Scolarity.Domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Etablissement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id ;
	private String libelle;
	private String description ;
	private String adresse ;
	private String email ; 
	private String photo ;
	private String telephone ; 
	private Boolean archiver =false ;
	
	@ManyToOne
	@JoinColumn(name = "codepostaleId")
	@JsonBackReference
	private CodePostale codepostale;

	

	
	

}
