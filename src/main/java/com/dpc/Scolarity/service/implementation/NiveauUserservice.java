package com.dpc.Scolarity.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.Scolarity.Domain.Classeleves;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.NiveauUser;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.NiveauRepository;
import com.dpc.Scolarity.Repository.NiveauUserRepository;
@Service

public class NiveauUserservice {
	@Autowired
	private IUtilisateur iUtilisateur ; 
	@Autowired
	NiveauRepository niveauRepository ; 
@Autowired
NiveauUserRepository niveauuserepos ; 
	

}
