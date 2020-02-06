package fr.afpa.dto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;
import fr.afpa.entitespersistees.BatimentBDD;
import fr.afpa.entitespersistees.CentreBDD;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.entitespersistees.TypeSalleBDD;
import fr.afpa.interfaces.dto.IDTOCreationSalle;
import fr.afpa.repositories.IBatimentRepository;
import fr.afpa.repositories.ICentreRepository;
import fr.afpa.repositories.ISalleRepository;
import fr.afpa.repositories.ITypeSalleRepository;

@Service

public class DTOCreationSalle implements IDTOCreationSalle {
	
	@Autowired
	
	ITypeSalleRepository typeSalleRepository;
	@Autowired

	ISalleRepository salleRepository;
	@Autowired
	
	ICentreRepository centreRepository;
	@Autowired
	
	IBatimentRepository batimentRepository;

	@Override
	
	public boolean ajoutSalle(Salle salle, String batiment, String type) {
		SalleBDD salleBDD = new SalleBDD();
		
		salleBDD.setBatiment(batimentRepository.findByNom(batiment).get(0));
		salleBDD.setCapacite(salle.getCapacite());
		salleBDD.setId(salle.getId());
		salleBDD.setNom(salle.getNom());
		salleBDD.setNumero(salle.getNumero());
		salleBDD.setSurface(salle.getSurface());
		salleBDD.setTypeSalle(typeSalleRepository.findByType(type).get(0));
		salleRepository.save(salleBDD);

		return true;
	}

}
