package fr.afpa.interfaces.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;

@Service
public interface IDTOModificationSalle {

	Salle choixSalle(String id);

	List<Salle> listeSalles();

	ArrayList<Batiment> listerBatiment();

	boolean updateSalle(Salle salle);

	boolean activerDesactiverSalle(int parseInt);

	boolean supprimerSalle(int parseInt);

}
