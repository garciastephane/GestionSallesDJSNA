package fr.afpa.entites;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TypeSalle {

	BUREAU("bureau"), FORMATION("formation"), INFIRMERIE("infirmerie"), REUNION("reunion");

	private Integer id;
	private String type;

	TypeSalle(String type) {
		this.type = type;
	}

}
