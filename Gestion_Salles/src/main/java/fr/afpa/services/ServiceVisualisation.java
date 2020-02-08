package fr.afpa.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.Reservation;
import fr.afpa.entites.Utilisateur;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOUtilisateurs;
import fr.afpa.interfaces.dto.IDTOVisualisation;
import fr.afpa.interfaces.services.IServiceVisualisation;

@Service
@Primary
public class ServiceVisualisation implements IServiceVisualisation {

	@Autowired
	private IDTOGeneral dtoGeneral;
	@Autowired
	private IDTOUtilisateurs dtoUtilisateur;
	@Autowired
	private IDTOVisualisation dtoVisualisation;
	
	/**
	 * Permet de retourner la liste des utilisateurs sous la forme d'une chaine de
	 * caracteres avec les balises html pour un affichage dans un servlet
	 * 
	 * @return une chaine de caracteres
	 */
	public String afficher() {
		Map<Integer, Personne> listePersonnes = dtoUtilisateur.listePersonnes();
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
		/*DTOUtilisateur dtou = new DTOUtilisateur();
		return dtou.listePersonnes();*/
		return dtoUtilisateur.listePersonnes();
	}


	/**
	 * Permet de retourner la liste des utilisateurs sous la forme d'une chaine de
	 * caracteres avec les balises html pour un affichage dans un servlet
	 * 
	 * @return une chaine de caracteres
	 */
	public String afficherUser() {
		Map<Integer, Personne> listePersonnes = dtoUtilisateur.listePersonnes();
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
		return dtoGeneral.listeLoginMessageToListeMessage(login);
	}
	
	
	public List<Message> afficherListeMessageEnvoyer(String login){
		return dtoGeneral.listeLoginMessageToListeMessageEnvoye(login);
	}
	
	public List<Reservation> listeReservations(int id) {
		return dtoVisualisation.listeReservationSalle(id);
	}
}
