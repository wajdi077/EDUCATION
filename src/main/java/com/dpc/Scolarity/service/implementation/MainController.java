package com.dpc.Scolarity.service.implementation;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.dpc.Scolarity.Domain.*;
import com.dpc.Scolarity.Dto.*;


public class MainController {
	
	static final int ZERO = 0;
	static final int UN = 1;
	static final int DEUX = 2;
	static final int TROIS = 3;
	static final int QUATRE = 4;
	static final int CINQ = 5;
	static final int SIX = 6;
	static final int SEPT = 7;
	static final int HUIT = 8;
	
	@Autowired
	protected ModelMapper modelMapper;
	
	
	protected LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public static Date convertToDateViaInstant(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	//*************Convert Entity To DTO (DATA TRANSFERT OBJECT)*****************************
	
	
	protected DashboardDirecteurDTO convertdashboardToDTO(Programme entity) {
		DashboardDirecteurDTO dto = modelMapper.map(entity, DashboardDirecteurDTO.class);
		return dto;
	}
	
	protected EtablissementDTO convertEtablissementToDTO(Etablissement entity) {
		EtablissementDTO dto = modelMapper.map(entity, EtablissementDTO.class);
		return dto;
	}
	protected NotificationPenalitesDTO convertPenalitesToNotificationPenalitesDTO(Penalites entity) {
		NotificationPenalitesDTO dto = modelMapper.map(entity, NotificationPenalitesDTO.class);
		return dto;
	}
	protected AnneeScolaireDTO convertAnneeScolaireToDto(Anneescolaire anneeScolaire) {
		AnneeScolaireDTO anneeScolaireDto = modelMapper.map(anneeScolaire, AnneeScolaireDTO.class);
		return anneeScolaireDto;
	}
	protected ProgrammeDTO convertProgrammeToDto(Programme entity) {
		ProgrammeDTO dto = modelMapper.map(entity, ProgrammeDTO.class);
		return dto;
	}
	protected EmploisDTO convertEmploisToDto(Programme entity) {
		EmploisDTO dto = modelMapper.map(entity, EmploisDTO.class);
		return dto;
	}
	protected ClassesDesEleveDTO convertClassesDesEleveToDto(Classeleves entity) {
		ClassesDesEleveDTO dto = modelMapper.map(entity, ClassesDesEleveDTO.class);
		return dto;
	}
	
	protected SemaineDTO convertSemaineToDto(Semaine semaine) {
		SemaineDTO semaineDto = modelMapper.map(semaine, SemaineDTO.class);
		return semaineDto;
	}
	protected JoursDTO convertJoursToDto(Jours entity) {
		JoursDTO entityDto = modelMapper.map(entity, JoursDTO.class);
		return entityDto;
	}
	protected HeuresDTO convertHeuresToDto(Heures entity) {
		HeuresDTO entityDto = modelMapper.map(entity, HeuresDTO.class);
		return entityDto;
	}
	protected UserDTO convertUtilisateurToDto(Utilisateur entity) {
		UserDTO entityDto = modelMapper.map(entity, UserDTO.class);
		return entityDto;
	}
	protected MessageDTO convertMessageToDto(Message entity) {
		MessageDTO entityDto = modelMapper.map(entity, MessageDTO.class);
		return entityDto;
	}
	protected ClasseRoomDTO convertClasseToDto(Classes entity) {
		ClasseRoomDTO entityDto = modelMapper.map(entity, ClasseRoomDTO.class);
		return entityDto;
	}
	protected AuthorityDTO convertAuthorityToDto(Authority entity) {
		AuthorityDTO entityDto = modelMapper.map(entity, AuthorityDTO.class);
		return entityDto;
	}
	protected SalleDTO convertSalleToDto(Salle entity) {
		SalleDTO entityDto = modelMapper.map(entity, SalleDTO.class);
		return entityDto;
	}
	protected MatiereDTO convertMatiereToDto(Matiere entity) {
		MatiereDTO entityDto = modelMapper.map(entity, MatiereDTO.class);
		return entityDto;
	}
	
	
	protected EleveDTO convertEleveToDto(Eleve entity) {
		EleveDTO entityDto = modelMapper.map(entity, EleveDTO.class);
		return entityDto;
	}
	protected PenalitesDTO convertPenalitesToDto(Penalites entity) {
		PenalitesDTO entityDto = modelMapper.map(entity, PenalitesDTO.class);
		return entityDto;
	}
	
	protected TypePenalitesDTO convertTypePenalitesToDto(Type_Penalites entity) {
		TypePenalitesDTO entityDto = modelMapper.map(entity, TypePenalitesDTO.class);
		return entityDto;
	}
	protected StatutProgrammeDTO convertTypePenalitesToDto(StatutProgramme entity) {
		StatutProgrammeDTO entityDto = modelMapper.map(entity, StatutProgrammeDTO.class);
		return entityDto;
	}

	protected ProgrammeEmploiDTO convertProgrammeToProgrammeEmploiDTO(Programme entity){
		ProgrammeEmploiDTO programmeEmploiDTO=modelMapper.map(entity,ProgrammeEmploiDTO.class);

		return  programmeEmploiDTO;
	}
	
	
	protected NoteProfDTO convertNoteToNoteDTO(Note_enseignant note){
		NoteProfDTO noteDTO=modelMapper.map(note,NoteProfDTO.class);

		return  noteDTO;
	}


	// *****************Convert DTO (DATA TRANSFERT OBJECT ) to Entity**********************************
	protected Anneescolaire dtoToAnnee_scolaire(AnneeScolaireDTO Dto) {
		Anneescolaire entity = modelMapper.map(Dto, Anneescolaire.class);
		return entity;
	}
	
	protected Note_enseignant dtoToNote(NoteProfDTO Dto) {
		Note_enseignant entity = modelMapper.map(Dto, Note_enseignant.class);
		return entity;
	}
	 
	protected Eleve dtoToEleve(EleveDTO Dto) {
		Eleve entity = modelMapper.map(Dto, Eleve.class);
		return entity;
	}
	protected Utilisateur dtoToUtilisateur(UserDTO Dto) {
		Utilisateur entity = modelMapper.map(Dto, Utilisateur.class);
		return entity;
	}
	protected Matiere dtoToMatiere(MatiereDTO Dto) {
		Matiere entity = modelMapper.map(Dto, Matiere.class);
		return entity;
	}
	protected Classes dtoToClasses(ClasseRoomDTO Dto) {
		Classes entity = modelMapper.map(Dto, Classes.class);
		return entity;
	}
	
	protected Salle dtoToSalle(SalleDTO Dto) {
		Salle entity = modelMapper.map(Dto, Salle.class);
		return entity;
	}
	protected Jours dtoToJours(JoursDTO Dto) {
		Jours entity = modelMapper.map(Dto, Jours.class);
		return entity;
	}
	protected Heures dtoToHeures(HeuresDTO Dto) {
		Heures entity = modelMapper.map(Dto, Heures.class);
		return entity;
	}
	protected Semaine dtoToSemaine(SemaineDTO Dto) {
		Semaine entity = modelMapper.map(Dto, Semaine.class);
		return entity;
	}
	
	protected Authority dtoToAuthority(AuthorityDTO Dto) {
		Authority entity = modelMapper.map(Dto, Authority.class);
		return entity;
	}
	
	protected Classeleves dtoToClasseDesEleves(ClassesDesEleveDTO Dto) {
		Classeleves entity = modelMapper.map(Dto, Classeleves.class);
		return entity;
	}

	protected Programme dtoToProgramme(ProgrammeDTO Dto) {
		Programme entity = modelMapper.map(Dto, Programme.class);
		return entity;
	}
	protected Programme dtoToEmplois(EmploisDTO Dto) {
		Programme entity = modelMapper.map(Dto, Programme.class);
		return entity;
	}
	
	protected Penalites dtoToPenalites(PenalitesDTO Dto) {
		Penalites entity = modelMapper.map(Dto, Penalites.class);
		return entity;
	}
	protected Etablissement dtoToEtablissement(EtablissementDTO Dto) {
		Etablissement entity = modelMapper.map(Dto, Etablissement.class);
		return entity;
	}

}
