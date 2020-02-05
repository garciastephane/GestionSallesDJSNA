package fr.afpa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Administrateur;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.MessageBDD;
import fr.afpa.entitespersistees.ProfilBDD;
import fr.afpa.entitespersistees.RoleBDD;
import fr.afpa.entitespersistees.TypeProfilBDD;
import fr.afpa.interfaces.dao.IDAOCreation;
import fr.afpa.utils.HibernateUtils;

@Service()
@Qualifier("creation")
public class DAOCreation implements IDAOCreation {

	/**
	 * 
	 */
	public DAOCreation() {
		super();
	}

	/**
	 * Permet d'enregistrer un utilisateur dans la base de donnee
	 * 
	 * @param profil   le profil de l'utilisateur
	 * @param log      les logs de l'utilisateur
	 * @param role     le role de l'utilisateur
	 * @param personne l'utilisateur
	 * @return true si l'enregistrement est effectuer, false si non
	 */
	public boolean enregistrerUtilisateur(ProfilBDD profil, LogBDD log, RolePersonne role, Personne personne) {
		Session session = HibernateUtils.getSession();
		TypeProfilBDD typeProfil;
		RoleBDD roleBDD;
		// obtention du role
		if ("Formateur".equals(role.getRole())) {
			roleBDD = session.get(RoleBDD.class, 1);
		} else {
			roleBDD = session.get(RoleBDD.class, 2);
		}
		// obtention du type de profil
		if (personne instanceof Administrateur) {
			typeProfil = session.get(TypeProfilBDD.class, 1);
		} else {
			typeProfil = session.get(TypeProfilBDD.class, 2);
		}
		profil.setTypeProfil(typeProfil);
		profil.setRole(roleBDD);
		roleBDD.getListeProfils().add(profil);
		Transaction tx = session.beginTransaction();
		session.save(profil);
		session.save(log);
		tx.commit();
		session.close();
		return false;
	}
	
	/**
	 * Permet d'enregistrer un message dans la base de donnees
	 * @param message : le message a enregistrer
	 * @return true si le message a bien ete enregistre et false sinon
	 */
	public boolean enregistrerMessage(MessageBDD message, List<LoginMessageBDD> listeLogins) {
		Session session = null;
		Transaction tx = null;
		boolean flag = true;
		try {
			session = HibernateUtils.getSession();
			for (LoginMessageBDD lmbdd : listeLogins) {
				LogBDD login = session.load(LogBDD.class, lmbdd.getLogBdd().getLogin());
				lmbdd.setLogBdd(login);
			}
			for (LoginMessageBDD loginMessageBDD : listeLogins) {
				loginMessageBDD.setMessageBdd(message);
			}
			
			message.setListLoginMessage(listeLogins);
			
			tx = session.beginTransaction();
			session.save(message);
			
			for (LoginMessageBDD loginMessageBDD : listeLogins) {
				session.save(loginMessageBDD);
			}
			
			
			
			tx.commit();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return flag;
	}

}
