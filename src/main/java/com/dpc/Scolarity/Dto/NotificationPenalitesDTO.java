/**
 * 
 */
package com.dpc.Scolarity.Dto;

import java.util.Collection;
import java.util.Date;



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
public class NotificationPenalitesDTO {
private EleveDTO eleves ;
private Long id_penalites ;
private Date date_Penalites ;
private ProgrammeEmploiDTO programme ;
private String motif ;
private String remarque ;
}
