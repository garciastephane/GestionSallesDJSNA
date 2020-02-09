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
			res += "<form action=\"vrc\" ><input type=\"hidden\" name=\"id\" value=\""+salle.getId()+"\"><td><input type=\"submit\" value=\"VOIR\"></td></form>";
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
	public Salle getSalle2(String id) {
		return dtoModificationSalle.choixSalle2(id);
	}
	
	@Override
	public boolean updateSalle(Salle salle, int retro, int ordi, int reseau) {
		return dtoModificationSalle.updateSalle(salle, retro, ordi, reseau);
	}


	@Override
	public boolean supprimerSalle(int parseInt) {
		return dtoModificationSalle.supprimerSalle(parseInt);
	}

	@Override
	public String voirMateriel(int id) {
		Map<String, Integer> materiels = dtoModificationSalle.voirMateriel(id);
		String res = "";
		int i = 1;
		for (Entry<String, Integer> materiel : materiels.entrySet()) {
			res += "<tr>";
			res += "<td>" + materiel.getKey() + "</td>";
			res += "<td><input type='number' name='"+(i++)+"' value='"+materiel.getValue()+"'></td>";
			res += "</tr>";
		}
		return res;
	}

	
}
