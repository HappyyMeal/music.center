package com.bsuir.center.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.IPersonDao;
import com.bsuir.center.domain.Person;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/person")
public class PersonHandler {
	private static final Logger logger = Logger.getLogger(PersonHandler.class);

	@Autowired
	private IPersonDao personDao;

	@RequestMapping(value = "/{artistId}", method = GET)
	public Person read(@PathVariable Integer artistId) {
		Person person = null;

		try {
			person = personDao.read(artistId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return person;
	}

	@RequestMapping(method = GET)
	public List<Person> readAllGroups() {
		List<Person> persons = null;

		try {
			persons = personDao.readAll();
		} catch (DaoException e) {
			logger.error("Can't read all users due to DaoException.");
		}

		return persons;
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
		try {
			personDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(method = POST)
	public Integer createPerson(@RequestBody Person person) {
		Integer id = null;
		try {
			id = personDao.create(person);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@RequestMapping(value = "/{id}", method = POST)
	public ResponseEntity<String> updatePerson(@RequestBody Person person) {
		try {
			personDao.update(person);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
