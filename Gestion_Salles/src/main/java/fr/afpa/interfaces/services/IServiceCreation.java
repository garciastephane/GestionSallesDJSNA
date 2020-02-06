package fr.afpa.interfaces.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;

@Service
public interface IServiceCreation {

	public RolePersonne conversionRole(String role);
	
	public Personne creationPersonne(String nom, String prenom, LocalDate dateNaissance, String mail, String adresse,
			boolean actif, RolePersonne role, String login, String mdp, boolean admin);
	
	public boolean creationMessage(String expediteur, String destinataires, String objet
			, String contenu, LocalDateTime date);
	
}
