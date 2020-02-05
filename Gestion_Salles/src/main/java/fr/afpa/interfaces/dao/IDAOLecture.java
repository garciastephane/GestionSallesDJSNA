package fr.afpa.interfaces.dao;

import java.util.List;

import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;

public interface IDAOLecture {

	
	public List<ProfilBDD> listeTousProfils();
	
	
	public List<LogBDD> listeTousLogs();
	
	
	public List<ProfilBDD> listeTousUser();
	
	
	public List<LogBDD> authentification(String login, String mdp);
	
	
	public ProfilBDD getUser(String login, String password);
	
	
	public List<LoginMessageBDD> getAllMessages(String login);
	
	
	public List<LoginMessageBDD> getMessageEnvoye(String login);
	
	
	public List<String> getAllDestinataire(int id);
	
	
	public ProfilBDD profilDuLogin(String login);
	
}
