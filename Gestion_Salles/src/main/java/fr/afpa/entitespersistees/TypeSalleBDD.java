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
@Entity(name = "typesalle")
public class TypeSalleBDD {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TYPE_SALLE_SEQ")
	private Integer id;
	@Column
	private String type;
	@OneToMany(mappedBy = "typeSalle")
	private List<SalleBDD> salle;

	/**
	 * 
	 */
	public TypeSalleBDD() {
		super();
		// TODO Auto-generated constructor stub
	}

}
