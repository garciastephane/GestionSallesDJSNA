package fr.afpa.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Administrateur;
import fr.afpa.entites.Personne;
import fr.afpa.interfaces.controles.IControleAuthentificationUtilisateur;
import fr.afpa.interfaces.controles.IControleCreationUtilisateur;

@Service
public class ControleAuthentificationUtilisateur implements IControleAuthentificationUtilisateur {

	@Autowired
	private IControleCreationUtilisateur controleCreationUtilisateur;
	
	/**
	 * Permet de verifier si une personne est inscrite sur le site
	 * 
	 * @param login : le login a verifier
	 * @param mdp   : le mot de passe a verifier
	 * @return true si la personne est inscrite et false sinon
	 */
	public boolean controlePersonneInscrite(String login, String mdp) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.authentificationReussie(login, mdp);
	}

	/**
	 * Permet de verifier si tous les logins passes en parametre existes
	 * @param logins : une chaine de caracteres avec la liste de tous les
	 * destibataires d'un message
	 * @return true si tous les logins existent et false si l'un d'entre eux n'existe pas
	 */
	public boolean controleDestinataire(String logins) {
		String[] tabLogins = logins.split(";");
		for (String login : tabLogins) {
			if (controleCreationUtilisateur.controleLogin(login)) {
				return false;
			}
		}
		return true;
	}
	
	public Personne getUser(String login, String mdp) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		Personne utilisateur = dtou.user(login, mdp);
		return utilisateur;
	}
	
	/**
	 * Permet de verifier a partir d'un login si une personne est un admin ou non
	 * @param login : le login a verifier
	 * @return true si c'est un admin et false sinon
	 */
	public boolean controleAdmin(String login) {
		return new DTOUtilisateur().personneDuLogin(login) instanceof Administrateur;
	}
}
