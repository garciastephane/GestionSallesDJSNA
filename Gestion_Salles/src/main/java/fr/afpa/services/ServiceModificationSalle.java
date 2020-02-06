package fr.afpa.services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Salle;
import fr.afpa.interfaces.dto.IDTOModificationSalle;
import fr.afpa.interfaces.services.IServiceModificationSalle;

@Service
public class ServiceModificationSalle implements IServiceModificationSalle {
	
	@Autowired
	private IDTOModificationSalle dtoModificationSalle;
	
	public boolean choixSalle(int id) {
		return false;
	}
	
	@Override
	public String voirSalle() {
		Map<Integer, Salle> listeSalles = dtoModificationSalle.listeSalles();
		String res = "";
		for (Entry<Integer, Salle> coupleSalle : listeSalles.entrySet()) {
			Salle salle = coupleSalle.getValue();
			res += "<tr>";
			res += "<td>" + salle.getId() + "</td>";
			res += "<td>" + salle.getNumero() + "</td>";
			res += "<td>" + salle.getTypeSalle().getType() + "</td>";
			res += "<td>" + salle.getNom() + "</td>";
			res += "<td>" + salle.getCapacite() + "</td>";
			res += "<td>" + salle.getSurface() + "</td>";
			res += "</tr>";
		}
		return res;
	}

	@Override
	public ArrayList<Batiment> listerBatiment() {
		return dtoModificationSalle.listerBatiment();
	}
	

	@Override
	public Salle getSalle(String id) {
		return dtoModificationSalle.choixSalle(id);
	}

	@Override
	public boolean updateSalle(Salle salle) {
		return dtoModificationSalle.updateSalle(salle);
	}

	@Override
	public boolean activerDesactiverSalle(int parseInt) {
		return dtoModificationSalle.activerDesactiverSalle(parseInt);
	}

	@Override
	public boolean supprimerSalle(int parseInt) {
		return dtoModificationSalle.supprimerSalle(parseInt);
	}
}
