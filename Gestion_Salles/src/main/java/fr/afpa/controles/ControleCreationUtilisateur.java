package fr.afpa.controles;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.interfaces.controles.IControleCreationUtilisateur;

@Service
public class ControleCreationUtilisateur implements IControleCreationUtilisateur {

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
		DTOUtilisateur dtou = new DTOUtilisateur();
		List<String> listeLogs = dtou.listeLog();
		return !listeLogs.contains(parameter);
	}

}
