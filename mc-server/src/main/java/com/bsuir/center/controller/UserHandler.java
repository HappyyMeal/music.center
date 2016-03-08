package com.bsuir.center.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.IUserDao;
import com.bsuir.center.domain.User;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/mucis/user")
public class UserHandler {

	@Autowired
	private IUserDao userDao;

	@RequestMapping(method = POST)
	public String createUser(@RequestBody User user) {
		String createdLogin = null;

		try {
			createdLogin = userDao.create(user);
		} catch (DaoException e) {
			// TODO Log4j should be here due to lack of INET cannot currently
			// download it.
		}

		return createdLogin;
	}

	@RequestMapping(value = "/{login}", method = GET)
	public User readUser(@PathVariable String login) {

		User user = null;

		try {
			user = userDao.read(login);
		} catch (DaoException e) {
			// TODO Log4j should be here due to lack of INET cannot currently
			// download it.

		}

		return user;
	}

	@RequestMapping(method = GET)
	public List<User> readAllUsers() {
		List<User> users = null;

		try {
			users = userDao.readAll();
		} catch (DaoException e) {
			// TODO Log4j should be here due to lack of INET cannot currently
			// download it.
		}

		return users;

	}

	@RequestMapping(method = PUT)
	public void update(@RequestBody User user) {

		try {
			userDao.update(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{login}", method = DELETE)
	public void delete(@PathVariable String login) {
		try {
			userDao.delete(login);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
