package fr.afpa.interfaces.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;
@Service
public interface IServicesCreationSalle {

	boolean ajoutSalleBdd(Salle salle, String batiment, String type);
	
	boolean creationReservation(String intitule, LocalDate dateDebut, int duree, int idSalle);
}
