package fr.afpa.services;

import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Personne;

public class ServiceUtilisateur {
	
	public Personne utilisateur(String login, String mdp) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.user(login, mdp);
	}
}
