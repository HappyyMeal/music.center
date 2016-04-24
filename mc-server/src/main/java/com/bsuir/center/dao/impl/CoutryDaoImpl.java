package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.ICoutryDao;
import com.bsuir.center.domain.Country;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class CoutryDaoImpl implements ICoutryDao {

	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Country";

	public Integer create(Country entity) throws DaoException {
		return null;
	}

	public Country read(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Country> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Country> coutries = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			coutries = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all coutries functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return coutries;
	}

	public void update(Country entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void delete(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub

	}

}
