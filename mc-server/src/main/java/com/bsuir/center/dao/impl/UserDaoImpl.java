package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.IUserDao;
import com.bsuir.center.domain.User;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class UserDaoImpl implements IUserDao {

	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.User";

	private UserDaoImpl() {
	}

	public String create(User entity) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		String login = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			login = (String) session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in create user functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return login;
	}

	public User read(String entityId) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		User user = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			user = (User) session.get(User.class, entityId);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read user functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return user;
	}

	public List<User> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<User> users = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			users = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all user functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return users;
	}

	public void update(User user) throws DaoException {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			session.update(user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in update user functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

	}

	public void delete(String login) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		User user = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			user = (User) session.get(User.class, login);

			session.delete(user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in delete user functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

	}

}
