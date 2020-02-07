package fr.afpa.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;
import fr.afpa.entites.TypeMateriel;
import fr.afpa.entitespersistees.BatimentBDD;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOModificationSalle;
import fr.afpa.repositories.IBatimentRepository;
import fr.afpa.repositories.IMaterielRepository;
import fr.afpa.repositories.ISalleRepository;
import fr.afpa.repositories.ITypeMaterielRepository;

@Service
public class DTOModificationSalle implements IDTOModificationSalle {
	@Autowired
	private ISalleRepository modificationSalleRepository;
	@Autowired
	private IDTOGeneral dtoGeneral;
	@Autowired
	private IBatimentRepository dtoBatiment;
	@Autowired
	private ITypeMaterielRepository typeMaterielRepository;
	@Autowired
	private IMaterielRepository materielRepository;

	@Override
	public List<Salle> listeSalles() {
		List<Salle> listeSalle = new ArrayList<Salle>();
		List<SalleBDD> listeSalles = modificationSalleRepository.findAll();
		for (SalleBDD salleBDD : listeSalles) {
			listeSalle.add(dtoGeneral.salleBDDToSalle(salleBDD));
		}
		return listeSalle;
	}
	
	@Override
	public List<Salle> voirSalles() {
		List<Salle> listeSalle = new ArrayList<Salle>();
		List<SalleBDD> listeSalles = modificationSalleRepository.findAll();
		for (SalleBDD salleBDD : listeSalles) {
			listeSalle.add(dtoGeneral.salleBDDToSalle2(salleBDD));
		}
		return listeSalle;
	}

	@Override
	public Salle choixSalle(String id) {
		if (modificationSalleRepository.existsById(Integer.parseInt(id)))
			return dtoGeneral.salleBDDToSalle(modificationSalleRepository.findById(Integer.parseInt(id)).get());
		else
			return null;
	}

	@Override
	public ArrayList<Batiment> listerBatiment() {
		ArrayList<Batiment> bat = new ArrayList<Batiment>();
		List<BatimentBDD> batBdd = dtoBatiment.findAll();
		for (BatimentBDD batimentBDD : batBdd) {
			bat.add(dtoGeneral.batimentBDDToBatiment(batimentBDD));
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
		// TYPE SALLE
		return false;
	}

	@Override
	public boolean supprimerSalle(int parseInt) {
		modificationSalleRepository.deleteById(parseInt);
		return true;
	}

	@Override
	public Map<String, Integer> voirMateriel(int id) {
		Map<String, Integer> materiels = new HashMap<String, Integer>();
		TypeMateriel[] type = TypeMateriel.values();
		for (TypeMateriel typeMateriel : type) {
			List<Integer> i = materielRepository.findQuantiteByTypeMateriel(id, typeMateriel.getType().toUpperCase());
			materiels.put(typeMateriel.getType().toUpperCase(), (!i.isEmpty()) ? i.get(0) : 0);
		}
		return materiels;
	}

}
