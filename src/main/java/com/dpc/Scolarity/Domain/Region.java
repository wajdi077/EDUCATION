package com.dpc.Scolarity.Domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Region {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = false)
	private String nom;
	

	
	 
	/*
	 * @OneToMany(mappedBy = "region", cascade =
	 * CascadeType.ALL,fetch=FetchType.LAZY)
	 * 
	 * @JsonIgnore private List<Ville>listville;
	 */
	
	 

}
