/**
 * 
 */
package com.dpc.Scolarity.Dto;

import java.util.List;

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
public class AbsenceDTO {
	private List<String> numInscription ;
	private Long idProgramme ;

}
