package com.bsuir.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsuir.center.dao.IPersonDao;
import com.bsuir.center.dao.IUserDao;
import com.bsuir.center.domain.Artist;
import com.bsuir.center.domain.Country;
import com.bsuir.center.domain.Genre;
import com.bsuir.center.domain.Person;
import com.bsuir.center.domain.Sex;
import com.bsuir.center.exception.DaoException;

@Controller
public class Handler {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IPersonDao personDao;

	@RequestMapping(value = "/hey", method = GET)
	public void main() {

		Sex sex = new Sex();
		sex.setSexId(1);
		Genre genre = new Genre();
		genre.setGenreId(1);

		Country country = new Country();
		country.setCountryId(1);

		Artist artist = new Artist();
		artist.setArtistId(27);
		artist.setGenre(genre);
		artist.setCountry(country);

		Person person = new Person();
		person.setArtistId(27);
		person.setFirstName("White");
		person.setLastName("Maula");
		person.setArtist(artist);
		person.setBirthday(new Date());
		person.setSex(sex);

		try {
			personDao.update(person);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
