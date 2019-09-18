package com.dpc.Scolarity.Domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * @author slim
 *
 */
@Entity
public class Type_Penalites {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id ;
	private Boolean archiver ;
	 private String nom ;
	 @OneToMany(mappedBy="type",fetch=FetchType.LAZY)
	 private Collection<Penalites> penlatitees ;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Type_Penalites(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Type_Penalites() {
		super();
	}

	public Type_Penalites(String nom) {
		super();
		this.nom = nom;
	}
	 
	 
	
	

}
