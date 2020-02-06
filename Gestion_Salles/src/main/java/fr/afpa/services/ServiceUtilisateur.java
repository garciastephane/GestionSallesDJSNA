package fr.afpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Personne;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOUtilisateurs;
import fr.afpa.interfaces.services.IServiceUtilisateur;

@Service
public class ServiceUtilisateur implements IServiceUtilisateur {
	
	@Autowired
	private IDTOUtilisateurs dtoUtilisateurs;
	
	public Personne utilisateur(String login, String mdp) {
		return dtoUtilisateurs.user(login, mdp);
	}
}
