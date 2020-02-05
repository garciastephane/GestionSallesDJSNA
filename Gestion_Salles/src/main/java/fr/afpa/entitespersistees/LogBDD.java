package fr.afpa.entitespersistees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "login")
public class LogBDD implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	private String login;

	@Column
	private String motdepasse;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_profil")
	private ProfilBDD profil;

	
	@OneToMany(mappedBy = "logBdd", cascade = CascadeType.ALL)
	private List<LoginMessageBDD> listeLGM;
	
	
	public LogBDD() {
		super();
	}

	public LogBDD(String login, String mdp) {
		super();
		this.login = login;
		this.motdepasse = mdp;
		this.listeLGM = new ArrayList<LoginMessageBDD>();
	}

}
