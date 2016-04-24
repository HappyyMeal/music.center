package com.bsuir.center.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.ISongDao;
import com.bsuir.center.domain.Song;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/song")
public class SongHandler {
	private static final Logger logger = Logger.getLogger(SongHandler.class);

	@Autowired
	private ISongDao songDao;

	@RequestMapping(method = GET)
	public List<Song> readAllSongs() {
		List<Song> songs = null;

		try {
			songs = songDao.readAll();
		} catch (DaoException e) {
			logger.error("Can't read all users due to DaoException.");
		}

		return songs;
	}

	@RequestMapping(value = "/{songID}", method = GET)
	public Song read(@PathVariable Integer songID) {
		Song song = null;

		try {
			song = songDao.read(songID);
		} catch (DaoException e) {
			logger.error("Can't read song due to DaoException.");
		}
		return song;
	}

	@RequestMapping(value = "find/{songTitle}", method = GET)
	public List<Song> findSong(@PathVariable String songTitle, HttpServletResponse response) {
		List<Song> songs = null;

		try {
			songs = songDao.findSongByTitle(songTitle);
		} catch (DaoException e) {
			logger.error("Can't read all users due to DaoException.");
		}

		response.addHeader("Access-Control-Allow-Origin", "*");
		return songs;
	}
}
