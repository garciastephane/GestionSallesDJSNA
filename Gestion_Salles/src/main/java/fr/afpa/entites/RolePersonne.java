package fr.afpa.entites;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RolePersonne {

	FORMATEUR("Formateur"), STAGIAIRE("Stagiaire");

	private String role;

	RolePersonne(String role) {
		this.role = role;
	}

}
