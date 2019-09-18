package com.dpc.Scolarity.Dto;

import java.util.Date;

import com.dpc.Scolarity.Domain.Message;
import com.dpc.Scolarity.Domain.Utilisateur;

import lombok.Data;
@Data
public class MessageDTO {

	private String reciever ;
	private String message ;
	private String sujet ;
	private String username;
}
