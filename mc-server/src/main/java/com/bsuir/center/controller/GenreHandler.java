package com.bsuir.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.IGenreDao;
import com.bsuir.center.domain.Genre;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/genre")
public class GenreHandler {

	@Autowired
	private IGenreDao genreDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Genre> readAll() {
		List<Genre> genres = null;

		try {
			genres = genreDao.readAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return genres;
	}
}
