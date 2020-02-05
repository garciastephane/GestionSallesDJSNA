package fr.afpa.interfaces.dao;

import fr.afpa.entitespersistees.ProfilBDD;

public interface IDAOModification {

	/**
	 * Permet de supprimer un utilisateur de la base de donnees
	 * @param id
	 * @return
	 */
	public boolean suppressionUtilisateurBDD(int id);
	
	/**
	 * Permet d'activer ou de desactiver un utilisateur
	 * @param id
	 * @return
	 */
	public boolean activerDesactiverUtilisateurBDD(int id);
	
	/**
	 * Permet de modifier un utilisateur en base de donnees
	 * @param userBdd
	 * @param id
	 * @param mdp
	 * @return
	 */
	public boolean modifierUtilisateurBDD(ProfilBDD userBdd, int id, String mdp);
	
}
