package fr.afpa.entitespersistees;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "message")
public class MessageBDD {
	
	@SequenceGenerator(name = "MESSAGE_SEQ", initialValue = 1, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ")
	private Integer id_message;
	
	@Column
	private String objet;
	
	@Column
	private String contenu;
	
	@Column
	private LocalDateTime date;
	
	@Column
	private boolean archive;
	
	@OneToMany(mappedBy = "messageBdd", cascade = CascadeType.ALL)
	private List<LoginMessageBDD> listLoginMessage;
	
	public MessageBDD() {
		super();
	}

	public MessageBDD(String objet, String contenu, LocalDateTime date, boolean archive) {
		super();
		this.objet = objet;
		this.contenu = contenu;
		this.date = date;
		this.archive = archive;
		this.listLoginMessage = new ArrayList<LoginMessageBDD>();
	}
}
