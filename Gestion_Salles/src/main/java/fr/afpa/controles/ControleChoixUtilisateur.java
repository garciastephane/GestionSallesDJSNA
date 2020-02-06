package fr.afpa.controles;

import org.springframework.stereotype.Service;

import fr.afpa.interfaces.controles.IControleChoixUtilisateur;

@Service
public class ControleChoixUtilisateur implements IControleChoixUtilisateur {
	/**
	 * Constructeur prive de la classe ControleChoixUtilisateur
	 */
	private ControleChoixUtilisateur() {
		
	}
	
	/**
	 * Permet de verifier le choix
	 * 
	 * @param choix le choix effectuer
	 * @return true si le choix est correct, false si non
	 */
	public boolean verificationChoix(String choix) {
		if (choix != null && choix.matches("\\d+")) {
				return true;
		}
		return false;
	}

}
