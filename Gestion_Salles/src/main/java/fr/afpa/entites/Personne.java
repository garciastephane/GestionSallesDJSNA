package fr.afpa.entites;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Personne {
	protected int id_personne;
	protected String nom;
	protected String prenom;
	protected LocalDate dateNaissance;
	protected String email;
	protected String adresse;
	protected boolean actif;
	protected RolePersonne role;

	public Personne() {
		super();
	}

	public Personne(String nom, String prenom, LocalDate dateNaissance, String email, String adresse, boolean actif,
			RolePersonne role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.adresse = adresse;
		this.actif = actif;
		this.role = role;
	}

}
