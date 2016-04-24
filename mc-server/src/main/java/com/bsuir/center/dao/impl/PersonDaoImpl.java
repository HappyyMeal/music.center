package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.IPersonDao;
import com.bsuir.center.domain.Country;
import com.bsuir.center.domain.Genre;
import com.bsuir.center.domain.Person;
import com.bsuir.center.domain.Sex;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class PersonDaoImpl implements IPersonDao {
	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Person";

	public Integer create(Person entity) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		Integer createdId = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			createdId = (Integer) session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in save person functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return createdId;
	}

	public Person read(Integer entityId) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		Person person = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			person = session.get(Person.class, entityId);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read person functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}
		return person;
	}

	public List<Person> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Person> persons = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			persons = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all persons functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return persons;
	}

	public void update(Person entity) throws DaoException {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			session.update(entity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in update person functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void delete(Integer entityId) throws DaoException {
		Person person = new Person();
		person.setArtistId(entityId);

		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			session.delete(person);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in delete person functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
