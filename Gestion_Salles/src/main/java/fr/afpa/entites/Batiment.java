package fr.afpa.entites;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Batiment {
	private Integer id;
	private String nom;
	private List<Salle> listeSalles;

	public Batiment() {
		super();
	}

	public Batiment(String nom, List<Salle> listeSalles) {
		super();
		this.nom = nom;
		this.listeSalles = listeSalles;
	}

	public Batiment(String nom) {
		super();
		this.nom = nom;
		this.listeSalles = new ArrayList();
	}
}
