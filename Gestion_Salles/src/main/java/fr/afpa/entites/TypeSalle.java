package fr.afpa.entites;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public enum TypeSalle {

	BUREAU(1,"bureau"), FORMATION(2,"formation"), INFIRMERIE(3,"infirmerie"), REUNION(4,"reunion");
	private Integer id;
	private String type;

	TypeSalle(Integer id , String type) {
		this.id = id;
		this.type = type;
	}

}
