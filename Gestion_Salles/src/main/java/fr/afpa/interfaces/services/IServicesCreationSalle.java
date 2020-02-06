package fr.afpa.interfaces.services;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;
@Service
public interface IServicesCreationSalle {

	boolean ajoutSalleBdd(Salle salle, String batiment, String type);
}
