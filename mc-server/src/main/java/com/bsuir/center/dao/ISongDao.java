package com.bsuir.center.dao;

import java.util.List;

import com.bsuir.center.domain.Song;
import com.bsuir.center.exception.DaoException;

public interface ISongDao extends IGenericDao<Song, Integer> {

	public List<Song> findSongByTitle(String title) throws DaoException;
}
