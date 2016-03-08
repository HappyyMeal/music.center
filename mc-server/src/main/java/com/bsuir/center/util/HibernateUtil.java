package com.bsuir.center.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final String CONFIG_PATH = "hibernate/hibernate.cfg.xml";

	public static final ThreadLocal session = new ThreadLocal();
	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure(CONFIG_PATH).buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);
		}
	}

	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();

		// Open a new Session, if this Thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null)
			s.close();
	}

}
