package com.dpc.Scolarity.Domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CodePostale {
	
	@Id
	@GeneratedValue
	Long id;

	@Column(unique = false, nullable = false)
	private String nom;

	@Column(unique = false, nullable = false)

	//private Long code;
	
  //  @JsonIgnore
//	private Long code;
    
    @JsonIgnore
	private Double code;
    
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "villeId")
	private Ville ville;

}
