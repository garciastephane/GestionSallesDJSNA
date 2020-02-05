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
public class Salle {

	private String numero;
	private String nom;
	private int capacite;
	private int nbrChaise;
	private int nbrTable;
	private float surface;
	private TypeSalle typeSalle;
	private List<Materiel> listeMateriels;
	private List<Reservation> listeReservations;

	public Salle() {
		super();
	}

	public Salle(String numero, String nom, int capacite, int nbrChaise, int nbrTable, float surface,
			TypeSalle typeSalle) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.capacite = capacite;
		this.nbrChaise = nbrChaise;
		this.nbrTable = nbrTable;
		this.surface = surface;
		this.typeSalle = typeSalle;
		this.listeMateriels = new ArrayList();
		this.listeReservations = new ArrayList();
	}

}
