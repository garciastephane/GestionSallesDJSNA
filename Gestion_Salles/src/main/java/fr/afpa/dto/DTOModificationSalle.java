package fr.afpa.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;
import fr.afpa.entitespersistees.BatimentBDD;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOModificationSalle;
import fr.afpa.repositories.IBatimentRepository;
import fr.afpa.repositories.ISalleRepository;

@Service
public class DTOModificationSalle implements IDTOModificationSalle {
	@Autowired
	private ISalleRepository modificationSalleRepository;
	@Autowired
	private IDTOGeneral dtoGeneral;
	@Autowired
	private IBatimentRepository dtoBatiment;


	@Override
	public Map<Integer, Salle> listeSalles() {
		Map<Integer, Salle> listePersonnes = new HashMap();
		List<SalleBDD> listeSalles = modificationSalleRepository.findAll();
		for (SalleBDD salleBDD : listeSalles) {
			listePersonnes.put(salleBDD.getId(), dtoGeneral.salleBDDToSalle(salleBDD));
		}
		return listePersonnes;
	}

	@Override
	public Salle choixSalle(String id) {
		if(modificationSalleRepository.existsById(Integer.parseInt(id)))
			return dtoGeneral.salleBDDToSalle(modificationSalleRepository.findById(Integer.parseInt(id)).get());
		else
			return null;
	}

	@Override
	public ArrayList<Batiment> listerBatiment() {
		ArrayList<Batiment> bat = new ArrayList<Batiment>();
		List<BatimentBDD> batBdd = dtoBatiment.findAll();
		for (BatimentBDD batimentBDD : batBdd) {
			bat.add(dtoGeneral.batimentBDDtobatiment(batimentBDD));
		}
		return bat;
	}

	@Override
	public boolean updateSalle(Salle salle) {
		SalleBDD salleBDD = modificationSalleRepository.findById(salle.getId()).get();
		salleBDD.setCapacite(salle.getCapacite());
		salleBDD.setNom(salle.getNom());
		salleBDD.setNumero(salle.getNumero());
		salleBDD.setSurface(salle.getSurface());
		modificationSalleRepository.save(salleBDD);
		//TYPE SALLE
		return false;
	}

	@Override
	public boolean activerDesactiverSalle(int parseInt) {
		SalleBDD salleBDD = modificationSalleRepository.getOne(parseInt);
		//salleBDD.setActif(!salleBDD.isActif());
		return false;
	}

	@Override
	public boolean supprimerSalle(int parseInt) {
		modificationSalleRepository.deleteById(parseInt);
		return true;
	}
	
	
}
