package com.bsuir.center.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.IPlaylistDao;
import com.bsuir.center.domain.Playlist;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/playlist")
public class PlayListHandler {
	private static final Logger logger = Logger.getLogger(PlayListHandler.class);

	@Autowired
	private IPlaylistDao playlistDao;

	@RequestMapping(method = RequestMethod.POST)
	public Integer create(@RequestBody Playlist playlist) {
		Integer playlistId = null;

		try {
			playlistId = playlistDao.create(playlist);
		} catch (DaoException e) {
			logger.error("Can't create playlist due to DaoException.");
		}

		return playlistId;
	}

	@RequestMapping(value = "/{playlistID}", method = RequestMethod.GET)
	public Playlist read(@PathVariable Integer playlistID) {
		Playlist playlist = null;

		try {
			playlist = playlistDao.read(playlistID);
		} catch (DaoException e) {
			logger.error("Something wrong happend due to DaoException.");
		}

		return playlist;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Playlist> readAll() {
		List<Playlist> playlists = null;

		try {
			playlists = playlistDao.readAll();
		} catch (DaoException e) {
			logger.error("Can't read all playlists due to DaoException.");
		}

		return playlists;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public HttpStatus update(@RequestBody Playlist playlist) {
		try {
			playlistDao.update(playlist);
		} catch (DaoException e) {
			logger.error("Can't read all playlists due to DaoException.");
		}

		return HttpStatus.OK;
	}

	@RequestMapping(value = "/find/{userName}", method = RequestMethod.GET)
	public List<Playlist> findPlaylistsByUsername(@PathVariable String userName) {
		List<Playlist> playlists = null;

		try {
			playlists = playlistDao.findByUsername(userName);
		} catch (DaoException e) {
			logger.error("Can't read all playlists due to DaoException.");
		}

		return playlists;
	}
}
