package fr.afpa.interfaces.dto;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Reservation;
import fr.afpa.entites.Salle;
@Service
public interface IDTOCreationSalle {

	boolean ajoutSalle(Salle salle, String batiment, String type);
	
	boolean ajoutReservation(Reservation reservation, int idSalle);

}
