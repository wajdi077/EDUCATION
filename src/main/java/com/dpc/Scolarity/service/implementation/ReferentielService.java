
package com.dpc.Scolarity.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dpc.Scolarity.Domain.Authority;
import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Domain.Eleve;
import com.dpc.Scolarity.Domain.Heures;
import com.dpc.Scolarity.Domain.Jours;
import com.dpc.Scolarity.Domain.Matiere;
import com.dpc.Scolarity.Domain.Niveau;
import com.dpc.Scolarity.Domain.Niveau_Matiere;
import com.dpc.Scolarity.Domain.Salle;
import com.dpc.Scolarity.Domain.Semaine;
import com.dpc.Scolarity.Domain.Utilisateur;
import com.dpc.Scolarity.Dto.HeuresDTO;
import com.dpc.Scolarity.Dto.JoursDTO;
import com.dpc.Scolarity.Dto.SemaineDTO;
import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.EleveRepository;
import com.dpc.Scolarity.Repository.HeuresRepository;
import com.dpc.Scolarity.Repository.IAuthority;
import com.dpc.Scolarity.Repository.IUtilisateur;
import com.dpc.Scolarity.Repository.JoursRepository;
import com.dpc.Scolarity.Repository.MatierRepository;
import com.dpc.Scolarity.Repository.NiveauMatiereRepository;
import com.dpc.Scolarity.Repository.NiveauRepository;
import com.dpc.Scolarity.Repository.SalleRepository;
import org.springframework.stereotype.Service;
import com.dpc.Scolarity.Repository.SemaineRepository;
@Service
public class ReferentielService  extends MainController{
	
	@Autowired
	ClassesRepository classesrepository ;
	@Autowired
	EleveRepository eleverepository ;
	@Autowired
	HeuresRepository heuresrepository ;
	@Autowired
	JoursRepository joursrepository ;
	@Autowired
	MatierRepository matiererepository ;
	@Autowired
	SalleRepository sallerepository ;
	@Autowired
	SemaineRepository semainerepository ;
	@Autowired
	ClassesRepository classerepo;
	@Autowired
	IUtilisateur utilisateurRepository ;
	@Autowired
	IAuthority authority;
	@Autowired 
	NiveauRepository niveauRepository;
	@Autowired
	NiveauMatiereRepository niveauMatiereRepository;
	
	
	
	
	public JoursDTO saveJours(String jour) {
		JoursDTO joursDTO  = new JoursDTO();
		joursDTO.setJour(jour);
		Jours j = dtoToJours(joursDTO) ;
		Jours Jo = this.joursrepository.save(j);
        JoursDTO jourdt = convertJoursToDto(Jo);

        	return jourdt;
	}
	 public void addSalle(String nomsalle,String type,String remarque) {
		 
		 Salle salle = new Salle();
		 salle.setNomSalle(nomsalle);
		 salle.setRemarque(remarque);
		 salle.setType(type);
		 this.sallerepository.save(salle);
				 
	 }
	 public void addclasse(String nomclasse,String niveau,String remarque) {
		Classes classe = new Classes();
		classe.setNiveau(niveau);
		classe.setNomclasse(nomclasse);
		classe.setRemarque(remarque);
		 this.classerepo.save(classe);
		 
		 
		 
	 }
	
	public SemaineDTO saveSemaine(String semaineName) {
		SemaineDTO semaineDTO = new SemaineDTO();
		semaineDTO.setSemaine(semaineName);
		Semaine j = dtoToSemaine(semaineDTO) ;
		Semaine Jo = this.semainerepository.save(j);
		SemaineDTO semainedt = convertSemaineToDto(Jo);

			return semainedt;
	}
	
