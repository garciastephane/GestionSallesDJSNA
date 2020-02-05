package fr.afpa.entitespersistees;

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
@Entity(name = "materiel")
public class MaterielBDD {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIEL_SEQ")
	private Integer id;
	@Column
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name = "salle")
	private SalleBDD salle;
	
	@ManyToOne
	@JoinColumn(name = "typemateriel")
	private TypeMaterielBDD typeMateriel;

	/**
	 * 
	 */
	public MaterielBDD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
