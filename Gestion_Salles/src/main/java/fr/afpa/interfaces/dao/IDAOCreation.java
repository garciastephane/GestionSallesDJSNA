package fr.afpa.interfaces.dao;

import java.util.List;

import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;

public interface IDAOCreation {
	
	/**
	 * Permet d'enregistrer un utilisateur en base de donnees
	 * @param profil
	 * @param log
	 * @param role
	 * @param personne
	 * @return
	 */
	public boolean enregistrerUtilisateur(ProfilBDD profil, LogBDD log, RolePersonne role, Personne personne);
	
	/**
	 * Permet d'enregistrer un message en base de donnees
	 * @param message
	 * @param listeLogins
	 * @return
	 */
	public boolean enregistrerMessage(MessageBDD message, List<LoginMessageBDD> listeLogins);
	
}
