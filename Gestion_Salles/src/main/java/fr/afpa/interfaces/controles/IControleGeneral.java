package fr.afpa.interfaces.controles;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Reservation;

@Service
public interface IControleGeneral {

	public boolean controleNomPrenom(String nom);
	
	public boolean controleDateDeNaissance(String date);
	
	public boolean controleRole(String role);
	
	public boolean controleTailleContenuMesage(String contenu);
	
	public boolean controleTailleObjetMesage(String objet);
	
	public boolean controleCollisionDates(List<Reservation> reservations, LocalDate date, int duree);
	
	public boolean controleDateObsolete(LocalDate date);
	
}
