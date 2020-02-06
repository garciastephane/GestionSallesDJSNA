package fr.afpa.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.dao.DAOLecture;
import fr.afpa.entites.Administrateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entites.Utilisateur;
import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.entitespersistees.RoleBDD;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.services.ServiceGeneral;

@Service
public class DTOGeneral implements IDTOGeneral {

	/**
	 * Permet de transformer une instance de RoleBDD en instance de RolePersonne
	 * 
	 * @param role : le role a transformer
	 * @return le role en instance de RolePersonne
	 */
	public RolePersonne roleBDDToRolePersonne(RoleBDD role) {
		if (RolePersonne.FORMATEUR.getRole().equals(role.getLibelle())) {
			return RolePersonne.FORMATEUR;
		} else if (RolePersonne.STAGIAIRE.getRole().equals(role.getLibelle())) {
			return RolePersonne.STAGIAIRE;
		} else {
			return null;
		}
	}

	/**
	 * Permet de transformer une instance de ProfilBDD en instance d'Administrateur
	 * ou d'Utilisateur
	 * 
	 * @param profilBDD : le profil a transformer
	 * @return une instance qui herite d'une classe fille de la classe Personne
	 */
	public Personne profilBDDToPersonne(ProfilBDD profilBDD) {
		Personne personne;
		if ("Administrateur".equals(profilBDD.getTypeProfil().getLibelle())) {
			personne = new Administrateur();
		} else {
			personne = new Utilisateur();
		}
		personne.setNom(profilBDD.getNom());
		personne.setPrenom(profilBDD.getPrenom());
		personne.setDateNaissance(ServiceGeneral.conversionDate(profilBDD.getDateNaissance()));
		personne.setEmail(profilBDD.getMail());
		personne.setAdresse(profilBDD.getAdresse());
		personne.setActif(profilBDD.isActif());
		personne.setRole(roleBDDToRolePersonne(profilBDD.getRole()));
		return personne;
	}

	
	/**
	 * Permet de transformer une instance de ProfilBDD en instance d'Administrateur
	 * ou d'Utilisateur
	 * 
	 * @param profilBDD : le profil a transformer
	 * @return une instance qui herite d'une classe fille de la classe Personne
	 */
	public ProfilBDD personneToProfilBDD(Personne personne) {
		ProfilBDD profil = new ProfilBDD();
		profil.setAdresse(personne.getAdresse());
		profil.setDateNaissance(ServiceGeneral.conversionDate(personne.getDateNaissance()));
		profil.setMail(personne.getEmail());
		profil.setActif(personne.isActif());
		profil.setNom(personne.getNom());
		profil.setPrenom(personne.getPrenom());
		profil.setRole(ServiceGeneral.conversionRole(personne.getRole()));
		return profil;
	}
	
	/**
	 * Permet de transformer un message de type Message en un message
	 * de type MessageBDD
	 * @param message : le message transformer
	 * @return le message transforme
	 */
	public MessageBDD messageToMessageBDD(Message message) {
		MessageBDD res = new MessageBDD();
		res.setId_message(message.getId());
		res.setObjet(message.getObjet());
		res.setContenu(message.getContenu());
		res.setDate(message.getDate());
		res.setArchive(message.isArchivage());
		return res;
	}
	
	/**
	 * Permet de transformer une liste de logins destinataires en liste de LoginMessageBDD
	 * @param destinataires : les logins qui recevront le futur message
	 * @return les logins transformes
	 */
	public List<LoginMessageBDD> listeLoginsToListeLoginMessageBDD(
			List<String> destinataires, String expediteur) {
		List<LoginMessageBDD> res = new ArrayList<LoginMessageBDD>();
		for (String destinataire : destinataires) {
			LoginMessageBDD loginMessage = new LoginMessageBDD();
			loginMessage.setExpDest(false);
			loginMessage.setLogBdd(new LogBDD(destinataire, ""));
			res.add(loginMessage);
		}
		LoginMessageBDD loginMessage = new LoginMessageBDD();
		loginMessage.setExpDest(true);
		loginMessage.setLogBdd(new LogBDD(expediteur, ""));
		res.add(loginMessage);
		return res;
	}
	
	/**
	 * Permet de transformer une liste de login_message_bdd en liste de message
	 * @param le login
	 * @return liste de Message transformé
	 */
	public List<Message> listeLoginMessageToListeMessage(String login) {
		DAOLecture daol = new DAOLecture();
		List<LoginMessageBDD> lmbdd = daol.getAllMessages(login);
		List<Message> listMessages = new ArrayList<Message>();
		for (LoginMessageBDD loginMessageBdd : lmbdd) {
			Message message = new Message();
			message.setId(loginMessageBdd.getMessageBdd().getId_message());
			message.setExpediteur(loginMessageBdd.getLogBdd().getLogin());
			int nombre = loginMessageBdd.getMessageBdd().getId_message();
			message.setDestinataires(daol.getAllDestinataire(nombre));
			message.setObjet(loginMessageBdd.getMessageBdd().getObjet());
			message.setContenu(loginMessageBdd.getMessageBdd().getContenu());
			message.setDate(loginMessageBdd.getMessageBdd().getDate());
			message.setArchivage(loginMessageBdd.getMessageBdd().isArchive());
			listMessages.add(message);
		}
		
		return listMessages;
	}
	
	public List<Message> listeLoginMessageToListeMessageEnvoye(String login) {
		DAOLecture daol = new DAOLecture();
		List<LoginMessageBDD> lmbdd = daol.getMessageEnvoye(login);
		List<Message> listMessages = new ArrayList<Message>();
		for (LoginMessageBDD loginMessageBdd : lmbdd) {
			Message message = new Message();
			message.setId(loginMessageBdd.getMessageBdd().getId_message());
			message.setExpediteur(loginMessageBdd.getLogBdd().getLogin());
			int nombre = loginMessageBdd.getMessageBdd().getId_message();
			message.setDestinataires(daol.getAllDestinataire(nombre));
			message.setObjet(loginMessageBdd.getMessageBdd().getObjet());
			message.setContenu(loginMessageBdd.getMessageBdd().getContenu());
			message.setDate(loginMessageBdd.getMessageBdd().getDate());
			message.setArchivage(loginMessageBdd.getMessageBdd().isArchive());
			listMessages.add(message);
		}
		
		return listMessages;
	}
	
	
	
	
}
