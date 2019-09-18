package com.dpc.Scolarity.Domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Classes {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Column(name = "nomclasse")
	private String nomclasse ;
	private String niveau ;
	private String remarque ; 
	@ManyToOne
	private Etablissement etablissement ; 

	@OneToMany(mappedBy="myclass",fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<Classeleves> classeseleves ; 
	@OneToMany(mappedBy="classe",fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<Programme> emploideclasse ;
	private Boolean archiver ;


}
