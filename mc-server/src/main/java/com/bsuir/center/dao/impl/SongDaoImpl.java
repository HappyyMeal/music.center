package com.bsuir.center.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bsuir.center.dao.ISongDao;
import com.bsuir.center.domain.Song;
import com.bsuir.center.exception.DaoException;
import com.bsuir.center.util.HibernateUtil;

@Repository
public class SongDaoImpl implements ISongDao {

	private static final String READ_ALL_HQL = "from com.bsuir.center.domain.Song";

	public Integer create(Song entity) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public Song read(Integer entityId) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		Song song = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			song = session.get(Song.class, entityId);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read song functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return song;
	}

	public List<Song> readAll() throws DaoException {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		List<Song> songs = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			query = session.createQuery(READ_ALL_HQL);
			songs = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in read all songs functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return songs;
	}

	public void update(Song entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void delete(Integer entityId) throws DaoException {
		// TODO Auto-generated method stub

	}

	public List<Song> findSongByTitle(String title) throws DaoException {
		Session session = null;
		Transaction transaction = null;
		List<Song> songs = null;

		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();

			songs = session.createCriteria(Song.class).add(Restrictions.like("songTitle", title, MatchMode.ANYWHERE)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			throw new DaoException("Hibernate exception in find song by title functionality", e);
		} finally {
			HibernateUtil.closeSession();
		}

		return songs;
	}

}
