package fr.afpa.interfaces.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;

@Service
public interface IServiceModificationSalle {

	String voirSalle();

	Salle getSalle(String id);

	Object listerBatiment();

	boolean updateSalle(Salle salle);

	boolean supprimerSalle(int parseInt);

	Map<String, Integer> voirMateriel(int id);

	String listeSalle2();

}
