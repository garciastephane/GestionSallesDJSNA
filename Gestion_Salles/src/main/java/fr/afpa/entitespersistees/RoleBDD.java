package fr.afpa.entitespersistees;

import java.util.ArrayList;
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

@Entity(name = "roleprofil")
public class RoleBDD {

	@SequenceGenerator(name = "ROLE_SEQ", initialValue = 3, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private int id_role;

	@Column
	private String libelle;

	@OneToMany(mappedBy = "role")
	private List<ProfilBDD> listeProfils;

	@OneToMany(mappedBy = "role")
	private List<ArchiveBDD> listeArchives;

	public RoleBDD() {
		super();
	}

	public RoleBDD(String libelle) {
		super();
		this.libelle = libelle;
		this.listeProfils = new ArrayList();
	}

}
