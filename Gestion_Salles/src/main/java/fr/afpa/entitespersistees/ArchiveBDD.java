package fr.afpa.entitespersistees;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "archive")
public class ArchiveBDD {

	@SequenceGenerator(name = "ARCHIVE_SEQ", initialValue = 1, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARCHIVE_SEQ")
	private Integer id_archive;

	@Column
	private String nom;

	@Column
	private String prenom;

	@Column(name = "datenaissance")
	private Date dateNaissance;

	@Column
	private String mail;

	@Column
	private String adresse;

	@ManyToOne
	@JoinColumn(name = "id_role")
	private RoleBDD role;

	@ManyToOne
	@JoinColumn(name = "id_type_profil")
	private TypeProfilBDD typeProfil;

	public ArchiveBDD() {
		super();
	}

	public ArchiveBDD(String nom, String prenom, String mail, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
	}

}
