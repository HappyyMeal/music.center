package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.ISexDao;
import com.bsuir.center.domain.Genre;
import com.bsuir.center.domain.Sex;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class SexDaoImpl implements ISexDao {
	
	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Sex";

	public Integer create(Sex entity) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public Sex read(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Sex> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Sex> sexs = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			sexs = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all sexs functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return sexs;
	}

	public void update(Sex entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void delete(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub

	}

}
