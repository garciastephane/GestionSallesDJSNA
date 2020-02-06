package fr.afpa.interfaces.services;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Personne;

@Service
public interface IServiceUtilisateur {

	public Personne utilisateur(String login, String mdp);
	
}
