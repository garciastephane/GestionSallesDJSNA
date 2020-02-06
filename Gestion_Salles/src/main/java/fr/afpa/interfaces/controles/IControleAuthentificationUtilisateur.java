package fr.afpa.interfaces.controles;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Personne;

@Service
public interface IControleAuthentificationUtilisateur {

	public boolean controlePersonneInscrite(String login, String mdp);
	
	public boolean controleDestinataire(String logins);
	
	public Personne getUser(String login, String mdp);
	
	public boolean controleAdmin(String login);
	
}
