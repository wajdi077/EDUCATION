package com.dpc.Scolarity.Dto;


import java.util.Date;


import lombok.Data;

@Data
public class NoteProfDTO {

	private String description;
	private String remarque ;
    private Date datenotification;
	private String time;

	

	private Long idprogramme ;
}
