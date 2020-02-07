package fr.afpa.interfaces.dto;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.Batiment;
import fr.afpa.entites.Materiel;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.Reservation;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entites.Salle;
import fr.afpa.entites.TypeMateriel;
import fr.afpa.entitespersistees.BatimentBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.MaterielBDD;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.entitespersistees.ReservationBDD;
import fr.afpa.entitespersistees.RoleBDD;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.entitespersistees.TypeMaterielBDD;

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
	
	
	public Salle salleBDDToSalle(SalleBDD salleBDD);
	
	public Salle salleBDDToSalle2(SalleBDD salleBDD) ;

	public Batiment batimentBDDToBatiment(BatimentBDD batimentBDD);
	
	public List<Materiel> listMaterielBDDToListMateriel(List<MaterielBDD> listMaterielBDD);
	
	public Materiel materielBDDToMateriel(MaterielBDD materielBDD);
	
	public TypeMateriel typeMaterielBDDToTypeMateriel(TypeMaterielBDD typematerielBDD);
	
	public List<Reservation> listReservationBDDToListReservation(List<ReservationBDD> listReservationBDD);
	
	public Reservation reservationBDDToReservation(ReservationBDD reservationBDD);
}
