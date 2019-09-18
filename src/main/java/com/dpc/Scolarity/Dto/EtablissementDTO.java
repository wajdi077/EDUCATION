/**
 * 
 */
package com.dpc.Scolarity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author slim
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EtablissementDTO {
	private Long Id ;
	private String libelle;
	private String description ;
	private String adresse ;
	private String email ; 
	private String photo ;
	private String telephone ; 
}
