package fr.afpa.interfaces.controles;

import org.springframework.stereotype.Service;

@Service
public interface IControleChoixUtilisateur {

	public boolean verificationChoix(String choix);
	
}
