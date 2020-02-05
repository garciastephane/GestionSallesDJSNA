package fr.afpa.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Materiel {

	private TypeMateriel type;
	private int quantite;

	public Materiel() {
		super();
	}

	public Materiel(TypeMateriel type, int quantite) {
		super();
		this.type = type;
		this.quantite = quantite;
	}

}
