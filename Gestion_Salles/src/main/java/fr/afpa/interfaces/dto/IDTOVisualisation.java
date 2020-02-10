package fr.afpa.interfaces.dto;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Reservation;

@Service
public interface IDTOVisualisation {

	List<Reservation> listeReservationSalle(int id);
	
}
