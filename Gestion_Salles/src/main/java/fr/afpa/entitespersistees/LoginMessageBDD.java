package fr.afpa.entitespersistees;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "login_message")
@IdClass(LoginMessageBDDId.class)
public class LoginMessageBDD implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "login")
	private LogBDD logBdd;
	
	@Id 
	@ManyToOne
	@JoinColumn(name = "id_message")
	private MessageBDD messageBdd;
	
	@Id
	@Column(name = "expediteur")
	private Boolean expDest;


	public LoginMessageBDD() {
		super();
	}

	public LoginMessageBDD(LogBDD logBdd, MessageBDD messageBdd, boolean expDest) {
		super();
		this.logBdd = logBdd;
		this.messageBdd = messageBdd;
		this.expDest = expDest;
	} 
	
}
