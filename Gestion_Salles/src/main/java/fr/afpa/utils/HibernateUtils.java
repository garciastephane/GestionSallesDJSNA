package fr.afpa.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

;

public class HibernateUtils {
	private static final SessionFactory sessionFactory;

	// Cree une unique instance de la SessionFactory a partir du fichier
	// hibernate.cfg.xml
	static {
		try {
			// Instanciation de la sessionFactory
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Probleme de configuration : " + ex.getMessage(), ex);
		}
	}

	// Renvoie une session Hibernate
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}
