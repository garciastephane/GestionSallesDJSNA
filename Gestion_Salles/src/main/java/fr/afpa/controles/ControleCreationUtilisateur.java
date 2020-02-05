package fr.afpa.controles;

import java.util.List;

import fr.afpa.dto.DTOUtilisateur;

public class ControleCreationUtilisateur {

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
	public static boolean controleLogin(String parameter) {
		DTOUtilisateur dtou = new DTOUtilisateur();
		List<String> listeLogs = dtou.listeLog();
		return !listeLogs.contains(parameter);
	}

}
