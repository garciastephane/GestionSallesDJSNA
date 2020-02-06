package fr.afpa.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.interfaces.controles.IControleCreationUtilisateur;
import fr.afpa.interfaces.dto.IDTOUtilisateurs;

@Service
public class ControleCreationUtilisateur implements IControleCreationUtilisateur {

	@Autowired
	private IDTOUtilisateurs dtoUtilisateur;
	
	/**
	 * Constructeur prive de la classe ControleCreationUtilisateur
	 */
	private ControleCreationUtilisateur() {

	}

	/**
	 * Permet de controler si le login existe
	 * 
	 * @param parameter le login a verifier
	 * @return true si le login n'existe pas, false si non
	 */
	public boolean controleLogin(String parameter) {
		return !dtoUtilisateur.listeLog().contains(parameter);
	}

}
