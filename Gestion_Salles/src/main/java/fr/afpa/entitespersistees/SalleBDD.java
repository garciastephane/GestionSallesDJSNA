package fr.afpa.entitespersistees;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity(name = "salle")
public class SalleBDD {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALLE_SEQ")
	private Integer id;
	@Column
	private String numero;
	@Column
	private String nom;
	@Column
	private int capacite;
	@Column
	private float surface;
	
	@ManyToOne
	@JoinColumn(name = "typesalle")
	private TypeSalleBDD typeSalle;
	
	@ManyToOne
	@JoinColumn(name = "batiment")
	private BatimentBDD batiment;
	
	@OneToMany(mappedBy = "salle")
	private List<ReservationBDD> reservation;
	
	@OneToMany(mappedBy = "salle")
	private List<MaterielBDD> materiel;

	/**
	 * 
	 */
	public SalleBDD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
