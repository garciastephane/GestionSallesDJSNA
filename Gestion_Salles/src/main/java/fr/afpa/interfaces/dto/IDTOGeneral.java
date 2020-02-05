package fr.afpa.interfaces.dto;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.entitespersistees.RoleBDD;

@Service
public interface IDTOGeneral {

	public static RolePersonne roleBDDToRolePersonne(RoleBDD role) {return null;}
	
	public static Personne profilBDDToPersonne(ProfilBDD profilBDD) {return null;}
	
	public static ProfilBDD personneToProfilBDD(Personne personne) {return null;}
	
	public static MessageBDD messageToMessageBDD(Message message) {return null;}
	
	public static List<LoginMessageBDD> listeLoginsToListeLoginMessageBDD(
			List<String> destinataires, String expediteur) {return null;}
	
	public static List<Message> listeLoginMessageToListeMessage(String login) {return null;}
	
	public static List<Message> listeLoginMessageToListeMessageEnvoye(String login) {return null;}
	
}
