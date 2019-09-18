package com.dpc.Scolarity.Dto;

import java.util.Date;

import javax.persistence.ManyToOne;

import com.dpc.Scolarity.Domain.Utilisateur;

import lombok.Data;

@Data
public class MessageConvert {

	
	private String date_message ;
	private String duree ;
	private String message ;
	private String sujet ;
	private Utilisateur sender ;

	private Utilisateur reciever ;
	
}
