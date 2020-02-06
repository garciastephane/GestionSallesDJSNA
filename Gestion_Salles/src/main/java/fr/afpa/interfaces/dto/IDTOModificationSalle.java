package fr.afpa.interfaces.dto;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;

@Service
public interface IDTOModificationSalle {

	Salle choixSalle(String id);

	Map<Integer, Salle> listeSalles();

	ArrayList<Batiment> listerBatiment();

	boolean updateSalle(Salle salle);

	boolean activerDesactiverSalle(int parseInt);

	boolean supprimerSalle(int parseInt);

}
