package fr.afpa.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;
import fr.afpa.entites.TypeMateriel;
import fr.afpa.entitespersistees.BatimentBDD;
import fr.afpa.entitespersistees.MaterielBDD;
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
			listeSalle.add(dtoGeneral.salleBDDToSalleComplete(salleBDD));
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
	public Salle choixSalleComplete(String id) {
		if (modificationSalleRepository.existsById(Integer.parseInt(id)))
			return dtoGeneral.salleBDDToSalleComplete(modificationSalleRepository.findById(Integer.parseInt(id)).get());
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
	public boolean updateSalle(Salle salle, int retro, int ordi, int reseau) {
		SalleBDD salleBDD = modificationSalleRepository.findById(salle.getId()).get();
		salleBDD.setCapacite(salle.getCapacite());
		salleBDD.setNom(salle.getNom());
		salleBDD.setNumero(salle.getNumero());
		salleBDD.setSurface(salle.getSurface());
		modificationSalleRepository.save(salleBDD);
		List<MaterielBDD> materiel = materielRepository.findBySalleOrderByTypemateriel(salleBDD.getId());
		MaterielBDD retroprojecteur = materielRepository.findById(materiel.get(0).getId()).get();
		retroprojecteur.setQuantite(retro);
		materielRepository.save(retroprojecteur);
		MaterielBDD ordinateur = materielRepository.findById(materiel.get(1).getId()).get();
		ordinateur.setQuantite(ordi);
		materielRepository.save(ordinateur);
		MaterielBDD priseReseau = materielRepository.findById(materiel.get(2).getId()).get();
		priseReseau.setQuantite(reseau);
		materielRepository.save(priseReseau);
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
