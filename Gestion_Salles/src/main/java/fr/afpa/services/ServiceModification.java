package fr.afpa.services;

import org.springframework.stereotype.Service;

import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.interfaces.services.IServiceModification;

@Service
public class ServiceModification implements IServiceModification {

	public static final String CHOIX = "choix";

	/**
	 * Permet de supprimer un utilisateur en passant par le DTO
	 * 
	 * @param id l'id de l'utilisateur a supprimer
	 * @return true si l'utilisateur est supprimer, false si non
	 */
	public boolean supprimerUtilisateur(int id) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.suppressionBDD(id);
	}

	/**
	 * Permet d'activer ou de desactiver un utilisateur en passant par le DTO
	 * 
	 * @param id l'id de l'utilisateur a activer ou a desactiver
	 * @return true si l'opération a ete effectuee
	 */
	public boolean activerDesactiverUtilisateur(int id) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.activerDesactiverBDD(id);
	}
	

	/**
	 * Permet de modifier un utilisateur en passant par le DTO
	 * 
	 * @param user l'utilisateur a modifier
	 * @param id   l'id de l'utilisateur
	 * @param mdp  le nouveau mot de passe
	 * @return true si l'utilisateur est modifier, false si non
	 */
	public boolean modifierUtilisateur(Personne user, int id, String mdp) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.modifierBDD(user, id, mdp);
	}

	public boolean archiverMsg(int id) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.archivage(id);
		
	}
}
