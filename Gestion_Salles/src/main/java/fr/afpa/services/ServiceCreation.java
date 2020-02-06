package fr.afpa.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afpa.controles.ControleAuthentificationUtilisateur;
import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Administrateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entites.Utilisateur;
import fr.afpa.interfaces.controles.IControleGeneral;
import fr.afpa.interfaces.services.IServiceCreation;
import fr.afpa.interfaces.services.IServiceGeneral;

public class ServiceCreation implements IServiceCreation {
	
	@Autowired
	private IControleGeneral controleGeneral;
	@Autowired
	private IServiceGeneral serviceGeneral;
	
	/**
	 * Permet de convertir un role de type String en role de type RolePersonne
	 * 
	 * @param role le role
	 * @return le role
	 */
	public RolePersonne conversionRole(String role) {
		switch (role.toLowerCase()) {
		case "formateur":
			return RolePersonne.FORMATEUR;
		case "stagiaire":
			return RolePersonne.STAGIAIRE;
		default:
			return null;
		}
	}

	/**
	 * Permet de créer une personne
	 * 
	 * @param nom           le nom de la personne
	 * @param prenom        le prenom de la personne
	 * @param dateNaissance la date de naissance de la personne
	 * @param mail          le mail de la personne
	 * @param adresse       l'adresse de la personne
	 * @param role          le role de la personne
	 * @param login         le login de la personne
	 * @param mdp           le mot de passe de la personne
	 * @param admin         le type de personne (true = admin, false = utilisateur)
	 * @return une nouvelle personne
	 */
	public Personne creationPersonne(String nom, String prenom, LocalDate dateNaissance, String mail, String adresse,
			boolean actif, RolePersonne role, String login, String mdp, boolean admin) {
		if (mdp != null) {
			Personne p;
			DTOUtilisateur dtou = new DTOUtilisateur();
			if (admin) {
				p = new Administrateur(nom, prenom, dateNaissance, mail, adresse, actif, role);
			} else {
				p = new Utilisateur(nom, prenom, dateNaissance, mail, adresse, actif, role);
			}
			dtou.ajoutBDD(p, login, mdp, role);
			return p;
		}
			return null;
	}
	
	/**
	 * Permet de creer un message
	 * @param expediteur : l'expediteur du message
	 * @param destinataires : les destinataires du message
	 * @param objet : l'objet du message
	 * @param contenu : le contenu du message
	 * @param date : la date de creation du message
	 * @return true si le message a ete cree et false sinon
	 */
	public boolean creationMessage(String expediteur, String destinataires, String objet
			, String contenu, LocalDateTime date) {
		ControleAuthentificationUtilisateur cau = new ControleAuthentificationUtilisateur();
		if (cau.controleDestinataire(destinataires)
				&& controleGeneral.controleTailleContenuMesage(contenu)
				&& controleGeneral.controleTailleObjetMesage(objet)) {
			Message message = new Message(expediteur, serviceGeneral.conversionStringEnListe(destinataires)
					, objet, contenu, date, false);
			return new DTOUtilisateur().ajoutMessageBDD(message);
		}
		return false;
	}
}
