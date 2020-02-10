package fr.afpa.interfaces.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;

@Service
public interface IDTOModificationSalle {

	Salle choixSalle(String id);

	List<Salle> listeSalles();

	ArrayList<Batiment> listerBatiment();

	boolean updateSalle(Salle salle, int retro, int ordi, int reseau);

	boolean supprimerSalle(int parseInt);

	Map<String, Integer> voirMateriel(int id);
	
	public List<Salle> voirSalles();

	Salle choixSalleComplete(String id);

}
