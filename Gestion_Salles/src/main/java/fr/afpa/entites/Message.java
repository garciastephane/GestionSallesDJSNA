package fr.afpa.entites;

import java.time.LocalDateTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Message {

	private int id;
	private String expediteur;
	private List<String> destinataires;
	private String objet;
	private String contenu;
	private LocalDateTime date;
	private boolean archivage;
	
	
	public Message() {
		super();
	}
	
	public Message(String expediteur, List<String> destinataires, String objet, String contenu, LocalDateTime date, boolean archivage) {
		super();
		this.expediteur = expediteur;
		this.destinataires = destinataires;
		this.objet = objet;
		this.contenu = contenu;
		this.date = date;
		this.archivage=archivage;
	}

}
