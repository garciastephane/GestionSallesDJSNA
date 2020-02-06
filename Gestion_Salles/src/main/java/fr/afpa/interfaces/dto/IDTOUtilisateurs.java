package fr.afpa.interfaces.dto;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;

@Service
@ComponentScan("fr.afpa.interfaces.dto")
public interface IDTOUtilisateurs {

	public Map<Integer, Personne> listePersonnes();
	
	
	public boolean ajoutBDD(Personne personne, String login, String mdp, RolePersonne role);
	
	
	public boolean suppressionBDD(int id);
	
	
	public boolean modifierBDD(Personne user, int id, String mdp);
	
	
	
	
	public Personne personneDuLogin(String login);	
	
	
	public boolean authentificationReussie(String login, String mdp);
	
	
	public Personne user(String login, String mdp);
	
	
	public List<String> listeLog();
	
	
	
	
	public boolean activerDesactiverBDD(int id);
	
	
	public boolean ajoutMessageBDD(Message message);
	
	
	public boolean archivage(int id);
	
}
