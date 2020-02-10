package fr.afpa.controles;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Reservation;
import fr.afpa.interfaces.controles.IControleGeneral;

@Service
public class ControleGeneral implements IControleGeneral {
	
	/**
	 * Constructeur prive de la classe ControleGeneral
	 */
	private ControleGeneral() {

	}
	/**
	 * Controle du nom et du prenom saisie
	 * 
	 * @param nom le nom ou le prenom de la personne
	 * @return true si le nom ou prenom est correct, false si non
	 */
	public boolean controleNomPrenom(String nom) {
		if (nom != null)
			return nom.matches("[A-Za-z]+");
		return false;
	}

	/**
	 * Controle de la date saisie
	 * 
	 * @param date la date saisie
	 * @return true si la date est correct, false si non
	 */
	public boolean controleDateDeNaissance(String date) {
		if (date != null)
			return date.matches(
					"(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/([1-9][0-9]{3}|0[1-9][0-9]{2}|00[1-9][0-9]{1}|000[1-9])");
		return false;
	}

	/**
	 * Controle du role
	 * 
	 * @param role le role a verifier
	 * @return true si le role existe, false si non
	 */
	public boolean controleRole(String role) {
		if (role != null) {
			switch (role.toLowerCase()) {
			case "formateur":
			case "stagiaire":
				return true;
			default:
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Permet de verifier si le contenu d'un message depasse la taille
	 * de 255 caracteres
	 * @param contenu : le contenu du message
	 * @return true si le contenu ne depasse pas les 255 caracteres
	 * et false sinon
	 */
	public boolean controleTailleContenuMesage(String contenu) {
		if (contenu != null) {
			return contenu.length() <= 255;
		}
		return false;
	}
	
	/**
	 * Permet de verifier si l'objet d'un message depasse la taille
	 * de 50 caracteres
	 * @param objet : l'objet du message
	 * @return true si l'objet ne depasse pas les 50 caracteres
	 * et false sinon
	 */
	public boolean controleTailleObjetMesage(String objet) {
		if (objet != null) {
			return objet.length() <= 50;
		}
		return false;
	}
	
	
	public boolean controleCollisionDates(List<Reservation> reservations, LocalDate date, int duree) {
		for (Reservation reservation : reservations) {
			if ((date.isAfter(reservation.getDateDebut()) && date.isBefore(reservation.getDateFin()))
					|| (date.plusDays(duree).isAfter(reservation.getDateDebut()) && date.plusDays(duree).isBefore(reservation.getDateFin()))
					|| (reservation.getDateDebut().isAfter(date) && reservation.getDateFin().isBefore(date))
					|| (reservation.getDateDebut().isAfter(date.plusDays(duree)) && reservation.getDateFin().isBefore(date.plusDays(duree)))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean controleDateObsolete(LocalDate date) {
		return date.isAfter(LocalDate.now());
	}
}
