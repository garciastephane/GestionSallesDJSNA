package fr.afpa.interfaces.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.Reservation;

@Service
public interface IServiceVisualisation {

	public Map<Integer, Personne> listeTousPersonnes();
	
	public String afficherUser();
	
	public List<Message> afficherListeMessage(String login);
	
	public List<Message> afficherListeMessageEnvoyer(String login);
	
	public List<Reservation> listeReservations(int id);
	
}
