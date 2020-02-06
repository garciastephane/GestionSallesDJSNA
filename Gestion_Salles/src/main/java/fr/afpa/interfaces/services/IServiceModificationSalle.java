package fr.afpa.interfaces.services;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;

@Service
public interface IServiceModificationSalle {

	String voirSalle();

	Salle getSalle(String id);

	Object listerBatiment();

}
