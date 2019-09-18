/**
 * 
 */
package com.dpc.Scolarity.Dto;

import java.util.Date;

import com.dpc.Scolarity.Domain.Utilisateur;

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
public class EleveParrainDTO {

	private EleveDTO elevedto ;
private Utilisateur user ; 
	
	
}
