package fr.afpa.interfaces.services;

import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Personne;

@Service
@ComponentScan("fr.afpa.interfaces.services")
public interface IServiceVisualisation {

	public Map<Integer, Personne> listeTousPersonnes();
	
}
