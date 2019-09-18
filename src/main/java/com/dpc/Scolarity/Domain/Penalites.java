package com.dpc.Scolarity.Domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Penalites implements Serializable  {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_penalites ;
	private Date date_Penalites ;
	@Temporal(TemporalType.DATE)
	private Date datejour ;
	
	@ManyToOne
	private Eleve eleves ;
	
	@ManyToOne 
	private Type_Penalites type ;
	@ManyToOne 
	private Programme programme ;
	
	private String motif ;
	private String remarque ;
	private Boolean archiver ;
	

	
	

}
