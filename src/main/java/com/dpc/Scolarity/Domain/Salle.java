package com.dpc.Scolarity.Domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Salle  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idsalle ;
	private String nomSalle;
	private String Type ;
	private String remarque ;
	
	private Boolean archiver ;
	@OneToMany(mappedBy="salle",fetch=FetchType.LAZY)
	private Collection<Programme> emploidesalle ;

	@ManyToOne
	private Etablissement etablissement ; 
	public Salle(String nomSalle, String type, String remarque) {
		super();
		this.nomSalle = nomSalle;
		Type = type;
		this.remarque = remarque;
	}
	
	
	

}
