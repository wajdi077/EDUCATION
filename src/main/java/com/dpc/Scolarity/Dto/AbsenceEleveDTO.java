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
public class AbsenceEleveDTO {
private String prenomEleve ;
private String dateAbsence;
private String matiere;
private String nomClasse;
private String type;
private String remarque;
}
