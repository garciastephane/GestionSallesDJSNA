package fr.afpa.services;

import java.util.ArrayList;
import java.util.List;
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
		List<Salle> listeSalles = dtoModificationSalle.listeSalles();
		String res = "";
		for (Salle salle : listeSalles) {
			res += "<tr>";
			res += "<td>" + salle.getId() + "</td>";
			res += "<td>" + salle.getNumero() + "</td>";
			res += "<td>" + salle.getTypeSalle().getType() + "</td>";
			res += "<td>" + salle.getNom() + "</td>";
			res += "<td>" + salle.getCapacite() + "</td>";
			res += "<td>" + salle.getSurface() + "</td>";
			res += "<td><button>Reserver</button></td>";
			res += "</tr>";
		}
		return res;
	}

	@Override
	public String listeSalle2() {
		List<Salle> listeSalles = dtoModificationSalle.voirSalles();
		String res = "";
		for (Salle salle : listeSalles) {
			res += "<tr>";
			res += "<td>" + salle.getId() + "</td>";
			res += "<td>" + salle.getNumero() + "</td>";
			res += "<td>" + salle.getTypeSalle().getType() + "</td>";
			res += "<td>" + salle.getNom() + "</td>";
			res += "<td>" + salle.getCapacite() + "</td>";
			res += "<td>" + salle.getSurface() + "</td>";
			res += "<td>" + salle.getBatiment().getNom() + "</td>";
			res += "<td><button>voir</button></td>";
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
	public boolean supprimerSalle(int parseInt) {
		return dtoModificationSalle.supprimerSalle(parseInt);
	}

	@Override
	public String voirMateriel(int id) {
		Map<String, Integer> materiels = dtoModificationSalle.voirMateriel(id);
		String res = "";
		for (Entry<String, Integer> materiel : materiels.entrySet()) {
			res += "<tr>";
			res += "<td>" + materiel.getKey() + "</td>";
			res += "<td>" + materiel.getValue() + "</td>";
			res += "<td> <button>ajouter</button> </td>";
			res += "<td> <button>retirer</button> </td>";
			res += "</tr>";
		}
		return res;
	}

	
}
