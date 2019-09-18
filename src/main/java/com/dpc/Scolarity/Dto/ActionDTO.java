package com.dpc.Scolarity.Dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ActionDTO {
	@Temporal(TemporalType.DATE)
	private  Date date_action ;
	private String typeAction ;
	private String remarque ;
}