	public HeuresDTO saveHeures(String heureDebut , String HeuresFin)
	{
		HeuresDTO heuredto = new HeuresDTO();
	
		Heures h = dtoToHeures(heuredto);
		Heures hs = this.heuresrepository.save(h);
		HeuresDTO  heuresdto = convertHeuresToDto(hs);
			return heuresdto;
		
	}
	public void addeleve(String nom, String prenom, String etat, String sexe, Date datenaissonce, String classeactulle,
			String niveau, String remarque, String situation,String numinsc) {
		// TODO Auto-generated method stub
		
		Eleve eleve= new Eleve();
		eleve.setEtat(etat);
		eleve.setClasseActuel(classeactulle);
		eleve.setDateNaissance(datenaissonce);
		eleve.setNiveauEtude(niveau);	
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		eleve.setRemarque(remarque);
		eleve.setSituationFamiliale(situation);
		eleve.setSexe(sexe);
		eleve.setNumInscription(numinsc);
		eleve.setDateInscription(new Date(System.currentTimeMillis()));
		String a = eleve.getSexe().substring(0,1); 
		Eleve e = this.eleverepository.save(eleve);
		Long id = e.getId();
		String ids = id.toString();
		e.setDateInscription(new Date(System.currentTimeMillis()));
		//e.setNumInscription(a+ids);
		 this.eleverepository.save(e);
	}
	
	/* fonction d'ajout  d'un suveillant
	 * developed by : sara naifar
	 * date: 18/07/2019
	 */
	public Utilisateur addSurveillant(String nom, String prenom, String email, String username, String password, String profil, String login) {
		// TODO Auto-generated method stub
		Utilisateur userConnecte = this.utilisateurRepository.findByUsername(login);
		System.out.println("********** CEST LE ROLE DU SURVEILLANT**********");	
		String survProfil = profil.substring(0,1).toUpperCase() + profil.substring(1).toLowerCase();
		Authority role =    this.authority.findByDescription(survProfil);
		Utilisateur connectedUser = new Utilisateur ();
		Utilisateur surveillant = new Utilisateur();
		surveillant.setNom(nom);
		surveillant.setPrenom(prenom);
		surveillant.setEmail(email);
		surveillant.setUsername(username);
		surveillant.setPassword(password);
		surveillant.setProfil(profil);
		surveillant.setEtablissement(userConnecte.getEtablissement());
		surveillant.setAuthorities(role);
		surveillant.setArchiver(false);
		surveillant.setEnabled(true);
	
		
	
		return  this.utilisateurRepository.save(surveillant);
	}
	
	
	
	/* fonction de mis à jour des classes et niveaux  des eleves existant 
	 * developed by : sara naifar
	 * date: 06/08/2019
	 */
	public void affectationEleve(String niveau, String classeactulle,String ancien_classe, String numinsc )
	
{
		// TODO Auto-generated method stub
	
		Eleve eleve= this.eleverepository.findByNumInscription(numinsc);
		
		if (eleve != null) {
			eleve.setNiveauEtude(niveau);
			eleve.setClasseActuel(classeactulle);
			eleve.setAncien_classe(ancien_classe);
				
			 this.eleverepository.save(eleve);
			
		}
		
	}
	
	/* fonction de ajout de nombre d'heur par niveau par matiere 
	 * developed by : sara naifar
	 * date: 30/08/2019
	 */
	public void ajoutNombreHeurParMatiereParNiveau(ArrayList<String> matieres, ArrayList nombreHeur, ArrayList<String> niveauxEtude )
	
	{
		Niveau_Matiere niveauMatiere ;
		// TODO Auto-generated method stub
		for(int i=0;i<nombreHeur.size();i++) {
			List l = new ArrayList<>();
				l=	(List) nombreHeur.get(i);
			
			for(int m=0; m<l.size();m++) {
				niveauMatiere =new Niveau_Matiere();
					 Matiere matiere = this.matiererepository.findByNommatiereFR(matieres.get(i));
					 Double nbreHeur=(Double) l.get(m);
							Niveau niveau = this.niveauRepository.findBynomniveau(niveauxEtude.get(m));
							niveauMatiere.setNiveau(niveau);
							niveauMatiere.setNbheures(nbreHeur);
							niveauMatiere.setMatiere(matiere);
						
							System.out.print("----");
							System.out.print(niveauMatiere);
							System.out.print(niveauMatiere.getMatiere().getNommatiereFR());
							this.niveauMatiereRepository.save(niveauMatiere);
			
			
		}
			
		}
		
	}
	
	
}
