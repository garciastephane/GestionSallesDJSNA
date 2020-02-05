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
public class Reservation {
	private Integer id;
	private String intitule;
	private LocalDate dateDebut;
	private LocalDate dateFin;

	public Reservation() {
		super();
	}

	public Reservation(String intitule, LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.intitule = intitule;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

}
