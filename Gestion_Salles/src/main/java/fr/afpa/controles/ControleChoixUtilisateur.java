package fr.afpa.controles;

public class ControleChoixUtilisateur {
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
	public static boolean verificationChoix(String choix) {
		if (choix != null && choix.matches("\\d+")) {
				return true;
		}
		return false;
	}

}
