package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.IGroupDao;
import com.bsuir.center.domain.Group;
import com.bsuir.center.domain.Person;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class GroupDaoImpl implements IGroupDao {
	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Group";

	public Integer create(Group entity) throws DaoException {
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

	public Group read(Integer entityId) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		Group group = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			group = session.get(Group.class, entityId);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read group functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}
		return group;
	}

	public List<Group> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Group> groups = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			groups = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all groups functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return groups;
	}

	public void update(Group entity) throws DaoException {
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

			throw new DaoException("Hibernate exception in update group functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void delete(Integer entityId) throws DaoException {
		Group group = new Group();
		group.setArtistId(entityId);

		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			session.delete(group);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in delete group functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
