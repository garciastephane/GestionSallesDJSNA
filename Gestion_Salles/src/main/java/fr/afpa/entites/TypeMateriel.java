package fr.afpa.entites;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public enum TypeMateriel {

	ORDINATEUR("ordinateur"), PRISE_RESEAUX("prise reseaux"), RETROPROJECTEUR("retroprojecteur");
	private String type;

	TypeMateriel(String type) {
		this.type = type;
	}

}
