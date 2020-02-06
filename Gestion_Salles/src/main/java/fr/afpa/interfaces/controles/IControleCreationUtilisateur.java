package fr.afpa.interfaces.controles;

import org.springframework.stereotype.Service;

@Service
public interface IControleCreationUtilisateur {

	public boolean controleLogin(String parameter);
	
}
