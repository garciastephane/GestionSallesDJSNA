package fr.afpa.interfaces.services;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Personne;

@Service
public interface IServiceModification {

	public boolean supprimerUtilisateur(int id);
	
	public boolean activerDesactiverUtilisateur(int id);
	
	public boolean modifierUtilisateur(Personne user, int id, String mdp);
	
	public boolean archiverMsg(int id);
	
}
