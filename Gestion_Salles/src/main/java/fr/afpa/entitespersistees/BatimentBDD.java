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

@Getter
@Setter
@Entity(name = "batiment")
public class BatimentBDD {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATIMENT_SEQ")
	private Integer id;
	@Column
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "id_centre")
	private CentreBDD centre;
	
	@OneToMany(mappedBy = "batiment")
	private List<SalleBDD> salle;

	/**
	 * 
	 */
	public BatimentBDD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
