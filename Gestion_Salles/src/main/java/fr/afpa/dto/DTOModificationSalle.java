package fr.afpa.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Salle;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOModificationSalle;
import fr.afpa.repositories.ISalleRepository;

@Service
public class DTOModificationSalle implements IDTOModificationSalle {
	@Autowired
	private ISalleRepository modificationSalleRepository;
	@Autowired
	private IDTOGeneral dtoGeneral;
	
	@Override
	public boolean choixSalle(int id) {
		return false;
		
	}

	@Override
	public Map<Integer, Salle> listeSalles() {
		Map<Integer, Salle> listePersonnes = new HashMap();
		List<SalleBDD> listeSalles = modificationSalleRepository.findAll();
		for (SalleBDD salleBDD : listeSalles) {
			listePersonnes.put(salleBDD.getId(), dtoGeneral.salleBDDToSalle(salleBDD));
		}
		return listePersonnes;
	}
}
