package fr.afpa.entitespersistees;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "typeprofil")
public class TypeProfilBDD {

	@SequenceGenerator(name = "TYPE_PROFIL_SEQ", initialValue = 3, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TYPE_PROFIL_SEQ")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Integer id_type_profil;

	@Column
	private String libelle;

	@OneToMany(mappedBy = "typeProfil")
	private List<ProfilBDD> listeProfils;

	@OneToMany(mappedBy = "typeProfil")
	private List<ArchiveBDD> listeArchives;

	public TypeProfilBDD() {
		super();
	}

	public TypeProfilBDD(String libelle) {
		super();
		this.libelle = libelle;
	}
	
}
