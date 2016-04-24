package com.bsuir.center.dao;

import java.util.List;

import com.bsuir.center.domain.Playlist;
import com.bsuir.center.exception.DaoException;

public interface IPlaylistDao extends IGenericDao<Playlist, Integer> {
	public List<Playlist> findByUsername(String username) throws DaoException;
}
