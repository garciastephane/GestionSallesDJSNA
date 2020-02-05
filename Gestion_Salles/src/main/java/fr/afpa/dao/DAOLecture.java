package fr.afpa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.entitespersistees.TypeProfilBDD;
import fr.afpa.interfaces.dao.IDAOLecture;
import fr.afpa.utils.HibernateUtils;

@Service()
@Qualifier("lecture")
public class DAOLecture implements IDAOLecture {

	/**
	 * Permet de retourner la liste de tous les profils
	 * 
	 * @return la liste de tous les profils dans la base de donnees
	 */
	public List<ProfilBDD> listeTousProfils() {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from profil");
		List<ProfilBDD> listeProfils = (ArrayList<ProfilBDD>) query.list();
		session.close();
		return listeProfils;
	}

	/**
	 * Permet de Lire tout les logs
	 * 
	 * @return la liste des logs
	 */
	public List<LogBDD> listeTousLogs() {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from login");
		List<LogBDD> listeLogs = (ArrayList<LogBDD>) query.list();
		session.close();
		return listeLogs;
	}

	/**
	 * Permet de lire tout les utilisateurs
	 * 
	 * @return la liste des utilisateurs
	 */
	public List<ProfilBDD> listeTousUser() {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from profil where id_type_profil=2");
		List<ProfilBDD> listeLogs = (List<ProfilBDD>) query.list();
		session.close();
		return listeLogs;
	}

	/**
	 * Permet de retourner une liste qui contient le profil recherche via son login
	 * et son mot de passe
	 * 
	 * @return une liste contenant le profil rechercher s'il est trouve et une liste
	 *         vide sinon
	 */
	public List<LogBDD> authentification(String login, String mdp) {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from login where login.login='" + login + "' and motdepasse='" + mdp + "'");
		List<LogBDD> listeLMDP = (ArrayList<LogBDD>) query.list();
		session.close();
		return listeLMDP;
	}
	
	/**
	 * recuperer un user ou un admin selon son login et mot de passe
	 * @param login
	 * @param password
	 * @return
	 */
	public ProfilBDD getUser(String login, String password) {
		Session session = null;
		Transaction transaction = null;
		LogBDD auth = null;
		ProfilBDD personne = null;
		try {
			session = HibernateUtils.getSession();
			transaction = session.beginTransaction();

			auth = (LogBDD) session.createQuery("FROM login a WHERE a.login = :login")
					.setParameter("login", login).uniqueResult();
			personne = auth.getProfil();
			TypeProfilBDD type = personne.getTypeProfil();
			int idTypeProfil = type.getId_type_profil();
			if (auth != null && auth.getMotdepasse().equals(password) && idTypeProfil == 2) {
				transaction.commit();
				return personne;
			}else if(auth != null && auth.getMotdepasse().equals(password) && idTypeProfil == 1){
				transaction.commit();
				return personne;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return personne;
	}
	
	
	/**
	 * Lister les messages de l'utilisateur
	 * @param login
	 * @return
	 */
	//SELECT m.objet, m.contenu, m.date, lm.logBdd from login_message lm inner join lm.messageBdd m where lm.logBdd = '"+login+"' and  exp_dest = true
	public List<LoginMessageBDD> getAllMessages(String login){
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from login_message lm where lm.logBdd ='"+login+"' and lm.expDest=false");
		List<LoginMessageBDD> listeLoginExpediteurMessage = (ArrayList<LoginMessageBDD>) query.list();
		List<LoginMessageBDD> listeLoginMessage = new ArrayList<LoginMessageBDD>();
		LoginMessageBDD lmbdd = null;
		for (LoginMessageBDD loginMessageBDD : listeLoginExpediteurMessage) {
			int idMessage = loginMessageBDD.getMessageBdd().getId_message();
			Query query2 = session.createQuery("from login_message lm where lm.messageBdd ='"+idMessage+"' and lm.expDest=true");
			lmbdd = (LoginMessageBDD) query2.uniqueResult();
			listeLoginMessage.add(lmbdd);
		}
		session.close();
		return listeLoginMessage;
	}
	
	/**
	 * récupere une liste loginMessage si la personne qui se logg est expediteur
	 * @param login
	 * @return
	 */
	public List<LoginMessageBDD> getMessageEnvoye(String login){
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery("from login_message lm where lm.logBdd ='"+login+"' and lm.expDest=true");
		List<LoginMessageBDD> listeLoginExpediteurMessage = (ArrayList<LoginMessageBDD>) query.list();
		List<LoginMessageBDD> listeLoginMessage = new ArrayList<LoginMessageBDD>();
		List<LoginMessageBDD> lmbdd = null;
		for (LoginMessageBDD loginMessageBDD : listeLoginExpediteurMessage) {
			int idMessage = loginMessageBDD.getMessageBdd().getId_message();
			Query query2 = session.createQuery("from login_message lm where lm.messageBdd ='"+idMessage+"' and lm.expDest=false");
			lmbdd = (List<LoginMessageBDD>) query2.getResultList();
			listeLoginMessage.add(lmbdd.get(0));
		}
		session.close();
		return listeLoginMessage;
	}
	
	/**
	 * Récuperation d'une liste de destinataire selon l'id du message
	 * @param id du message
	 * @return une liste de destinataire
	 */
	public List<String> getAllDestinataire(int id){
		Session session = HibernateUtils.getSession();
		List<String> listeLoginDestinataireMessage = new ArrayList<String>();
		Query query = session.createQuery("from login_message lm where lm.messageBdd ='"+id+"' and lm.expDest=false");
		List<LoginMessageBDD> logBdd = query.list();
		for (LoginMessageBDD loginMessageBDD : logBdd) {
			String destinataire = loginMessageBDD.getLogBdd().getLogin();
			listeLoginDestinataireMessage.add(destinataire);
		}	
		return listeLoginDestinataireMessage;
		
	}
	
	
	/**
	 * Permet de recuperer un profil a partir de son login
	 * @param login : le login dont on cherche le profil
	 * @return le profil recherche
	 */
	public ProfilBDD profilDuLogin(String login) {
		Session session = null;
		Transaction transaction = null;
		LogBDD auth = null;
		ProfilBDD personne = null;
		try {
			session = HibernateUtils.getSession();
			transaction = session.beginTransaction();
			auth = (LogBDD) session.createQuery("FROM login a WHERE a.login = :login")
					.setParameter("login", login).uniqueResult();
			personne = auth.getProfil();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return personne;
	}
}
