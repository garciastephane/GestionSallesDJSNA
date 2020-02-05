package fr.afpa.entitespersistees;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "reservation")
public class ReservationBDD {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_SEQ")
	private Integer id;
	@Column
	private String intitule;
	@Column
	private LocalDate dateDebut;
	@Column
	private LocalDate dateFin;
	
	@ManyToOne
	@JoinColumn(name = "salle")
	private SalleBDD salle;

	/**
	 * 
	 */
	public ReservationBDD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
