package fr.afpa.entites;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Administrateur extends Personne {

	public Administrateur() {
		super();
	}

	public Administrateur(String nom, String prenom, LocalDate dateNaissance, String email, String adresse,
			boolean actif, RolePersonne role) {
		super(nom, prenom, dateNaissance, email, adresse, actif, role);
	}

}
