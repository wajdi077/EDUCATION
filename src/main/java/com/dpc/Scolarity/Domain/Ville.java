package com.dpc.Scolarity.Domain;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ville {
	
	@Id
	@GeneratedValue
	private Long  id;

	@Column(unique = false, nullable = false)
	private String nom;
	
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "regionId")
	private Region region;
		  
	/*
	 * @OneToMany(mappedBy = "ville", cascade =CascadeType.ALL,fetch=FetchType.LAZY)
	 * 
	 * @JsonIgnore private List<CodePostale>listcodepostale;
	 */

}
