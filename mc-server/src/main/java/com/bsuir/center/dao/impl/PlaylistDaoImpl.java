package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.IPlaylistDao;
import com.bsuir.center.domain.Playlist;
import com.bsuir.center.domain.User;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class PlaylistDaoImpl implements IPlaylistDao {
	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Playlist";

	public Integer create(Playlist entity) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		Integer id = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			id = (Integer) session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in create playlist functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return id;
	}

	public Playlist read(Integer entityId) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		Playlist playlist = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			playlist = session.get(Playlist.class, entityId);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read playlist by id functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return playlist;
	}

	public List<Playlist> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Playlist> playlists = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			playlists = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all playlists functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return playlists;
	}

	public void update(Playlist entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void delete(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub

	}

	public List<Playlist> findByUsername(String username) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		List<Playlist> playlists = null;

		User user = new User();
		user.setUserLogin(username);

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			playlists = session.createCriteria(Playlist.class).add(Restrictions.eq("user", user)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read playlists by username functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return playlists;
	}

}
