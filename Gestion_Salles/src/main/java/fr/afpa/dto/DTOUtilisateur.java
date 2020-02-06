package fr.afpa.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.dao.DAOCreation;
import fr.afpa.dao.DAOModification;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOUtilisateurs;
import fr.afpa.interfaces.services.IServiceGeneral;
import fr.afpa.repositories.ILogRepository;
import fr.afpa.repositories.IMessageRepository;
import fr.afpa.repositories.IProfilRepository;

@Service
public class DTOUtilisateur implements IDTOUtilisateurs {
	
	@Autowired
	private IProfilRepository profilRepository;
	@Autowired
	private ILogRepository loginRepository;
	@Autowired
	private IMessageRepository messageRepository;
	
	@Autowired
	private IDTOGeneral dtoGeneral;
	@Autowired
	private IServiceGeneral serviceGeneral;
	
	
	
	/**
	 * Permet de recuperer la liste des personnes
	 * 
	 * @return Permet de retourner la liste des personnes avec leur id
	 */
	public Map<Integer, Personne> listePersonnes() {
		Map<Integer, Personne> listePersonnes = new HashMap<Integer, Personne>();
		for (ProfilBDD profilBDD : profilRepository.findAll()) {
			listePersonnes.put(profilBDD.getId_profil(), dtoGeneral.profilBDDToPersonne(profilBDD));
		}
		return listePersonnes;
	}

	/**
	 * Verifie si le login et le mot de passe correspondent a un profil dans la base
	 * de donnees
	 * 
	 * @param login : le login a verifier
	 * @param mdp   : le mot de passe a verifier
	 * @return true si la personne est dans la base de donnees et false sinon
	 */
	public boolean authentificationReussie(String login, String mdp) {
		List<LogBDD> loginMDP = loginRepository.findByLoginAndMotdepasse(login, mdp);
		return !loginMDP.isEmpty();
	}
	
	public Personne user(String login, String mdp) {
		ProfilBDD profilBdd = profilRepository.findByLoginMdp(loginRepository.findByLoginAndMotdepasse(login, mdp).get(0));
		return dtoGeneral.profilBDDToPersonne(profilBdd);
	}


	/**
	 * Permet de recuperer la liste des logins
	 * 
	 * @return la liste des logins
	 */
	public List<String> listeLog() {
		List<String> liste = new ArrayList<String>();
		for (LogBDD log : loginRepository.findAll()) {
			liste.add(log.getLogin());
		}
		return liste;
	}

	/**
	 * Permet d'ajouter une personne a la base de donnee
	 * 
	 * @param personne la personne a ajouter
	 * @param login    le login de la personne
	 * @param mdp      le mot de passe de la personne
	 * @param role     le role de la personne
	 * @return true si la personne a ete ajouter, false si non
	 */
	public boolean ajoutBDD(Personne personne, String login, String mdp, RolePersonne role) {
		ProfilBDD profil = dtoGeneral.personneToProfilBDD(personne);
		LogBDD log = new LogBDD(login, mdp);
		profil.setLoginMdp(log);
		log.setProfil(profil);
		return new DAOCreation().enregistrerUtilisateur(profil, log, role, personne);
	}

	/**
	 * Permet de supprimer un utilisateur dans la base de donnee
	 * 
	 * @param id l'id de l'utilisateur
	 * @return true si la suppression a ete faite, false si non
	 */
	public boolean suppressionBDD(int id) {
		DAOModification daom = new DAOModification();
		return daom.suppressionUtilisateurBDD(id);
	}

	/**
	 * Permet de desactiver un utilisateur dans la base de donnee s'il est 
	 * actif et de l'activer s'il est desactive
	 * 
	 * @param id l'id de l'utilisateur
	 * @return true si l'operation a ete effectuee
	 */
	public boolean activerDesactiverBDD(int id) {
		DAOModification daom = new DAOModification();
		return daom.activerDesactiverUtilisateurBDD(id);
	}

	/**
	 * Permet de modifier un utilisateur dans la base de donnee
	 * 
	 * @param user l'utilisateur modifier a ajouter
	 * @param id   l'id de l'utilisateur
	 * @param mdp  le nouveau mot de passe
	 * @return true si l'utilisateur a ete modifier, false si non
	 */
	public boolean modifierBDD(Personne user, int id, String mdp) {
		DAOModification daom = new DAOModification();
		ProfilBDD userBDD = new ProfilBDD();
		userBDD.setNom(user.getNom());
		userBDD.setPrenom(user.getPrenom());
		userBDD.setAdresse(user.getAdresse());
		userBDD.setMail(user.getEmail());
		userBDD.setDateNaissance(serviceGeneral.conversionDate(user.getDateNaissance()));
		return daom.modifierUtilisateurBDD(userBDD, id, mdp);
	}
	
	/**
	 * Permet d'ajouter un message a la base de donnees
	 * @param message le message a ajouter
	 * @return true si le message a ete ajoute et false sinon
	 */
	public boolean ajoutMessageBDD(Message message) {
		MessageBDD messageBDD = dtoGeneral.messageToMessageBDD(message);
		List<LoginMessageBDD> listeLogins = 
				dtoGeneral.listeLoginsToListeLoginMessageBDD(message.getDestinataires(), message.getExpediteur());
		return new DAOCreation().enregistrerMessage(messageBDD, listeLogins);
	}
	
	public Personne personneDuLogin(String login) {
		return dtoGeneral.profilBDDToPersonne(profilRepository.findByLoginMdp(loginRepository.findById(login)));
	}

	public boolean archivage(int id) {
		DAOModification dm = new DAOModification();
		return dm.archivageDAO(id);
		
		/*Optional<MessageBDD> message = messageRepository.findById(id);
		if (message.isPresent()) {
			message.get().setArchive(true);
			messageRepository.save(message.get());
			return true;
		}
		return false;*/
	}
}
