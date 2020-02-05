package fr.afpa.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Message;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.interfaces.dao.IDAOModification;
import fr.afpa.utils.HibernateUtils;

@Service()
@Qualifier("modification")
public class DAOModification implements IDAOModification {

	/**
	 * Permet de supprimer un utilisateur dans la base de donnee
	 * 
	 * @param id l'id de l'utilisateur
	 * @return true si la suppression a ete effectuer, false si non
	 */
	public boolean suppressionUtilisateurBDD(int id) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		ProfilBDD p = session.load(ProfilBDD.class, id);
		session.delete(p);
		tx.commit();
		session.close();
		return true;
	}
	
	public boolean archivageDAO(int id) {
		Session s = HibernateUtils.getSession();
		Transaction tx = s.beginTransaction();
		MessageBDD message = s.load(MessageBDD.class, id);
		message.setArchive(true);
		s.update(message);
		tx.commit();
		s.close();
		return true;
	}

	/**
	 * Permet de desactiver un utilisateur dans la base de donnee s'il est 
	 * actif et de l'activer s'il est desactive
	 * 
	 * @param id l'id de l'utilisateur
	 * @return true si l'operation a ete effectuee
	 */
	public boolean activerDesactiverUtilisateurBDD(int id) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		ProfilBDD p = session.load(ProfilBDD.class, id);
		p.setActif(!p.isActif());
		session.update(p);
		tx.commit();
		session.close();
		return true;
	}

	/**
	 * Permet de modifier un utilisateur dans la base de donnee
	 * 
	 * @param userBdd l'utilisateur
	 * @param id      l'id de l'utilisateur
	 * @param mdp     le nouveau mot de passe
	 * @return true si la modification a ete effectuer, false si non
	 */
	public boolean modifierUtilisateurBDD(ProfilBDD userBdd, int id, String mdp) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		ProfilBDD p = session.load(ProfilBDD.class, id);
		p.setAdresse(userBdd.getAdresse());
		p.setDateNaissance(userBdd.getDateNaissance());
		p.setMail(userBdd.getMail());
		p.setNom(userBdd.getNom());
		p.setPrenom(userBdd.getPrenom());
		if (!"".equals(mdp))
			p.getLoginMdp().setMotdepasse(mdp);
		session.update(p);
		tx.commit();
		session.close();
		return true;
	}
}
