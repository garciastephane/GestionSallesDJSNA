package fr.afpa.entitespersistees;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "profil")
public class ProfilBDD {

	@SequenceGenerator(name = "PROFIL_SEQ", initialValue = 2, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFIL_SEQ")
	private int id_profil;

	@Column
	private String nom;

	@Column
	private String prenom;

	@Column(name = "datenaissance")
	private Date dateNaissance;

	@Column
	private boolean actif;

	@Column
	private String mail;

	@Column
	private String adresse;

	@OneToOne(mappedBy = "profil", cascade = CascadeType.ALL)
	private LogBDD loginMdp;

	@ManyToOne
	@JoinColumn(name = "id_role")
	private RoleBDD role;

	@ManyToOne
	@JoinColumn(name = "id_type_profil")
	private TypeProfilBDD typeProfil;

	
	
	
	public ProfilBDD() {
		super();
	}

	public ProfilBDD(String nom, String prenom, String mail, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.actif = true;
	}

}
