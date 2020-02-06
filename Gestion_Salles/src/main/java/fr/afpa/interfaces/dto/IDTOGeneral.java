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

	public RolePersonne roleBDDToRolePersonne(RoleBDD role);
	
	public Personne profilBDDToPersonne(ProfilBDD profilBDD);
	
	public ProfilBDD personneToProfilBDD(Personne personne);
	
	public MessageBDD messageToMessageBDD(Message message);
	
	public List<LoginMessageBDD> listeLoginsToListeLoginMessageBDD(
			List<String> destinataires, String expediteur);
	
	public List<Message> listeLoginMessageToListeMessage(String login);
	
	public List<Message> listeLoginMessageToListeMessageEnvoye(String login);
	
	
}
