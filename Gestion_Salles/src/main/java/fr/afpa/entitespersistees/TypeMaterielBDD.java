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
@Entity(name = "typemateriel")
public class TypeMaterielBDD {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TYPE_MATERIEL_SEQ")
	private Integer id_typemateriel;
	@Column
	private String type;
	
	@OneToMany(mappedBy = "typeMateriel")
	private List<MaterielBDD> materiel;
	
	/**
	 * 
	 */
	public TypeMaterielBDD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
