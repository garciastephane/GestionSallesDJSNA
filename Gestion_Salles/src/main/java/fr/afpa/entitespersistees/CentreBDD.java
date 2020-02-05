package fr.afpa.entitespersistees;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "centre")
public class CentreBDD {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CENTRE_SEQ")
	private int id_centre;
	@Column
	private String nom;
	@OneToMany(mappedBy = "centre")
	private List<BatimentBDD> listeBatiment;
	
	/**
	 * 
	 */
	public CentreBDD() {
		super();
	}
	
	
}
