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
public class Centre {

	private String nom;
	private List<Batiment> listeBatiments;

	public Centre() {
		super();
	}

	public Centre(String nom) {
		super();
		this.nom = nom;
		this.listeBatiments = new ArrayList();
	}

	public Centre(String nom, List<Batiment> listeBatiments) {
		super();
		this.nom = nom;
		this.listeBatiments = listeBatiments;
	}

}
