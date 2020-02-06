package fr.afpa.interfaces.controles;

import org.springframework.stereotype.Service;

@Service
public interface IControleGeneral {

	public boolean controleNomPrenom(String nom);
	
	public boolean controleDateDeNaissance(String date);
	
	public boolean controleRole(String role);
	
	public boolean controleTailleContenuMesage(String contenu);
	
	public boolean controleTailleObjetMesage(String objet);
	
}
