package fr.afpa.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.afpa.dto.DTOGeneral;
import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.Utilisateur;

public class ServiceVisualisation {

	/**
	 * Permet de retourner la liste des utilisateurs sous la forme d'une chaine de
	 * caracteres avec les balises html pour un affichage dans un servlet
	 * 
	 * @return une chaine de caracteres
	 */
	public String afficher() {
		DTOUtilisateur dtou = new DTOUtilisateur();
		Map<Integer, Personne> listePersonnes = dtou.listePersonnes();
		String res = "";
		for (Entry<Integer, Personne> couplePersonne : listePersonnes.entrySet()) {
			Personne personne = couplePersonne.getValue();
			res += "<tr>";
			res += "<td>" + personne.getNom() + "</td>";
			res += "<td>" + personne.getPrenom() + "</td>";
			res += "<td>" + personne.getDateNaissance() + "</td>";
			res += "<td>" + personne.getEmail() + "</td>";
			res += "<td>" + personne.getAdresse() + "</td>";
			res += "<td>" + personne.getRole().getRole() + "</td>";
			res += "</tr>";
		}
		return res;
	}

	/**
	 * Permet de retourner la liste de toutes les personnes
	 * 
	 * @return la liste de toutes les personnes
	 */
	public Map<Integer, Personne> listeTousPersonnes() {
		DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.listePersonnes();
	}


	/**
	 * Permet de retourner la liste des utilisateurs sous la forme d'une chaine de
	 * caracteres avec les balises html pour un affichage dans un servlet
	 * 
	 * @return une chaine de caracteres
	 */
	public String afficherUser() {
		DTOUtilisateur dtou = new DTOUtilisateur();
		Map<Integer, Personne> listePersonnes = dtou.listePersonnes();
		String res = "";
		for (Entry<Integer, Personne> couplePersonne : listePersonnes.entrySet()) {
			Personne personne = couplePersonne.getValue();
			if (personne instanceof Utilisateur) {
				res += "<tr>";
				res += "<td>" + couplePersonne.getKey() + "</td>";
				res += "<td>" + personne.getNom() + "</td>";
				res += "<td>" + personne.getPrenom() + "</td>";
				res += "<td>" + personne.getDateNaissance() + "</td>";
				res += "<td>" + personne.getEmail() + "</td>";
				res += "<td>" + personne.getAdresse() + "</td>";
				res += "<td>" + personne.getRole().getRole() + "</td>";
				res += "</tr>";
			}
		}
		return res;
	}
	
	public List<Message> afficherListeMessage(String login){
		return DTOGeneral.listeLoginMessageToListeMessage(login);
	}
	
	
	public List<Message> afficherListeMessageEnvoyer(String login){
		return DTOGeneral.listeLoginMessageToListeMessageEnvoye(login);
	}
}
