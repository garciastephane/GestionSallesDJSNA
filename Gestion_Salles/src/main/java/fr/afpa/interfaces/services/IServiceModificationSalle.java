package fr.afpa.interfaces.services;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;

@Service
public interface IServiceModificationSalle {

	String voirSalle();

	Salle getSalle(String id);

	Object listerBatiment();

	boolean updateSalle(Salle salle, int i, int j, int k);

	boolean supprimerSalle(int parseInt);

	String voirMateriel(int id);

	String listeSalle2();

	Salle getSalle2(String id);

}
