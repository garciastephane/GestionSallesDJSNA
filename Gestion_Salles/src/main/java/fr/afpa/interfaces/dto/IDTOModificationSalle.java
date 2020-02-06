package fr.afpa.interfaces.dto;

import java.util.Map;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;

@Service
public interface IDTOModificationSalle {

	boolean choixSalle(int id);

	Map<Integer, Salle> listeSalles();

}
