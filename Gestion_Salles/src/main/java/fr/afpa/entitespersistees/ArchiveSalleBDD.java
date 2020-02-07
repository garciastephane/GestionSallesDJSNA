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
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "archivesalle")
public class ArchiveSalleBDD {
	@SequenceGenerator(name = "ARCHIVE_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARCHIVE_SALLE_SEQ")
	@Id
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
	public ArchiveSalleBDD() {
		super();
		// TODO Auto-generated constructor stub
	}
}
