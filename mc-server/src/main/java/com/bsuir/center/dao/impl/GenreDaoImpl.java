package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.IGenreDao;
import com.bsuir.center.domain.Genre;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class GenreDaoImpl implements IGenreDao {

	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Genre";

	public Integer create(Genre entity) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public Genre read(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Genre> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Genre> genries = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			genries = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all genries functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return genries;
	}

	public void update(Genre entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void delete(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub

	}

}
