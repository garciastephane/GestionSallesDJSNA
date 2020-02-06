package fr.afpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;
import fr.afpa.interfaces.dto.IDTOCreationSalle;
import fr.afpa.interfaces.services.IServicesCreationSalle;

@Service

public class ServiceCreationSalle implements IServicesCreationSalle {
	
	@Autowired
	
	private IDTOCreationSalle dtoCreationSalle;
	
	@Override
	
	public boolean ajoutSalleBdd(Salle salle,String batiment, String type) {
		return dtoCreationSalle.ajoutSalle(salle,batiment,type);
	}

}
