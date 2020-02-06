package fr.afpa.interfaces.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Personne;

@Service
public interface IServiceVisualisation {

	public Map<Integer, Personne> listeTousPersonnes();
	
}
