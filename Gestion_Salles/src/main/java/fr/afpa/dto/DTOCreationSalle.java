package fr.afpa.dto;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afpa.entites.Salle;
import fr.afpa.entites.TypeSalle;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.interfaces.dto.IDTOCreationSalle;
import fr.afpa.repositories.ISalleRepository;

public class DTOCreationSalle implements IDTOCreationSalle {
	@Autowired
	ISalleRepository iSalleRepository;

	public boolean ajoutSalle(Salle salle) {
		SalleBDD salleBDD = new SalleBDD();
		salleBDD.setCapacite(salle.getCapacite());
		salleBDD.setId(salle.getId());
		salleBDD.setNom(salle.getNom());
		salleBDD.setNumero(salle.getNumero());
		salleBDD.setSurface(salle.getSurface());
		iSalleRepository.save(salleBDD);

		return true;
	}

	

}
